package com.troyszc.firstHibernate;

import com.troyszc.firstHibernate.entity.Course;
import com.troyszc.firstHibernate.entity.Instructor;
import com.troyszc.firstHibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourses {

    public static void main (String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        //use session obj to save java obj
        try {
            int instructorId = 1;

            //start transaction
            session.beginTransaction();

            //get Instructor
            Instructor theInstructor = session.get(Instructor.class, instructorId);

            //create courses
            Course course1 = new Course("track racing apex 101");
            Course course2 = new Course("rev matching, the next level shifting");
            Course course3 = new Course("over steer correction");

            //add courses to instructor
            theInstructor.addCourse(course1);
            theInstructor.addCourse(course2);
            theInstructor.addCourse(course3);

            //save courses
            session.save(course1);
            session.save(course2);
            session.save(course3);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done: saving courses to db.");
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
