import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Main ME = new Main();

      /* Add few employee records in database */
        Integer HouseID1 = ME.addHouse("123 Mission St", 5000, "Ali");
        Integer HouseID2 = ME.addHouse("555 South Rd", 6000, "Tom");
        Integer HouseID3 = ME.addHouse("897 North Ave", 10000, "John");

      /* List down all the houses */
        ME.listHouses();

      /* Update house rent */
        ME.updateHouse(HouseID1, 7500);

      /* Delete a house from the database */
        ME.deleteHouse(HouseID2);

      /* List down new list of the houses */
        ME.listHouses();
    }
    /* Method to CREATE a house in the database */
    public Integer addHouse(String address, int rent, String landlord){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer houseID = null;
        try{
            tx = session.beginTransaction();
            House house = new House(address, rent, landlord);
            houseID = (Integer) session.save(house);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return houseID;
    }
    /* Method to  READ all the houses */
    public void listHouses(){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List houses = session.createQuery("FROM House").list();
            for (Iterator iterator =
                 houses.iterator(); iterator.hasNext();){
                House employee = (House) iterator.next();
                System.out.print("  Address: " + employee.getAddress());
                System.out.print("  Rent: " + employee.getRent());
                System.out.println("  Landlord: " + employee.getLandlord());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to UPDATE rent for house */
    public void updateHouse(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            House house =
                    (House)session.get(House.class, EmployeeID);
            house.setRent( salary );
            session.update(house);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE a house fro DB */
    public void deleteHouse(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            House employee =
                    (House) session.get(House.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}