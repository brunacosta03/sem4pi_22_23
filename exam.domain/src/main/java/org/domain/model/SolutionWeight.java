package org.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SolutionWeight {

    @Column(name = "solution_weight", nullable = false)
    private final int value;


    protected SolutionWeight(){
        this.value = 0;
    }

    public SolutionWeight(int value) {
        // for ORM
        this.value = value;
    }

    public int value() {
        return value;
    }
}
