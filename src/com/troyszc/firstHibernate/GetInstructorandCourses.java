package com.troyszc.firstHibernate;

import com.troyszc.firstHibernate.entity.Course;
import com.troyszc.firstHibernate.entity.Instructor;
import com.troyszc.firstHibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorandCourses {

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

            //get instructor and corresponding courses
            Instructor theInstructor = session.get(Instructor.class, instructorId);
            System.out.println("The instructor retrieved is: " + theInstructor + "\n");
            System.out.println("The instructor's courses are: " + theInstructor.getCourseList() + "\n");

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done: retrieved DAO from db.");
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
