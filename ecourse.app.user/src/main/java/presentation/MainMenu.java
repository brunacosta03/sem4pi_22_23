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
import presentation.boards.CreateBoardUI;
import presentation.usermanagement.DisableUserUI;
import presentation.usermanagement.EnableUserUI;
import presentation.usermanagement.ListUsersUI;
import presentation.usermanagement.RegisterUserUI;

public class MainMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // MANAGER SUB-MENUS
    private static final int SET_MANAGER_MANAGE_USERS_OPTION = 1;
    private static final int SET_MANAGER_MANAGE_COURSES_OPTION = 2;

    // TEACHER SUB-MENUS
    private static final int SET_TEACHER_MANAGE_COURSES_OPTION = 1;

    // STUDENT SUB-MENUS
    private static final int SET_STUDENT_MANAGE_COURSES_OPTION = 1;

    //MANAGER MANAGE USER's
    private static final int SET_MANAGER_CREATE_USERS_OPTION = 1;
    private static final int SET_MANAGER_ENABLE_USER_OPTION = 2;
    private static final int SET_MANAGER_DISABLE_USER_OPTION = 3;
    private static final int SET_MANAGER_LIST_USERS_OPTION = 4;

    // MANAGER MANAGE COURSES
    private static final int SET_MANAGER_LIST_AVAILABLE_COURSES_OPTION = 1;

    // TEACHER MANAGE COURSES
    private static final int SET_TEACHER_LIST_AVAILABLE_COURSES_OPTION = 1;

    // STUDENT MANAGE COURSES
    private static final int SET_STUDENT_LIST_AVAILABLE_COURSES_OPTION = 1;
    private static final int SET_STUDENT_ENROLL_COURSE_OPTION = 2;

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

        if (authz.isAuthenticatedUserAuthorizedTo(CourseRoles.MANAGER)) {
            final Menu managerMenu = buildManagerMenu();
            final Menu managerManageCoursesMenu = buildManagerManageCoursesMenu();
            mainMenu.addSubMenu(SET_MANAGER_MANAGE_USERS_OPTION, managerMenu);
            mainMenu.addSubMenu(SET_MANAGER_MANAGE_COURSES_OPTION, managerManageCoursesMenu);
        }else if(authz.isAuthenticatedUserAuthorizedTo(CourseRoles.TEACHER)) {
            final Menu teacherMenu = buildTeacherMenu();
            mainMenu
                    .addSubMenu(SET_TEACHER_MANAGE_COURSES_OPTION, teacherMenu);
        }else if (authz.isAuthenticatedUserAuthorizedTo(CourseRoles.STUDENT)) {
            final Menu studentMenu = buildStudentMenu();
            mainMenu.addSubMenu(SET_STUDENT_MANAGE_COURSES_OPTION, studentMenu);
        }

        final Menu boardMenu = buildBoardMenu();
        mainMenu.addSubMenu(SET_USER_BOARD_OPTION, boardMenu);

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildManagerManageCoursesMenu() {
        final Menu menu = new Menu("Manage eCourse Courses");

        menu.addItem(SET_MANAGER_LIST_AVAILABLE_COURSES_OPTION,
                "List Available Courses", new ListCoursesUI()::doShow);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildStudentMenu() {
        final Menu menu = new Menu("Manage eCourse Courses");

        menu.addItem(SET_STUDENT_LIST_AVAILABLE_COURSES_OPTION,
                "List Available Courses", new ListCoursesUI()::doShow);
        menu.addItem(
                SET_STUDENT_ENROLL_COURSE_OPTION,
                "Request Enrollment in a Course",
                new RequestEnrollmentUI()::doShow
        );
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildManagerMenu() {
        final Menu menu = new Menu("Manage eCourse Users");

        menu.addItem(SET_MANAGER_CREATE_USERS_OPTION, "Create Users",
                new RegisterUserUI()::show);
        menu.addItem(SET_MANAGER_ENABLE_USER_OPTION, "Enable User",
                new EnableUserUI()::show);
        menu.addItem(SET_MANAGER_DISABLE_USER_OPTION, "Disable User",
                new DisableUserUI()::show);
        menu.addItem(SET_MANAGER_LIST_USERS_OPTION, "List Users",
                new ListUsersUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildTeacherMenu() {
        final Menu menu = new Menu("Manage eCourse Courses");

        menu.addItem(SET_TEACHER_LIST_AVAILABLE_COURSES_OPTION,
                "List Available Courses", new ListCoursesUI()::doShow);
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
