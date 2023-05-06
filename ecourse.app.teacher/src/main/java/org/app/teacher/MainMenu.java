package org.app.teacher;

import boards.CreateBoardUI;
import courses.ListCoursesUI;
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

public class MainMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // TEACHER SUB-MENUS
    private static final int SET_TEACHER_MANAGE_COURSES_OPTION = 1;

    // TEACHER MANAGE COURSES
    private static final int SET_TEACHER_LIST_AVAILABLE_COURSES_OPTION = 1;

    //SHARED BOARD
    private static final int SET_USER_BOARD_OPTION = 9;
    private static final int SET_USER_CREATE_BOARD_OPTION = 1;

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

        if(authz.isAuthenticatedUserAuthorizedTo(CourseRoles.TEACHER)) {
            final Menu teacherMenu = buildTeacherMenu();
            final Menu boardMenu = buildBoardMenu();

            mainMenu.addSubMenu(SET_TEACHER_MANAGE_COURSES_OPTION, teacherMenu);
            mainMenu.addSubMenu(SET_USER_BOARD_OPTION, boardMenu);
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildTeacherMenu() {
        final Menu menu = new Menu("Manage eCourse Courses");

        menu.addItem(SET_TEACHER_LIST_AVAILABLE_COURSES_OPTION,
                "List Available Courses", new ListCoursesUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildBoardMenu() {
        final Menu menu = new Menu("Shared Boards");

        menu.addItem(SET_USER_CREATE_BOARD_OPTION, "Create Board",
                new CreateBoardUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "eCourse";
    }
}