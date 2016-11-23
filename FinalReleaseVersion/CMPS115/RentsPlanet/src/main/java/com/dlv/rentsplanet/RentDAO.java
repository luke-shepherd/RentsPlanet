package com.dlv.rentsplanet;

/**
 * Created by bicboi on 10/19/16.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RentDAO {
    private static SessionFactory factory ;

    /* Method to CREATE a house in the database */
    public void addEntry(RentEntryBean reb){
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
            RentEntry rentEntry = new RentEntry();
            rentEntry.setAddress(reb.getAddress());
            rentEntry.setRent(reb.getRent());
            rentEntry.setLandlord(reb.getLandlord());
            rentEntry.setReview(reb.getReview());
            session.save(rentEntry);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to  READ all the houses */
    public List<RentEntryBean> listEntries(){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse() "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
    	List<RentEntryBean> entryList = new ArrayList<RentEntryBean>();
        try{
            tx = session.beginTransaction();
            List entries = session.createQuery("FROM Houses").list();
            Iterator iter = entries.iterator();
	        while(iter.hasNext()) {
                RentEntry house = (RentEntry) iter.next();
	            entryList.add(house.toRentEntryBean(house));
                /*for (Iterator iterator =
                 houses.iterator(); iterator.hasNext();) {
                    RentEntry employee = (RentEntry) iterator.next();
                    System.out.print("  Address: " + employee.getAddress());
                    System.out.print("  Rent: " + employee.getRent());
                    System.out.println("  Landlord: " + employee.getLandlord());
                }*/
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            return entryList;
        }
    }

    /* Method to List Single Entry */
    public RentEntryBean getEntry(String address) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!! Failed to build SessionFactory in getHouse!!!" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx;
        RentEntryBean reb = new RentEntryBean();
        try {
            tx = session.beginTransaction();
            RentEntry rentEntry = session.get(RentEntry.class, address);
            reb = rentEntry.toRentEntryBean(rentEntry);
            tx.commit();
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
            return reb;
        }
    }

    /* Method to UPDATE rent for house */
    public void updateEntry(RentEntryBean reb){
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
            RentEntry rentEntry = new RentEntry();
            rentEntry.setAddress(reb.getAddress());
            rentEntry.setRent(reb.getRent());
            rentEntry.setLandlord(reb.getLandlord());
            rentEntry.setReview(reb.getReview());
            session.update(rentEntry);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /* Method to DELETE a house fro DB */
    public void deleteEntry(RentEntryBean reb){
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
            RentEntry rentEntry = new RentEntry();
            rentEntry.setAddress(reb.getAddress());
            rentEntry.setRent(reb.getRent());
            rentEntry.setLandlord(reb.getLandlord());
            rentEntry.setReview(reb.getReview());
            session.delete(rentEntry);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
