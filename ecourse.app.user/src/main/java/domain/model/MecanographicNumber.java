package domain.model;

import javax.persistence.Version;
import java.time.LocalDateTime;

public class MecanographicNumber {

    /**
     * Version to resolve conflicts
     */
    @Version
    private Long version;

    public static final String GENERATE_QUERY = "SELECT MAX(value) FROM USER WHERE YEAR:=" + LocalDateTime.now().getYear() + ";";

    /**
     * Year of the generated number
     */
    private Integer year;
    /**
     * Value to be added to the string of the number, generated automatically
     */
    private Integer value;

    public MecanographicNumber(final Integer value){
        this.year = LocalDateTime.now().getYear();
        this.value = value;
    }


    public boolean equals(MecanographicNumber number){
        return this.year.equals(number.year) &&
                this.value.equals(number.value);
    }

    public String value(){
        return year.toString() + value.toString();
    }
}
