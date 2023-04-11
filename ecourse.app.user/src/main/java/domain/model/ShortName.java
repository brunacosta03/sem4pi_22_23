package domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class ShortName implements ValueObject {

    private String value;

    private ShortName(String value){
        this.value = value;
    }

    public static ShortName valueOf(String value){
        Preconditions.nonEmpty(value, "Short Name can't be empty.");
        Preconditions.ensure(value.length()>3, "Short name must have 3 characters or more");
        return new ShortName(value);
    }
}
