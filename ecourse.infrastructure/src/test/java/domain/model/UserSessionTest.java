package domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class UserSessionTest {

    UserSession userSession;

    @Mock
    User user = new User();

    @BeforeEach
    void setUp() {
        userSession = new UserSession(user);
    }

    @Test
    void testToString() {
        userSession
                .toString();
    }

    @Test
    void authenticatedUser() {
        User authUser = userSession.authenticatedUser();

        assertEquals(user, authUser);
    }
}