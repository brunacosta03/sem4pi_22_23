package domain.model;

import javax.persistence.Version;
import java.util.Date;

public final class BirthDate {
    /**
     * Version to resolve conflicts
     */
    @Version
    private Long version;
    /**
     * Date when someone born.
     */
    private Date value;
    private BirthDate(final Date valuep) {
        this.value = valuep;
    }

}
