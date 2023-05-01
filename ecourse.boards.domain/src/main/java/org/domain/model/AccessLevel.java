package org.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

/**
 * The type AccesLevel.
 */
@Embeddable
public class AccessLevel implements ValueObject {
    private static final long serialVersionUID = 1L;
    private final String accessLevel;

    private AccessLevel(final String accessLevelp) {
        Preconditions.nonEmpty(accessLevelp);
        this.accessLevel = accessLevelp;
    }

    protected AccessLevel() {
        this.accessLevel = null;
    }

    public String toString() {
        return this.accessLevel;
    }

    public static AccessLevel valueOf(final String accessLevelp) {
        return new AccessLevel(accessLevelp);
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AccessLevel)) {
            return false;
        } else {
            AccessLevel other = (AccessLevel)o;

            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$accessLevel = this.accessLevel;
                Object other$accessLevel = other.accessLevel;

                if (this$accessLevel == null) {
                    if (other$accessLevel != null) {
                        return false;
                    }
                } else if (!this$accessLevel.equals(other$accessLevel)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccessLevel;
    }
}
