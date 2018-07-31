package com.troyszc.firstHibernate;

import com.troyszc.firstHibernate.entity.Course;
import com.troyszc.firstHibernate.entity.Instructor;
import com.troyszc.firstHibernate.entity.InstructorDetail;
import com.troyszc.firstHibernate.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviews {

    public static void main (String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        //use session obj to save java obj
        try {
            //start transaction
            session.beginTransaction();

            //create course and reviews
            Course theCourse = new Course("How to stick shift 101");
            Review theReview1 = new Review("driving a  ND Miata is just awesome bro.");
            Review theReview2 = new Review("only way to prevent auto theft in US.");
            Review theReview3 = new Review("this course kinda sucks, go back to my 7 speed CVT.");

            //add reviews
            theCourse.addReview(theReview1);
            theCourse.addReview(theReview2);
            theCourse.addReview(theReview3);

            //save course
            session.save(theCourse);
            System.out.println("the course: " + theCourse);
            System.out.println("and it's reviews: " + theCourse.getReviews() + "\n");
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done: data saved to db.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
