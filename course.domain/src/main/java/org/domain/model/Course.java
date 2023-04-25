package org.domain.model;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import org.user.management.CourseRoles;
import org.usermanagement.domain.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "T_COURSE")
public class Course implements AggregateRoot<CourseCode> {
    /**
     * Version of course;
     */
    @Version
    private Long version;

    /**
     * Name of the course.
     */
    private CourseName name;

    /**
     * Code of the course.
     */
    @EmbeddedId
    private CourseCode code;

    /**
     * Edition of the course.
     */
    @Column(unique = true)
    private CourseEdition edition;

    /**
     * Description of the course.
     */
    private CourseDescription description;

    /**
     * State of the course(Open, closed, enroll or in progress)
     */
    private CourseState state;

    /**
     * Maximum number of people who can enroll in the course.
     */
    private CourseMaxNumberLimit max;

    /**
     * Minimum number of people who have to enroll in the course.
     */
    private CourseMinNumberLimit min;

    /**
     * The head teacher for the course
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private User headTeacher;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "t_course_teacher",joinColumns =
    @JoinColumn(name = "course_code"), inverseJoinColumns =
    @JoinColumn(name = "teacher_email"))
    private Set<User> teachers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "t_course_student",joinColumns =
    @JoinColumn(name = "course_code"), inverseJoinColumns =
    @JoinColumn(name = "student_email"))
    private Set<User> students;

    public Course(final CourseName name,
           final CourseCode code,
           final CourseEdition edition,
           final CourseDescription description,
           final CourseState state,
           final CourseMaxNumberLimit max,
           final CourseMinNumberLimit min,
           final User headTeacher,
                  final Set<User> teachers,
                  final Set<User> students){

        necessaryParameters(name, code, edition, max, headTeacher);
        validateMaxMin(max, min);

        this.name = name;
        this.code = code;
        this.edition = edition;
        this.description = description;
        this.state = state;
        this.max = max;
        this.min = min;
        this.headTeacher = headTeacher;
        this.teachers = teachers;
        this.students = students;
    }

    protected Course() {
    }

    private void necessaryParameters(
            final CourseName name,
            final CourseCode code,
            final CourseEdition edition,
            final CourseMaxNumberLimit max,
            final User headTeacher){
        Preconditions.nonNull(name, "Name of te course cannot be null.");
        Preconditions.nonNull(code, "Code of the course can't be null.");
        Preconditions.nonNull(edition, "Edition of the course can't be null.");
        Preconditions.nonNull(max, "The maximum number of students can't be 0.");
        Preconditions.nonNull(headTeacher, "The course must have a head teacher.");
        Preconditions.ensure(headTeacher.role().equals(CourseRoles.TEACHER.toString()));
    }
    private void validateMaxMin(CourseMaxNumberLimit max, CourseMinNumberLimit min){
        int value = max.value()-min.value();

        if(value < 0){
            throw new IllegalArgumentException("The maximum quantity of students can't be lower than the minimum quantity of students");
        }
        if(value == 0){
            throw new IllegalArgumentException("There can't be a defined number of students. There must be some interval of numbers");
        }
    }

    @Override
    public boolean sameAs(Object other) {
        if(other instanceof Course){
            if(this.edition.value().equals(((Course) other).identity().value())){
                return true;
            }
            return this.code.value().equals(((Course) other).identity().value());
        }
        return false;
    }

    @Override
    public CourseCode identity() {
        return code;
    }

    public void addStudent(User student){
        Preconditions.ensure(
                student.role().equals(CourseRoles.STUDENT.toString()),
                "Only students can be assigned through this option");
        students.add(student);
    }

    public void removeStudent(User student){
        Preconditions.ensure(
                students.contains(student),
                "This student is not enrolled in this course");
        students.remove(student);
    }

    public void addTeacher(User teacher){
        Preconditions.ensure(
                teacher.role().equals(CourseRoles.TEACHER.toString()),
                "Only teachers can be assigned through this option");
        teachers.add(teacher);
    }

    public void removeTeacher(User teacher){
        Preconditions.ensure(
                teachers.contains(teacher),
                "This teacher is not enrolled in this course");
        teachers.remove(teacher);
    }
}
