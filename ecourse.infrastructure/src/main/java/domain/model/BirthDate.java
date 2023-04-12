package domain.model;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Embeddable
public final class BirthDate implements ValueObject {
    /**
     * Date when someone born.
     */
    @Column(name = "birthDate")
    private Date value;

    protected BirthDate() {
        value = null;
    }
    private BirthDate(final Date valuep) {
        this.value = valuep;
    }

    private BirthDate(final String valuep) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(valuep);
            this.value = date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static BirthDate valueOf(final String date) {
        return new BirthDate(date);
    }
}
