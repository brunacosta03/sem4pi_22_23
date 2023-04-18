package org.usermanagement.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.representations.dto.GeneralDTO;
import eapli.framework.time.util.CurrentTimeCalendars;
import eapli.framework.validations.Preconditions;
import eapli.framework.visitor.Visitable;
import eapli.framework.visitor.Visitor;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

@Entity
@Table(name = "T_COURSEUSER")
public class User
        implements AggregateRoot<EmailAddress>, DTOable<GeneralDTO>,
        Visitable<GeneralDTO>,
        Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Version of user.
     */
    @Version
    private Long version;
    /**
     * Short name of user.
     */
    private ShortName shortName;
    /**
     * Full name of user.
     */
    private FullName fullName;
    /**
     * Password of user.
     */
    private Password password;
    /**
     * Email of user.
     */
    @EmbeddedId
    private EmailAddress email;

    /**
     * Role of user.
     */
    private Role role;

    /**
     * Number Mecanographic only for students.
     */
    private MecanographicNumber numberMec;

    /**
     * Birthdate of user.
     */
    private BirthDate birthDate;

    /**
     * TaxPayerNumber of user.
     */
    private TaxPayerNumber taxPayerNumber;

    /**
     * Acronym of user.
     */
    private Acronym acronym;

    /**
     * Active or Desactive user.
     */
    private boolean userState;

    /**
     * Date when user got created in app.
     */
    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    /**
     * Date when user got deactivated in app.
     */
    @Temporal(TemporalType.DATE)
    private Calendar deactivatedOn;

    /**
     * Reset token to password.
     */
    private String resetToken;

    protected User() {

    }

    public User(final ShortName shortNamep,
                final FullName fullNamep,
                final Password passwordp,
                final EmailAddress emailp,
                final Role rolep,
                final BirthDate birthDatep,
                final MecanographicNumber numberMecp,
                final TaxPayerNumber taxPayNumberp,
                final Acronym acronymp) {
        necessaryParameters(shortNamep, fullNamep, emailp, rolep, passwordp);
        this.shortName = shortNamep;
        this.fullName = fullNamep;
        this.password = passwordp;
        this.email = emailp;
        this.role = rolep;
        this.birthDate = birthDatep;
        this.numberMec = numberMecp;
        this.taxPayerNumber = taxPayNumberp;
        this.acronym = acronymp;
        this.userState = true;
        this.createdOn = CurrentTimeCalendars.now();
    }

    private void necessaryParameters(
            final ShortName shortNamep,
            final FullName fullNamep,
            final EmailAddress emailp,
            final Role rolep,
            final Password passwordp
    ) {
        Preconditions.nonNull(shortNamep, "Short name cannot be null");
        Preconditions.nonNull(fullNamep, "Full name cannot be null");
        Preconditions.nonNull(emailp, "Email cannot be null");
        Preconditions.nonNull(rolep, "Role cannot be null");
        Preconditions.nonNull(passwordp, "Password cannot be null");
    }

    public User(final ShortName shortNamep,
                final FullName fullNamep,
                final Password passwordp,
                final EmailAddress emailp,
                final Role rolep,
                final BirthDate birthDatep,
                final MecanographicNumber numberMecp,
                final TaxPayerNumber taxPayNumberp,
                final Acronym acronymp,
                final Calendar createdOnp) {
        necessaryParameters(
                shortNamep,
                fullNamep,
                emailp,
                rolep,
                passwordp,
                createdOnp
        );
        this.shortName = shortNamep;
        this.fullName = fullNamep;
        this.password = passwordp;
        this.email = emailp;
        this.role = rolep;
        this.birthDate = birthDatep;
        this.numberMec = numberMecp;
        this.taxPayerNumber = taxPayNumberp;
        this.acronym = acronymp;
        this.userState = true;
        this.createdOn = createdOnp;
    }

    private void necessaryParameters(
            final ShortName shortNamep,
            final FullName fullNamep,
            final EmailAddress emailp,
            final Role rolep,
            final Password passwordp,
            final Calendar createdOnp
    ) {
        Preconditions.nonNull(shortNamep, "Short name cannot be null");
        Preconditions.nonNull(fullNamep, "Full name cannot be null");
        Preconditions.nonNull(emailp, "Email cannot be null");
        Preconditions.nonNull(rolep, "Role cannot be null");
        Preconditions.nonNull(passwordp, "Password cannot be null");
        Preconditions.nonNull(createdOnp, "CreatedOn cannot be null");
    }


    /**
     * Deactivates the user.
     * @param now
     * @throws IllegalStateException user is already deactivated.
     */
    public void deactivate(final Calendar now) {
        if (!this.userState) {
            throw new IllegalStateException("Cannot deactivate "
                    + "an already deactivated user.");
        }
        this.deactivatedOn = CurrentTimeCalendars.now();
        this.userState = false;
    }

    /**
     * Activates the user.
     * @throws IllegalArgumentException user is already active.
     */
    public void activate() {
        if (this.userState) {
            throw new IllegalStateException("Cannot activate "
                    + "an already active user.");
        }
        this.deactivatedOn = null;
        this.userState = true;
    }

    /**
     * Check if password match.
     * @param rawPassword
     * @param encoder
     * @return true/false
     */
    public boolean passwordMatches(final String rawPassword,
                                   final PasswordEncoder encoder) {
        return encoder.matches(rawPassword, password.value());
    }

    /**
     * Get if user is Active.
     * @return userState
     */
    public boolean isActive() {
        return userState;
    }

    /**
     * Check if user has any of Roles.
     * @param requiredRoles array of roles
     * @return have role return true
     */
    public boolean hasAnyOf(final Role[] requiredRoles) {
        return Arrays.asList(requiredRoles).contains(this.role);
    }

    /**
     * Change user password.
     * @param newPasswordp new user password
     */
    public void changePassword(final Password newPasswordp) {
        Preconditions.nonNull(newPasswordp, "Password cannot be null.");
        password = newPasswordp;
    }

    /**
     * User indentity is email.
     * @return user identity
     */
    public EmailAddress identity() {
        return email;
    }

    /**
     * Get email address.
     * @return EmailAddress
     */
    public EmailAddress emailAddress() {
        return this.email;
    }

    /**
     * Check if some User is the same object then other.
     * @param other
     * @return true/false
     */
    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof User)) {
            return false;
        } else {
            User that = (User) other;

            if (this == that) {
                return true;
            } else if (this.shortName.equals(that.shortName)
                    && this.fullName.equals(that.fullName)
                    && this.password.equals(that.password)
                    && this.email.equals(that.email)
                    && !this.role.equals(that.role)
                    && this.numberMec.equals(that.numberMec)
                    && this.birthDate.equals(that.birthDate)) {
                return this.resetToken == null
                        ? that.resetToken == null
                        : this.resetToken.equals(that.resetToken);
            } else {
                return false;
            }
        }
    }

    /**
     * Parse User to DTO.
     * @return GeneralDTO
     */
    @Override
    public GeneralDTO toDTO() {
        final GeneralDTO ret = new GeneralDTO("user");

        ret.put("shortName", shortName.toString());
        ret.put("fullName", fullName.toString());
        ret.put("email", email.toString());
        ret.put("role", role.toString());

        return ret;
    }

    /**
     * Accept method.
     * @param visitor
     */
    @Override
    public void accept(final Visitor<GeneralDTO> visitor) {
        visitor.visit(toDTO());
    }

    /**
     * Get role of User.
     * @return String
     */
    public String role() {
        return role.toString();
    }
}
