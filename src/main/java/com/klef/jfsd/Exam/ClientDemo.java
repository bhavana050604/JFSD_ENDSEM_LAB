package com.klef.jfsd.Exam;

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
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setAge(30);
        customer2.setLocation("Los Angeles");

        session.save(customer1);
        session.save(customer2);

        transaction.commit();

        // Apply Criteria Queries
        // Example: Equal
        Criteria criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("location", "New York"));
        List<Customer> result = criteria.list();
        System.out.println("Customers in New York:");
        result.forEach(c -> System.out.println(c.getName()));
        
        // Example: Not Equal
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("name", "Alice"));
        result = criteria.list();
        System.out.println("Customers not named Alice:");
        result.forEach(c -> System.out.println(c.getName()));

        // Example: Greater Than
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 26));
        result = criteria.list();
        System.out.println("Customers older than 26:");
        result.forEach(c -> System.out.println(c.getName()));

        // Example: Between
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 30));
        result = criteria.list();
        System.out.println("Customers aged between 20 and 30:");
        result.forEach(c -> System.out.println(c.getName()));

        // Example: Like
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("email", "%example.com"));
        result = criteria.list();
        System.out.println("Customers with email ending in example.com:");
        result.forEach(c -> System.out.println(c.getName()));

        session.close();
        sessionFactory.close();
    }
}