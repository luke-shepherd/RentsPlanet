package com.dl.rentsplanet;

/**
 * Created by bicboi on 10/19/16.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class RentDAO {
    private static SessionFactory factory ;
    /*public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        RentDAO db = new RentDAO();
        RentEntryBean h1 = new RentEntryBean();
        h1.setAddress("123 First St"); h1.setRent("1000"); h1.setLandLord("Adam");

        RentEntryBean h2 = new RentEntryBean();
        h2.setAddress("234 Second St."); h2.setRent("2000"); h2.setLandLord("Billy");

        db.addHouse(h1);
        db.addHouse(h2);
        //db.addHouse(h1);
        db.listHouses();
        db.deleteHouse(h1);
        db.deleteHouse(h2);

        System.exit(0);
    }*/

    /* Method to INSERT house in the database */
    public Integer addHouse(RentEntryBean rentEntry) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse()!!! "+ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        //Transaction tx = null;
        Integer houseID = null;
        try{
            session.beginTransaction();
            House house = new House(rentEntry.getAddress(), Integer.parseInt(rentEntry.getRent()), rentEntry.getLandLord());
            houseID = (Integer) session.save(house);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            System.err.println("!!!Failed to Insert House into DB" + e);
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
    	List<RentEntryBean> house_list = new ArrayList<RentEntryBean>();
        try{
            session.beginTransaction();
            String hql = "from House";
            List<House> result = session.createQuery(hql).list();
            for (House h : result) {
                house_list.add(h.toRentEntryBean());
                System.out.println("Address: "+h.getAddress());             //DELETE WHEN NOT TESTING
            }
            session.getTransaction().commit();
            }catch (HibernateException e) {
            System.err.println("!!!FAILED to List Houses" + e);
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
    public void deleteHouse(RentEntryBean house){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("!!!Failed to build SessionFactory in addHouse() "+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            //String hql = "delete from House where address = '" + house.getAddress() + "'";
            //System.out.println(hql);
            Query query = session.createQuery("delete from House where address = :address");
            query.setParameter("address", house.getAddress());
            query.executeUpdate();
            session.getTransaction().commit();
        }catch (HibernateException e) {
            System.err.println("!!!Failed to delete house!!! " + e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
