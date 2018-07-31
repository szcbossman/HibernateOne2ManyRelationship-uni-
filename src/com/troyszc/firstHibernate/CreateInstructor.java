package com.troyszc.firstHibernate;

import com.troyszc.firstHibernate.entity.Course;
import com.troyszc.firstHibernate.entity.Instructor;
import com.troyszc.firstHibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {

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
            //create DAO obj
            Instructor theInstructor = new Instructor("Troy", "Song", "troysong33@gmail.com");
            InstructorDetail theDetail = new InstructorDetail("youtube/szcbossmanCC", "racing");
            theInstructor.setInstructorDetail(theDetail);

            //start transaction
            session.beginTransaction();

            //save DAO obj
            session.save(theInstructor);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done: saving DAO to db.");
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
