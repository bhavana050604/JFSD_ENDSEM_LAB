package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Customer.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert Records
        Transaction transaction = session.beginTransaction();

        Customer customer1 = new Customer();
        customer1.setName("Bhavana");
        customer1.setEmail("bhavana@gmail.com");
        customer1.setAge(20);
        customer1.setLocation("Guntur");

        Customer customer2 = new Customer();
        customer2.setName("Shivani");
        customer2.setEmail("Shivani@gmail.com");
        customer2.setAge(22);
        customer2.setLocation("Vijayawada");

        session.save(customer1);
        session.save(customer2);

        transaction.commit();

        // Apply Criteria Queries
        // Example: Equal
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("location", "Guntur"));
        List<Customer> result = criteria.list();
        System.out.println("Customers Guntur:");
        result.forEach(c -> System.out.println(c.getName()));
        
        // Example: Not Equal
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("name", "Bhavana"));
        result = criteria.list();
        System.out.println("Customers not named Bhavana:");
        result.forEach(c -> System.out.println(c.getName()));

        // Example: Greater Than
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 20));
        result = criteria.list();
        System.out.println("Customers older than 26:");
        result.forEach(c -> System.out.println(c.getName()));

        // Example: Between
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 15, 30));
        result = criteria.list();
        System.out.println("Customers aged between 20 and 30:");
        result.forEach(c -> System.out.println(c.getName()));

        // Example: Like
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("email", "%gmail.com"));
        result = criteria.list();
        System.out.println("Customers with email ending in gmail.com:");
        result.forEach(c -> System.out.println(c.getName()));

        session.close();
        sessionFactory.close();
    }
}