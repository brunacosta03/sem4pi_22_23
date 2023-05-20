package org.domain.model;

import eapli.framework.validations.Preconditions;
import org.usermanagement.domain.model.User;

import java.util.Set;

public class ExamBuilder {

    private String title;

    private String header;

    private String startDate;

    private String endDate;

    private Set<User> students;

    private User teacher;

    private Course course;

    public ExamBuilder withCourse(Course course) {
        this.course = course;
        return this;
    }

    public ExamBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ExamBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public ExamBuilder withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public ExamBuilder withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public ExamBuilder withStudents(Set<User> students) {
        this.students = students;
        return this;
    }

    public ExamBuilder withTeacher(User teacher) {
        this.teacher = teacher;
        return this;
    }

    public ExamTemplate build() {
        Preconditions.noneNull(title, "Exam title cannot be null");
        Preconditions.noneNull(header, "Exam header cannot be null");
        Preconditions.noneNull(startDate, "Exam start date cannot be null");
        Preconditions.noneNull(endDate, "Exam end date cannot be null");
        Preconditions.noneNull(students, "Exam students cannot be null");
        Preconditions.noneNull(teacher, "Teacher does not exist");
        return new ExamTemplate(course, title, header, startDate, endDate, teacher, students);
    }

}
