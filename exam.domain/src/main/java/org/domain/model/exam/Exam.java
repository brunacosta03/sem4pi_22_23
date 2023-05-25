package org.domain.model.exam;

import eapli.framework.domain.model.AggregateRoot;
import org.domain.model.examtemplate.domain.ExamTemplate;
import org.usermanagement.domain.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_EXAM")
public class Exam implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    private ExamTemplate examTemplate;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User student;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ResolutionSection> resolutionSections;
    private Double grade;

    protected Exam() {
        // for ORM
    }

    public Exam(
            ExamTemplate examTemplate,
            User student,
            List<ResolutionSection> resolutionSections,
            Double grade
    ) {
        this.examTemplate = examTemplate;
        this.student = student;
        this.resolutionSections = resolutionSections;
        this.grade = grade;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return id;
    }
}
