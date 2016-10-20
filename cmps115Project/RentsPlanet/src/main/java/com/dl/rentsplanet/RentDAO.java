package com.dl.rentsplanet;

/**
 * Created by bicboi on 10/19/16.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RentDAO {
    private static SessionFactory factory ;
 /*   public static void main(String[] args) {
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        }catch (Throwable ex) {
//            System.err.println("!!!Failed to build session factory. " + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
        RentDAO m = new RentDAO();
        //Integer id1 = m.addHouse("123 Mission st", 5000, "bob");
        //Integer id2 = m.addHouse("576 Scjlsha st", 7000, "Smith");
        //Integer id3 = m.addHouse("123 Mission st", 4000, "Joe");
        //m.listHouses();
    }
    /* Method to CREATE a house in the database */
    public Integer addHouse(RentEntryBean rentEntry){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse() "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Integer houseID = null;
        try{
            tx = session.beginTransaction();
            House house = new House(rentEntry.getAddress(), Integer.parseInt(rentEntry.getRent()), rentEntry.getLandLord());
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
    public List<RentEntryBean> listHouses(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse() "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
	List<RentEntryBean> house_list = new ArrayList<RentEntryBean>();
        try{
            tx = session.beginTransaction();
            List houses = session.createQuery("FROM House").list();
            Iterator iter = houses.iterator();
	        while(iter.hasNext()) {
                House house = (House) iter.next();
	            house_list.add(house.toRentEntryBean());
               /* for (Iterator iterator =
                 houses.iterator(); iterator.hasNext();) {*/
               // House employee = (House) iterator.next();
               // System.out.print("  Address: " + employee.getAddress());
               // System.out.print("  Rent: " + employee.getRent());
               // System.out.println("  Landlord: " + employee.getLandlord());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return house_list;
    }
    /* Method to UPDATE rent for house */
    public void updateHouse(Integer EmployeeID, int salary){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse() "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            House house =
                    (House) session.get(House.class, EmployeeID);
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
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse() "+ex);
            throw new ExceptionInInitializerError(ex);
        }
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
