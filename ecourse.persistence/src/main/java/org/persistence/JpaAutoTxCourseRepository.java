package org.persistence;


import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.repositories.CourseRepository;

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
        return this.repo.ofIdentity(code);
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
