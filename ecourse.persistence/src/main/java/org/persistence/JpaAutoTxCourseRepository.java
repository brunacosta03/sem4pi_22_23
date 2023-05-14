package org.persistence;


import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.model.CourseStateConstants;
import org.domain.repositories.CourseRepository;
import org.usermanagement.domain.model.User;

import javax.persistence.TypedQuery;
import java.util.Map;
import java.util.Optional;

public class JpaAutoTxCourseRepository
        extends JpaAutoTxRepository<Course, CourseCode, CourseCode>
        implements CourseRepository {
    public JpaAutoTxCourseRepository(final String puname, final Map properties) {
        super(puname, properties, "code");
    }

    @Override
    public Course save(final Course course) {
        return this.repo.save(course);
    }

    @Override
    public Optional<Course> findByCode(final CourseCode code) {
        return this.repo.ofIdentity(code);
    }

    @Override
    public Iterable<Course> findCoursesThatITeach(User teacher) {
        final TypedQuery<Course> query = createQuery(
                "SELECT c FROM Course c JOIN c.teachers t WHERE t.email = :teacher_email",
                Course.class
        );

        query.setParameter("teacher_email", teacher.emailAddress());

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesThatILead(User teacher) {
        final TypedQuery<Course> query = createQuery(
                "SELECT c FROM Course c JOIN c.headTeacher ht WHERE ht.email = :teacher_email",
                Course.class
        );

        query.setParameter("teacher_email", teacher.emailAddress());

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findOpenForEnrolment() {
        final TypedQuery<Course> query = createQuery(
                "SELECT c FROM Course c WHERE c.state = :state",
                Course.class
        );

        query.setParameter("state", CourseStateConstants.ENROLL);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findCoursesTakenByStudent(User student) {
        // JPQL t_course_student is not being recognized
        final TypedQuery<Course> query = createQuery(
                "SELECT c FROM Course c JOIN c.students s WHERE s.email = :student_email",
                Course.class
        );


        query.setParameter("student_email", student.emailAddress());

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findClassesOfCourse(Course course) {
        final TypedQuery<Course> query = createQuery(
                "SELECT c FROM Course c JOIN c.classes cl WHERE cl.course = :course",
                Course.class
        );

        query.setParameter("course", course);

        return query.getResultList();
    }

    @Override
    public Iterable<Course> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Course> ofIdentity(CourseCode code) {
        return this.repo.ofIdentity(code);
    }

    @Override
    public void delete(Course entity) {

    }

    @Override
    public void deleteOfIdentity(CourseCode entityId) {

    }

    @Override
    public long count() {
        return 0;
    }
}
