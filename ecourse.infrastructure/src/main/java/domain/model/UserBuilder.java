package domain.model;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.validations.Preconditions;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;

public class UserBuilder implements DomainFactory<User> {
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
    private Calendar createdOn;

    private final PasswordPolicy policy;
    private final PasswordEncoder encoder;

    public UserBuilder(PasswordPolicy policy, PasswordEncoder encoder) {
        this.policy = policy;
        this.encoder = encoder;
    }


    public UserBuilder with(final String shortNamep,
                            final String passwordp,
                            final String fullNamep,
                            final String emailp,
                            final String rolep) {
        this.withShortName(shortNamep);
        this.withPassword(passwordp);
        this.withFullName(fullNamep);
        this.withEmail(emailp);
        this.withRole(rolep);
        return this;
    }

    public UserBuilder with(final String shortNamep,
                            final String passwordp,
                            final String fullNamep,
                            final String emailp,
                            final String birthDate,
                            final String rolep,
                            final String taxPayerNumberp) {
        this.withShortName(shortNamep);
        this.withPassword(passwordp);
        this.withFullName(fullNamep);
        this.withEmail(emailp);
        this.withBirthDate(birthDate);
        this.withRole(rolep);
        this.withTaxPayerNumber(taxPayerNumberp);
        return this;
    }

    public UserBuilder withShortName(final String shortNamep) {
        this.shortName = ShortName.of(shortNamep);
        return this;
    }

    public UserBuilder withShortName(final ShortName shortNamep) {
        this.shortName = shortNamep;
        return this;
    }

    public UserBuilder withPassword(final String rawPassword) {
        this.password = (Password)Password.encodedAndValid(rawPassword,
                this.policy, this.encoder).orElseThrow(IllegalArgumentException::new);
        return this;
    }

    public UserBuilder withPassword(final Password password) {
        Preconditions.nonNull(password);
        this.password = password;
        return this;
    }

    public UserBuilder withFullName(final String fullName) {
        this.fullName = FullName.of(fullName);
        return this;
    }

    public UserBuilder withFullName(final FullName fullNamep) {
        this.fullName = fullNamep;
        return this;
    }

    public UserBuilder withEmail(final String emailp) {
        this.email = EmailAddress.valueOf(emailp);
        return this;
    }

    public UserBuilder withEmail(final EmailAddress emailp) {
        this.email = emailp;
        return this;
    }

    public UserBuilder withRole(final String rolep) {
        this.role = Role.valueOf(rolep);
        return this;
    }
    public UserBuilder withRole(final Role rolep) {
        this.role = rolep;
        return this;
    }

    public UserBuilder withMecanographicNumber(final String numberMecp) {
        this.numberMec = MecanographicNumber.of(numberMecp);
        return this;
    }

    public UserBuilder withMecanographicNumber(final MecanographicNumber numberMecp) {
        this.numberMec = numberMecp;
        return this;
    }

    public UserBuilder createdOn(final Calendar createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public UserBuilder withBirthDate(final String birthDatep) {
        this.birthDate = BirthDate.of(birthDatep);
        return this;
    }

    public UserBuilder withBirthDate(final BirthDate birthDatep) {
        this.birthDate = birthDatep;
        return this;
    }

    public UserBuilder withTaxPayerNumber(final String taxPayerNumberp) {
        this.taxPayerNumber = TaxPayerNumber.of(taxPayerNumberp);
        return this;
    }

    public UserBuilder withTaxPayerNumber(final TaxPayerNumber taxPayerNumberp) {
        this.taxPayerNumber = taxPayerNumberp;
        return this;
    }

    public UserBuilder withAcronym(final String acronymp) {
        this.acronym = Acronym.of(acronymp);
        return this;
    }

    public UserBuilder withAcronym(final Acronym acronymp) {
        this.acronym = acronymp;
        return this;
    }

    @Override
    public User build() {
        User user;

        if (createdOn != null) {
            user = new User(shortName, fullName, password, email, role, birthDate, numberMec, taxPayerNumber, acronym, createdOn);
        } else {
            user = new User(shortName, fullName, password, email, role, birthDate, numberMec, taxPayerNumber, acronym);
        }

        return user;
    }
}
