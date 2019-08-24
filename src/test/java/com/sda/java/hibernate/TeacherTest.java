package com.sda.java.hibernate;

import com.sda.java.hibernate.domain.Teacher;
import com.sda.java.hibernate.util.HibernateUtil;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class TeacherTest {
    // CRUD - Create, Read, Update, Delete

    @Test
    public void createTeacher() {

        Transaction tx = null;

        //1. Get SessionFactory
        //2. Get Session
        try (
                SessionFactory factory = HibernateUtil.getSessionFactory();
                Session session = factory.openSession()) {

            //Pentru ca vreau sa fac un insert am nevoie de tranzactie
            tx = session.beginTransaction();

            //Creez un profesor
            Teacher teacher = new Teacher();
            teacher.setFirstName( "Alexandru" );
            teacher.setLastName( "Escu" );
            teacher.setHireDate(new Date());
            teacher.setSalary( 100D );

            //Salvez profesorul
            session.save( teacher );

            //Commit tranzactia
            tx.commit();
        } catch (Exception ex) {
            //rollback tranzactie
            if (null != tx) {
                tx.rollback();
            }

            ex.printStackTrace();
        }
    }
}
