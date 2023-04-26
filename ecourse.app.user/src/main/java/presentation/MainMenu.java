package presentation;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import org.authz.application.AuthorizationService;
import org.authz.application.AuthzRegistry;
import org.user.management.CourseRoles;
import presentation.usermanagement.DisableUserUI;
import presentation.usermanagement.EnableUserUI;
import presentation.usermanagement.RegisterUserUI;

public class MainMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // MANAGER SUB-MENUS
    private static final int SET_MANAGER_MANAGE_USERS_OPTION = 1;

    //MANAGER MANAGE USER's
    private static final int SET_MANAGER_CREATE_USERS_OPTION = 1;
    private static final int SET_MANAGER_ENABLE_USER_OPTION = 2;
    private static final int SET_MANAGER_DISABLE_USER_OPTION = 3;

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;

        renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);

        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        if (authz.isAuthenticatedUserAuthorizedTo(CourseRoles.MANAGER)) {
            final Menu managerMenu = buildManagerMenu();
            mainMenu.addSubMenu(SET_MANAGER_MANAGE_USERS_OPTION, managerMenu);
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildManagerMenu() {
        final Menu menu = new Menu("Manage eCourse Users");

        menu.addItem(SET_MANAGER_CREATE_USERS_OPTION, "Create Users",
                new RegisterUserUI()::show);
        menu.addItem(SET_MANAGER_ENABLE_USER_OPTION, "Activate User",
                new EnableUserUI()::show);
        menu.addItem(SET_MANAGER_DISABLE_USER_OPTION, "Disable User",
                new DisableUserUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "eCourse";
    }
}
