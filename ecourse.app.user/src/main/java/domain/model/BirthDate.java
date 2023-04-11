package domain.model;

import java.util.Date;

public final class BirthDate {
    /**
     * Date when someone born.
     */
    private Date value;
    private BirthDate(final Date valuep) {
        this.value = valuep;
    }

}
