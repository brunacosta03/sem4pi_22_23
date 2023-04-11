package org.user.management;

import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.util.Utility;

@Utility
public final class UserBuilderHelper {
    private UserBuilderHelper() {
    }

    /**
     * @return SystemUserBuilder
     */
    public static SystemUserBuilder builder() {
        return new SystemUserBuilder(new ECoursePasswordPolicy(),
                new PlainTextEncoder());
    }
}
