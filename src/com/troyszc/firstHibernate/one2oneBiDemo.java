package com.troyszc.firstHibernate;

import com.troyszc.firstHibernate.entity.Instructor;
import com.troyszc.firstHibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class one2oneBiDemo {

    public static void main (String[] args) {

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        //use session obj to save java obj
        try {
            session.beginTransaction();

            int theDetailId = 1;
            InstructorDetail theDetail = session.get(InstructorDetail.class, theDetailId);
            System.out.println("the Instructor Detail is: " + theDetail);
            System.out.println("the associated instructor is: " + theDetail.getInstructor());

            session.getTransaction().commit();


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
