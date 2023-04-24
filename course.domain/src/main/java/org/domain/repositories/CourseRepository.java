package org.domain.repositories;


import eapli.framework.domain.repositories.DomainRepository;
import org.domain.model.Course;
import org.domain.model.CourseCode;
import org.domain.model.CourseEdition;

import java.util.Optional;

public interface CourseRepository extends DomainRepository<CourseCode, Course> {

    /**
     * Persist course.
     * @param course
     * @return Course
     */
    Course save(Course course);

    /**
     * Get Course with Identity (Edition).
     * @param id CourseEdition
     * @return Course
     */
    Optional<Course> ofIdentity(CourseCode id);

    /**
     * Get Course with Edition.
     * @param code
     * @return Course
     */
    default Optional<Course> findByCode(
            final CourseCode code) {
        return ofIdentity(code);
    }


}
