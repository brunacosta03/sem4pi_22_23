package org.persistence;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.model.CourseEdition;
import org.domain.repositories.CourseRepository;
import org.usermanagement.domain.model.User;
import org.usermanagement.domain.repositories.UserRepository;

import java.util.Map;
import java.util.Optional;

public class JpaAutoTxCourseRepository
        extends JpaAutoTxRepository<Course, CourseCode, CourseCode> implements CourseRepository {
    public JpaAutoTxCourseRepository(final String puname, final Map properties) {
        super(puname, properties, "code");
    }

    @Override
    public Course save(final Course course) {
        return this.repo.save(course);
    }

    @Override
    public Optional<Course> findByCode(final CourseCode code) {
        return this.repo.findById(code);
    }

    @Override
    public Iterable<Course> findAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Course> ofIdentity(CourseCode id) {
        return Optional.empty();
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
