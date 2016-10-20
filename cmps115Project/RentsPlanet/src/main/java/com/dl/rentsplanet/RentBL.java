package com.dl.rentsplanet;

import java.util.List;

/**
 * Created by bicboi on 10/19/16.
 */
public class RentBL {
    public void addRentEntry(RentEntryBean reb) {
        /*
        Create DAO object here
         */
        RentDAO rentDAO = new RentDAO();
        rentDAO.addHouse(reb);
    }

    public void deleteHouseEntry(RentEntryBean rentEntryBean){
        RentDAO rentDAO = new RentDAO();
        //rentDAO.deleteEntry(rentEntryBean);
        //FIXME
    }


    public List<RentEntryBean> listHouses(){
        RentDAO rentDAO = new RentDAO();
        return rentDAO.listHouses();
    }

    public void updateHouseEntry(RentEntryBean rentEntryBean){
        RentDAO rentDAO = new RentDAO();
        //FIXME
    }
}
