//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.validations.Preconditions;
import java.io.Serializable;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Version;

import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
public final class Password implements ValueObject, Serializable {
    /**
     * Version to resolve conflicts
     */
    @Version
    private Long version;

    private static final long serialVersionUID = 1L;
    @Column(
            name = "password"
    )
    private final String value;

    protected Password() {
        this.value = null;
    }

    Password(final String password) {
        Preconditions.nonNull(password);
        this.value = password;
    }

    public static Optional<Password> encodedAndValid(final String rawPassword, final PasswordPolicy policy, final PasswordEncoder encoder) {
        Preconditions.noneNull(new Object[]{rawPassword, policy, encoder});
        return policy.isSatisfiedBy(rawPassword) ? Optional.of(new Password(encoder.encode(rawPassword))) : Optional.empty();
    }

    public String toString() {
        return "************";
    }

    String value() {
        return this.value;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Password)) {
            return false;
        } else {
            Password other = (Password)o;
            Object this$value = this.value;
            Object other$value = other.value;
            if (this$value == null) {
                if (other$value != null) {
                    return false;
                }
            } else if (!this$value.equals(other$value)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $value = this.value;
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }
}
