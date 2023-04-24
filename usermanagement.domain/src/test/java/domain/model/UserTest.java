package domain.model;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.*;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final PasswordEncoder ENCODER = new PlainTextEncoder();
    private static final PasswordPolicy POLICY = new ECoursePasswordPolicy();


    // mocks
    private static final String STRING_SHORTNAME = "shortName";
    private static final ShortName SHORTNAME = ShortName.of(STRING_SHORTNAME);
    private static final String STRING_FULLNAME = "fullName";

    private static final FullName FULLNAME = FullName.of(STRING_FULLNAME);
    private static final String STRING_EMAIL = "email@email.com";

    private static final EmailAddress EMAIL = EmailAddress.valueOf(STRING_EMAIL);
    private static final String STRING_ROLE = String.valueOf(CourseRoles.MANAGER);
    private static final Role ROLE = CourseRoles.MANAGER;


    private static final String STRING_PASS = "Correct5";
    private static final String STRING_PASS2 = "CorrectPass2";
    private static final Password PASSWORD = Password.encodedAndValid(STRING_PASS, POLICY, ENCODER).get();

    private static final Password PASSWORD2 = Password.encodedAndValid(STRING_PASS2, POLICY, ENCODER).get();
    private static final Calendar CREATED_ON = Calendar.getInstance();
    private static final Calendar DEACTIVATED_ON = Calendar.getInstance();

    private static final String STRING_ROLE_TWO = "ROLE_TWO";
    private static final Role ROLE_TWO = Role.valueOf(STRING_ROLE_TWO);
    private static final Role[] ROLES = {
            ROLE,
            ROLE_TWO
    };

    UserBuilder builder = new UserBuilder(POLICY, ENCODER);

    @Test
    void ensureUserHasShortNameWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                        null,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE
                ).build()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                        null,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                        .createdOn(CREATED_ON)
                        .build()
        );
    }

    @Test
    void ensureUserHasFullNameWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                PASSWORD,
                                null,
                                EMAIL,
                                ROLE)
                        .build()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                PASSWORD,
                                null,
                                EMAIL,
                                ROLE)
                        .createdOn(CREATED_ON)
                        .build()
        );
    }

    @Test
    void ensureUserHasPasswordWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                null,
                                FULLNAME,
                                EMAIL,
                                ROLE)
                        .build()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                null,
                                FULLNAME,
                                EMAIL,
                                ROLE)
                        .createdOn(CREATED_ON)
                        .build()
        );
    }

    @Test
    void ensureUserHasEmailWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                PASSWORD,
                                FULLNAME,
                                null,
                                ROLE)
                        .build()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                PASSWORD,
                                FULLNAME,
                                null,
                                ROLE)
                        .createdOn(CREATED_ON)
                        .build()
        );
    }

    @Test
    void ensureUserHasRoleWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                PASSWORD,
                                FULLNAME,
                                EMAIL,
                                null)
                        .build()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> builder.with(
                                SHORTNAME,
                                PASSWORD,
                                FULLNAME,
                                EMAIL,
                                null)
                        .createdOn(CREATED_ON)
                        .build()
        );
    }

    @Test
    void ensureUserHasCreatedOnWhenCreated() {
        User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .build();

        assertEquals(Calendar.getInstance(), user.createdOn());
    }

    @Test
    void ensureUserCanBeCreatedWhenValid() {
        User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        assertTrue(user.isActive());
        assertTrue(user.passwordMatches(STRING_PASS, ENCODER));
        assertTrue(user.hasAnyOf(ROLES));
    }

    @Test
    void ensureCantChangeToNullPassword(){
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> user.changePassword(null));
    }

    @Test
    void ensureCanChangeToValidPassword(){
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        user.changePassword(PASSWORD2);

        assertEquals(PASSWORD2, user.password());
    }

    @Test
    void ensureReturnsCorrectIdentity(){
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        assertEquals(EMAIL, user.identity());
        assertEquals(EMAIL, user.emailAddress());
    }

    @Test
    void testSameAs() {
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();
        final User user2 = user;

        assertTrue(user.sameAs(user2));

        final User user3 = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        assertFalse(user.sameAs(user3));
    }

    @Test
    void ensureNotPossibleToDeactivateDeactivetedUser() {
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        user.disable(DEACTIVATED_ON);

        assertThrows(IllegalStateException.class,
                () -> user.disable(DEACTIVATED_ON));

    }

    @Test
    void ensureNotPossibleToActivateActiveUser() {
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        assertThrows(IllegalStateException.class,
                () -> user.enable());
    }

    @Test
    void ensureUserCanBeDeactivated() {
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        user.disable(DEACTIVATED_ON);

        assertFalse(user.isActive());
    }

    @Test
    void ensureUserCanBeActivated() {
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        user.disable(DEACTIVATED_ON);
        user.enable();

        assertTrue(user.isActive());
    }

    @Test
    void ensureUserHasCorrectRoles() {
        final User user = builder.with(
                        SHORTNAME,
                        PASSWORD,
                        FULLNAME,
                        EMAIL,
                        ROLE)
                .createdOn(CREATED_ON)
                .build();

        assertEquals(STRING_ROLE, user.role());
    }
}