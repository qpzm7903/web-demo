package com.example.hibernatedemo;

import com.example.hibernatedemo.entity.Customer;
import com.example.hibernatedemo.entity.CustomerRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.UUID;

@SpringBootTest
class HibernateDemoApplicationTests {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private CustomerRepo customerRepo;

    @Test
    void contextLoads() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("test");
        customer.setAccountsPayableXrefId(UUID.randomUUID());

        entityManager.persist(customer);
        entityManager.flush();

        entityManager.persist(customer);


        entityManager.flush();


        entityManager.persist(customer);



        Customer customer1 = entityManager.find(Customer.class, 1);

        Assertions.assertEquals(customer.getName(), customer1.getName());

        entityManager.getTransaction().commit();
//        entityManager.close();
        entityManager.remove(customer);
        entityManager.detach(customer);

//        entityManager = entityManagerFactory.createEntityManager();
        customer.setName("tes2");
        entityManager.persist(customer);
        Customer customer2 = entityManager.find(Customer.class, customer.getId());
        Assertions.assertEquals(customer.getName(), customer2.getName());


    }

    @Test
    void test_repo() throws InterruptedException {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("test");
        customer.setAccountsPayableXrefId(UUID.randomUUID());
        Thread t1 = new Thread(() -> {
            Customer save = getCustomer(customer);
            Assertions.assertEquals(customer.getName(), save.getName());
            Assertions.assertEquals(customer.getAccountsPayableXrefId(), save.getAccountsPayableXrefId());
        });
        Thread t2 = new Thread(() -> {
            Customer save = getCustomer(customer);
            Assertions.assertEquals(customer.getName(), save.getName());
            Assertions.assertEquals(customer.getAccountsPayableXrefId(), save.getAccountsPayableXrefId());
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();



    }

    private Customer getCustomer(Customer customer) {
        Customer save = customerRepo.save(customer);
        return save;
    }

}
