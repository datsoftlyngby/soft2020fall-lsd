package dk.cphbusiness.koa.contract;

import dk.cphbusiness.koa.contract.dto.*;

import java.util.Collection;

public interface CourseManager {
    /**
     * List active categories for courses.
     * jdsfkls
     * @return all categories in system
     */
    Collection<CategorySummary> listCategories();

    /**
     *
     * @param criteria
     * @return
     */
    Collection<CourseSummary> listCourses(CourseCriteria criteria);

    /**
     *
     *
     * @param courseId
     * @return
     */
    CourseDetail findCourse(CourseIdentifier courseId);
    }
