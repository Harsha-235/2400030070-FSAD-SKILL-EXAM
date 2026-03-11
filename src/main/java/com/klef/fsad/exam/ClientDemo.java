package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure();
        cfg.addAnnotatedClass(Ticket.class);

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Ticket t = new Ticket(101,"Movie Ticket","11-03-2026","Booked");
        session.save(t);

        System.out.println("Record Inserted");

        tx.commit();
        session.close();

        Session session2 = factory.openSession();
        Transaction tx2 = session2.beginTransaction();

        Query q = session2.createQuery("update Ticket set name=?1,status=?2 where id=?3");

        q.setParameter(1,"Bus Ticket");
        q.setParameter(2,"Confirmed");
        q.setParameter(3,101);

        int n = q.executeUpdate();

        System.out.println("Records Updated = "+n);

        tx2.commit();

        session2.close();
        factory.close();
    }
}