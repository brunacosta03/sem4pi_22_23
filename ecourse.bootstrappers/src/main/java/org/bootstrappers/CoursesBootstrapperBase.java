package org.bootstrappers;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.course.controller.AddCourseController;
import org.domain.model.Course;

public class CoursesBootstrapperBase {

    private final AddCourseController ctrl = new AddCourseController();
    public CoursesBootstrapperBase(){
        super();
    }

    /**
     * Register course for bootstrap.
     * @param name
     * @param code
     * @param edition
     * @param description
     * @param max
     * @param min
     * @param headTeacher
     * @return Course
     */
    protected Course addCourse(final String name,
                               final String code,
                               final String edition,
                               final String description,
                               final Integer max,
                               final Integer min,
                               final String headTeacher){
        Course course = null;

        try{
            course = ctrl.addCourse(name, code, edition, description, max, min, headTeacher);
            System.out.println("Course " + name + " added.");
        }catch (final IllegalArgumentException e){
            System.out.println(e.getMessage());
        }catch (final IntegrityViolationException | ConcurrencyException e){
            System.out.println("Already exists --> " + code);
        }
        return course;
    }
}
