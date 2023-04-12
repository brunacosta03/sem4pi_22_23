package domain.model;

import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TaxPayerNumber {

    @Column(name = "TAX_PAYER_NUMBER", nullable = false, unique = true, length = 9)
    private String value;

    private static final String REGEX_PORTUGUESE = "[0-9]{9}"; // 9 digits

    private TaxPayerNumber(String value) {
        Preconditions.nonNull(value, "TaxPayerNumber cannot be null");
        Preconditions.nonEmpty(value, "TaxPayerNumber cannot be empty");
        Preconditions.ensure(
                value.matches(REGEX_PORTUGUESE),
                "TaxPayerNumber must match the pattern " + REGEX_PORTUGUESE
        );

        this.value = value;
    }

    protected TaxPayerNumber() {
        // for ORM
    }

    public static TaxPayerNumber of(String value) {
        return new TaxPayerNumber(value);
    }

    String value(){
        return this.value;
    }
}
