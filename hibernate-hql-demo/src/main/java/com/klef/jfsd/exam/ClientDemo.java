package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();

        // Insert records
        insertClients(sessionFactory);

        // Print all records
        printAllClients(sessionFactory);

        sessionFactory.close();
    }

    public static void insertClients(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Client client1 = new Client();
        client1.setName("John Doe");
        client1.setGender("Male");
        client1.setAge(30);
        client1.setLocation("New York");
        client1.setEmail("john.doe@example.com");
        client1.setMobileNumber("1234567890");

        Client client2 = new Client();
        client2.setName("Jane Smith");
        client2.setGender("Female");
        client2.setAge(28);
        client2.setLocation("Los Angeles");
        client2.setEmail("jane.smith@example.com");
        client2.setMobileNumber("0987654321");

        session.save(client1);
        session.save(client2);

        transaction.commit();
        session.close();

        System.out.println("Clients inserted successfully!");
    }

    public static void printAllClients(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        List<Client> clients = session.createQuery("from Client", Client.class).list();

        System.out.println("All Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }

        session.close();
    }
}
