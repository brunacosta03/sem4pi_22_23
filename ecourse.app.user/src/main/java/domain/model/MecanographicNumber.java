package domain.model;

import java.time.LocalDateTime;

public class MecanographicNumber {
    private int value;

    public MecanographicNumber(final int value){
        if(confirmNumber(value)){
            this.value = value;
        }
    }

    public boolean confirmNumber(final int value){
        int yearSearcher = value;
        while (yearSearcher >=1000 && yearSearcher < 10000){
            yearSearcher = yearSearcher / 10;
        }
        if(yearSearcher >= LocalDateTime.now().getYear()){
            throw new IllegalArgumentException("This number is not correct");
        }
        return true;
    }

    public boolean equals(MecanographicNumber number){
        if(this == number){
            return true;
        }
        return false;
    }
}
