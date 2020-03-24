/*
- Author: Jack Adams
- Author Id: S0201412
- Date Created: 29 Aug 2019
- Use: Main class includes main method. Ultimately displays all persisted data for the user as well as a menu for the user to access queries.
 */
package assignment2;

//Import Statements
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityTransaction;

public class Assignment2 {
    
    //Creates EntityManager 
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args)
    {
        dataCreation(); // Call of data creating method
        displayProperties(); //Call of method that displays all persisted properties
        displayManagers(); //Call of method that displays all persisted property managers
        displayAllocatedProperties(); //Call of method that displays all allocated properties and the assigned managers
        querySelection(); //Mehtod is called allowing the user to access the three different queries for persisted data
    }
    
    //Method to create and persist data into database
    public static void dataCreation()
    {               
        //Creation of Property objects and persisting them to database
        SaleProperty property1 = new SaleProperty();        
        property1.setAddress("123 Fake Str, Brisbane");
        property1.setDescription("This is a test house");
        property1.setNumRooms(3);
        property1.setNumBathrooms(2);
        property1.setSalePrice(300000);
        property1.setType("House");
             
        SaleProperty property2 = new SaleProperty();
        property2.setAddress("321 Test Ave, Brisbane");
        property2.setDescription("This is another test house");
        property2.setNumRooms(2);
        property2.setNumBathrooms(1);
        property2.setSalePrice(150000);
        property2.setType("Townhouse");               
        
        RentalProperty property3 = new RentalProperty();
        property3.setAddress("987 Testing Lane, Brisbane");
        property3.setDescription("This is yet another test house");
        property3.setNumRooms(4);
        property3.setNumBathrooms(3.5);
        property3.setRentalPrice(500);
        property3.setType("House");
        
        RentalProperty property4 = new RentalProperty();
        property4.setAddress("789 Fake Blvd, Brisbane");
        property4.setDescription("This is the last test house");
        property4.setNumRooms(3);
        property4.setNumBathrooms(1);
        property4.setRentalPrice(350);
        property4.setType("Apartment");

        //Creation of Manager objects and persisting them to database     
        PropertyManager manager1 = new PropertyManager();
        manager1.setFirstName("Jack");
        manager1.setLastName("Adams");
        manager1.setEmailAddress("Jack.Adams@realestate.com.au");
        manager1.setLandLinePhoneNumber("07 123 456");
        manager1.setMobileNumber("0412 345 678");
        ArrayList<Properties> properties1 = new ArrayList<>();
        properties1.add(property1);
        properties1.add(property2);
        manager1.setProperties(properties1);
                
        PropertyManager manager2 = new PropertyManager();
        manager2.setFirstName("John");
        manager2.setLastName("Smith");
        manager2.setEmailAddress("John.Smith@realestate.com.au");
        manager2.setLandLinePhoneNumber("07 123 456");
        manager2.setMobileNumber("0498 765 432");
        ArrayList<Properties> properties2 = new ArrayList<>();
        properties2.add(property3);
        properties2.add(property4);
        manager2.setProperties(properties2);
               
        tx.begin();
        em.persist(manager1);
        em.persist(manager2);
        em.flush();
        em.persist(property1);
        em.persist(property2);
        em.persist(property3);
        em.persist(property4);
        tx.commit();
        em.close();
        emf.close();
        
    }
    public static void displayProperties()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Properties> query = em.createNamedQuery("displayAllProperties", Properties.class);
        List<Properties> list = query.getResultList();
        
        System.out.println("**************************************************\n");
        for(Properties e:list)
        {
            if(e instanceof SaleProperty)
            {
            System.out.println("Sale Property" + "\nProperty ID: " + e.getId() + "\nType:" + e.getType() 
                    + "\nNumber of Bedrooms: " + e.getNumRooms() + "\nNumber of Bathrooms: " + e.getNumBathrooms()
                    + "\nDescription: " + e.getDescription() + "\nLocation: " + e.getAddress() + "\nSale Price: $" + ((SaleProperty) e).getSalePrice() + "\n");
            }
            else if(e instanceof RentalProperty)
            {
                System.out.println("Rental Property" + "\nProperty ID: " + e.getId() + "\nType:" + e.getType() 
                    + "\nNumber of Bedrooms: " + e.getNumRooms() + "\nNumber of Bathrooms: " + e.getNumBathrooms()
                    + "\nDescription: " + e.getDescription() + "\nLocation: " + e.getAddress() + "\nRental Price: $" + ((RentalProperty) e).getRentalPrice() + "\n");
            }
        }
    }
    public static void displayManagers()
    {
        System.out.println("**************************************************\n");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<PropertyManager> query = em.createNamedQuery("displayAllManagers", PropertyManager.class);
        List<PropertyManager> list = query.getResultList();
        
        for(PropertyManager e:list)
        {
            System.out.println("Property Manager" + "\nName: " + e.getFirstName() + " " + e.getLastName() + "\nEmail Address: " + e.getEmailAddress()
            + "\nPhone: " + e.getLandLineNumber() + "\nMobile: " + e.getMobileNumber() + "\n");
        }
    }
    public static void displayAllocatedProperties()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query1 = em.createQuery("SELECT p FROM PROPERTIES p");
        List<Properties> list = query1.getResultList();
        
        System.out.println("**************************************************\n");
        
        for(Properties e:list)
        {
            System.out.println("Allocated Properties" + "\nProperty ID: " + e.getId() + "\nManager Name: " + " <---> " + "Address Details: " +  e.getAddress() + "\n");
        }
    }
    //Method to show query options menu and read user selection
    public static void querySelection()
    {
        Scanner numScanner = new Scanner(System.in);
        int option = 0;
        while(option != 4) //Loop ensures the user can continue to use the app after selecting a menu item
        {
            queryMenu();
            System.out.print("Enter your option: ");
            option = numScanner.nextInt();

            switch(option)
            {
                case 1:
                    propertiesQuery();
                    break;
                case 2:
                    managersQuery();
                    break;
                case 3:
                    allocatedPropertiesQuery();
                    break;
                case 4:
                    System.out.println("Application Closing Down.");
                    System.exit(0);
            }
        }                
    }
    //Method to create the menu used in querySelection method
    public static void queryMenu()
    {
        System.out.println("**************************************************");
        System.out.println("Find a property----------------------------------1");
        System.out.println("Find a property manager--------------------------2");
        System.out.println("Find allocated properties of a manager-----------3");
        System.out.println("Exit---------------------------------------------4");
        System.out.println("**************************************************");
        System.out.println();
        System.out.println();
    }
    //Method containing query to display a given property and its details
    public static void propertiesQuery()
    {
        Scanner searchTerm = new Scanner(System.in);
        String streetNum;
        String streetName;
        //App asks user for a street number and street name
        System.out.print("Enter the street number: ");
        streetNum = searchTerm.nextLine();
        System.out.print("Enter street name: ");
        streetName = searchTerm.nextLine();
        //search terms are concatted for the query below
        String addressConCat = streetNum + " " + streetName;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Properties> query = em.createNamedQuery("displaySelectProperty", Properties.class).setParameter("addressConCat", "%" + addressConCat + "%");
        List<Properties> list = query.getResultList();
        
        for(Properties e:list)
        {
            if(e instanceof SaleProperty) //Checking the class of the found property to ensure correct details are printed
            {
            System.out.println("\n");
            System.out.println("Sale Property" + "\nProperty ID: " + e.getId() + "\nType:" + e.getType() 
                    + "\nNumber of Bedrooms: " + e.getNumRooms() + "\nNumber of Bathrooms: " + e.getNumBathrooms()
                    + "\nDescription: " + e.getDescription() + "\nLocation: " + e.getAddress() + "\nSale Price: $" + ((SaleProperty) e).getSalePrice());
            System.out.println("\n");
            }
            else if(e instanceof RentalProperty)
            {   
                System.out.println("\n");
                System.out.println("Rental Property" + "\nProperty ID: " + e.getId() + "\nType:" + e.getType() 
                    + "\nNumber of Bedrooms: " + e.getNumRooms() + "\nNumber of Bathrooms: " + e.getNumBathrooms()
                    + "\nDescription: " + e.getDescription() + "\nLocation: " + e.getAddress() + "\nRental Price: $" + ((RentalProperty) e).getRentalPrice());
                System.out.println("\n");
            }
        }
    }
    //Method containing query to display a given manager and their details
    public static void managersQuery()
    {
        Scanner searchTerm = new Scanner(System.in);
        String fName;
        String lName;
        
        System.out.print("Enter the first name of the manager: ");
        fName = searchTerm.nextLine();
        System.out.print("Enter the last name of the manager: ");
        lName = searchTerm.nextLine();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<PropertyManager> query = em.createNamedQuery("displaySelectManager", PropertyManager.class).setParameter("fName", fName).setParameter("lName", lName);        
        List<PropertyManager> list = query.getResultList();
        
        for(PropertyManager e:list)
        {
            System.out.println("\n");
            System.out.println("Property Manager" + "\nName: " + e.getFirstName() + " " + e.getLastName() + "\nEmail Address: " + e.getEmailAddress()
            + "\nPhone: " + e.getLandLineNumber() + "\nMobile: " + e.getMobileNumber());
            System.out.println("\n");
        }
    }
    public static void allocatedPropertiesQuery()
    {
        Scanner searchTerm = new Scanner(System.in);
        String fName;
        String lName;
        
        System.out.print("Enter the first name of the manager: ");
        fName = searchTerm.nextLine();
        System.out.print("Enter the last name of the manager: ");
        lName = searchTerm.nextLine();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM PROPERTIES p");
        
        List<Properties> list = query.getResultList();
        
        for(Properties e:list)
        {
            System.out.println("Allocated Properties" + "\nProperty ID: " + e.getId() + "\nManager Name: " + " <---> " + e.getAddress());
        }
    }
}
