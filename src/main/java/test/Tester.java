package test;

import entity.Book;
import entity.Customer;
import enums.CustomerType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester
{

    public static void main(String[] args)
    {
        //Persistence.generateSchema("pu", null);
        Book b1 = new Book("Book 1");
        Customer c1 = new Customer("Kurt", "Wottenburger", CustomerType.GOLD);
        c1.addHobby("Tennis");
        c1.addPhone("112", "its da sound of da police");
        c1.addPhone("114", "its still da sound of da police");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(c1);

            Customer c1Again = em.find(Customer.class, 1);
            Customer c2Again = em.find(Customer.class, 2);
            c1Again.addHobby("Football");
            c2Again.addHobby("Food");
            c2Again.addHobby("Tennis");
            
            em.persist(c1Again);
            em.persist(c2Again);
            
            System.out.println("Hobbies: " + em.find(Customer.class, 2).getId() + " : " + em.find(Customer.class, 2).getHobbies());
            
            em.getTransaction().commit();


        } finally
        {
            em.close();
        }
    }
}
