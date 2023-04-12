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

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type Password.
 */
@Embeddable
public final class Password implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "PASSWORD", nullable = false)
    private final String value;

    /**
     * Instantiates a new Password.
     */
    protected Password() {
        this.value = null;
    }

    /**
     * Instantiates a new Password.
     *
     * @param password the password
     */
    Password(final String password) {
        Preconditions.nonNull(password);
        this.value = password;
    }

    /**
     * Encoded and valid optional.
     * Factory method for creating a new Password. (Encoded and Valid)
     *
     * @param rawPassword the raw password
     * @param policy      the policy
     * @param encoder     the encoder
     * @return the optional
     */
    public static Optional<Password> encodedAndValid
    (
            final String rawPassword,
            final PasswordPolicy policy,
            final PasswordEncoder encoder
    ) {
        Preconditions.noneNull(new Object[]{rawPassword, policy, encoder});

        return policy.isSatisfiedBy(rawPassword)
                ? Optional.of(new Password(encoder.encode(rawPassword)))
                : Optional.empty();
    }

    /**
     * To string.
     *
     *
     * @return fake password
     */
    public String toString() {
        return "************";
    }

    /**
     * Value string.
     *
     * @return the string
     */
    String value() {
        return this.value;
    }

    /**
     * Equals boolean.
     *
     * @param o the object password to compare
     * @return
     */
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

    /**
     * Hash code int.
     *
     * @return the int
     */
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $value = this.value;
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        return result;
    }
}
