package org.domain.model;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import org.usermanagement.domain.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_EXAM")
public class ExamTemplate implements AggregateRoot<ExamTitle> {


    /**
     * Title of the exam.
     */
    @EmbeddedId
    @Column(unique = true)
    private ExamTitle title;

    /**
     * Description of the exam.
     */
    private ExamHeader header;

    private ExamDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "t_exam_teacher",joinColumns =
    @JoinColumn(name = "exam_title"), inverseJoinColumns =
    @JoinColumn(name = "teacher_email"))
    private final User teacher;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "t_exam_student", joinColumns =
    @JoinColumn(name = "exam_title"), inverseJoinColumns =
    @JoinColumn(name = "student_email"))
    private Set<User> students = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;


    /**
     * Constructor for ORM.
     */
    protected ExamTemplate() {
        // for ORM only
        title = null;
        header = null;
        date = null;
        teacher = null;
    }

    private ExamTemplate(final Course course, final ExamTitle title, final ExamHeader description,
                         final ExamDate date, final User teacher,
                         final Set<User> students) {
        Preconditions.noneNull(course, title, description, date, teacher, students);

        this.course = course;
        this.title = title;
        this.header = description;
        this.date = date;
        this.teacher = teacher;
        this.students.addAll(students);
    }

    public ExamTitle title() {
        return title;
    }

    public ExamHeader header() {
        return header;
    }

    public ExamDate date() {
        return date;
    }

    public String formattedStartDate() {
        return date.valueStartDate();
    }

    public String formattedEndDate() {
        return date.valueEndDate();
    }

    protected ExamTemplate(final Course course, final String title, final String description,
                           final String startDate, final String endDate, final User teacher,
                           final Set<User> students) {
        this(course, ExamTitle.of(title), ExamHeader.of(description), ExamDate.of(startDate, endDate), teacher, students);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public ExamTitle identity() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamTemplate)) return false;
        ExamTemplate that = (ExamTemplate) o;
        if (!title.equals(that.title)) return false;
        if (!header.equals(that.header)) return false;
        return date.equals(that.date);
    }

    public boolean overlaps(ExamTemplate examTemplate) {
        return this.title.equals(examTemplate.title) && this.date.overlaps(examTemplate.date);
    }

    @Override
    public String toString() {
        return "title: " + title +
                " || " + date +
                 '\n';
    }
}
