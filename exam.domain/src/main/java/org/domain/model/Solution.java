package org.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Solution {
    @Column(name = "solution_weight", nullable = false)
    private SolutionWeight weight;
    @Column(name = "solution_description", nullable = false)
    private SolutionDescription description;

    protected Solution(){
        // for ORM
        this.weight = null;
        this.description = null;
    }

    public Solution(SolutionWeight weight, SolutionDescription description) {
        this.weight = weight;
        this.description = description;
    }


}
