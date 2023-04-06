package org.user.management;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.strings.util.StringPredicates;

public class ECoursePasswordPolicy implements PasswordPolicy {
    @Override
    public boolean isSatisfiedBy(String rawPassword) {
        //Empty string or null
        if (StringPredicates.isNullOrEmpty(rawPassword)) {
            return false;
        }

        return true;
    }
}
