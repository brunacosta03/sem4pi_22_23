package domain.model;

import eapli.framework.validations.Preconditions;

public class FullName {
    private String value;

    private FullName(String value){
        this.value = value;
    }

    public static FullName valueOf(String value){
        Preconditions.nonEmpty(value, "Full Name can't be empty.");
        Preconditions.ensure(value.length()>3, "Full name must have 3 characters or more");
        return new FullName(value);
    }
}
