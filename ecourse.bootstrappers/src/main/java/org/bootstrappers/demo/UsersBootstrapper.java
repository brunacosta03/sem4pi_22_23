package org.bootstrappers.demo;

import eapli.framework.infrastructure.authz.domain.model.Role;
import org.bootstrappers.UsersBootstrapperBase;
import eapli.framework.actions.Action;
import org.user.management.CourseRoles;

import java.util.HashSet;
import java.util.Set;

public class UsersBootstrapper extends UsersBootstrapperBase implements Action {
    /**
     * Password for Manager.
     */
    private static final String PASSWORD_M = "passwordmanager";
    /**
     * Password for Teacher.
     */
    private static final String PASSWORD_T = "passwordteacher";
    /**
     * Password for Student.
     */
    private static final String PASSWORD_S = "passwordstudent";

    /**
     * Bootstraping (Manager, Teacher, Student).
     * @return
     */
    @Override
    public boolean execute() {
        registerManager("manager", PASSWORD_M, "Samuel",
                "Dias", "manager@email.com");
        registerTeacher("teacher", PASSWORD_T, "Henrique",
                "Pinto", "teacher@email.com");
        registerStudent("student1", PASSWORD_S, "Pedro",
                "Alves", "student1@email.com");
        registerStudent("student2", PASSWORD_S, "Bruna",
                "Costa", "student2@email.com");

        return true;
    }

    /**
     * Bootstrap to register a Manager.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     */
    private void registerManager(final String username, final String password,
                                 final String firstName, final String lastName,
                                 final String email) {
        final Set<Role> roles = new HashSet<>();

        roles.add(CourseRoles.MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    /**
     * Bootstrap to register a Teacher.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     */
    private void registerTeacher(final String username, final String password,
                                 final String firstName, final String lastName,
                                 final String email) {
        final Set<Role> roles = new HashSet<>();

        roles.add(CourseRoles.TEACHER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    /**
     * Bootstrap to register a Student.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     */
    private void registerStudent(final String username, final String password,
                                 final String firstName, final String lastName,
                                 final String email) {
        final Set<Role> roles = new HashSet<>();

        roles.add(CourseRoles.STUDENT);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
