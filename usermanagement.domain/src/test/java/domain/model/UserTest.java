/*
package domain.model;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private static final String STRING_ROLE = "role";
    private static final Role ROLE = Role.valueOf(STRING_ROLE);


    private static final String STRING_PASS = "Correct5";
    private static final Password PASSWORD = Password.encodedAndValid(STRING_PASS, POLICY, ENCODER).get();

    private static final Calendar CREATED_ON = Calendar.getInstance();
    private static final Calendar DEACTIVATED_ON = Calendar.getInstance();

    private static final String STRING_ROLE_TWO = "ROLE_TWO";
    private static final Role ROLE_TWO = Role.valueOf(STRING_ROLE_TWO);
    private static final Role[] ROLES = {
            ROLE,
            ROLE_TWO
    };

    @Test
    void ensureUserHasShortNameWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(
                        null,
                        FULLNAME,
                        PASSWORD,
                        EMAIL,
                        ROLE,
                        null,
                        null,
                        null,
                        null
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new User(null, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON)
        );
    }

    @Test
    void ensureUserHasFullNameWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, null, PASSWORD, EMAIL, ROLE, null, null, null, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, null, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON)
        );
    }

    @Test
    void ensureUserHasPasswordWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, null, EMAIL, ROLE, null, null, null, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, null, EMAIL, ROLE, null, null, null, null, CREATED_ON)
        );
    }

    @Test
    void ensureUserHasEmailWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, PASSWORD, null, ROLE, null, null, null, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, PASSWORD, null, ROLE, null, null, null, null, CREATED_ON)
        );
    }

    @Test
    void ensureUserHasRoleWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, null, null, null, null, null)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, null, null, null, null, null, CREATED_ON)
        );
    }

    @Test
    void ensureUserHasCreatedOnWhenCreated() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, null)
        );
    }

    @Test
    void ensureUserCanBeCreatedWhenValid() {
        User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);

        assertTrue(user.isActive());
        assertTrue(user.passwordMatches(STRING_PASS, ENCODER));
        assertTrue(user.hasAnyOf(ROLES));

        user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null);

        assertTrue(user.isActive());
        assertTrue(user.passwordMatches(STRING_PASS, ENCODER));
        assertTrue(user.hasAnyOf(ROLES));
    }

    @Test
    void ensureCantChangeToNullPassword(){
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        assertThrows(IllegalArgumentException.class, () -> user.changePassword(null));
    }

    @Test
    void ensureCanChangeToValidPassword(){
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        user.changePassword(PASSWORD);
    }

    @Test
    void ensureReturnsCorrectIdentity(){
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        assertEquals(EMAIL, user.identity());
        assertEquals(EMAIL, user.emailAddress());
    }

    @Test
    void testSameAs() {
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        final User user2 = user;
        assertTrue(user.sameAs(user2));

        final User user3 = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);

        assertFalse(user.sameAs(user3));
    }

    @Test
    void ensureNotPossibleToDeactivateDeactivetedUser() {
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        user.deactivate(DEACTIVATED_ON);

        assertThrows(IllegalStateException.class, () -> user.deactivate(DEACTIVATED_ON));

    }

    @Test
    void ensureNotPossibleToActivateActiveUser() {
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        assertThrows(IllegalStateException.class, () -> user.activate());
    }

    @Test
    void ensureUserCanBeDeactivated() {
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        user.deactivate(DEACTIVATED_ON);
        assertFalse(user.isActive());
    }

    @Test
    void ensureUserCanBeActivated() {
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        user.deactivate(DEACTIVATED_ON);
        user.activate();
        assertTrue(user.isActive());
    }

    @Test
    void ensureUserHasCorrectRoles() {
        final User user = new User(SHORTNAME, FULLNAME, PASSWORD, EMAIL, ROLE, null, null, null, null, CREATED_ON);
        assertEquals(STRING_ROLE, user.role());
    }
}

*/