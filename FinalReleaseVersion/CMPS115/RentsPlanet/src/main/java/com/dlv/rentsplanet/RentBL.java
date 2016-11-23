package com.dlv.rentsplanet;

import java.util.List;

/**
 * Created by bicboi on 10/19/16.
 */
public class RentBL {

    public void addRentEntry(RentEntryBean reb) {
        RentDAO rentDAO = new RentDAO();
        rentDAO.addEntry(reb);
    }

    public RentEntryBean getByAddress(String address){
        RentDAO rentDAO = new RentDAO();
        return rentDAO.getEntry(address);
    }

    public void deleteHouseEntry(RentEntryBean reb){
        RentDAO rentDAO = new RentDAO();
        rentDAO.deleteEntry(reb);
    }

    public List<RentEntryBean> listHouses(){
        RentDAO rentDAO = new RentDAO();
        return rentDAO.listEntries();
    }

    public void updateHouseEntry(RentEntryBean reb){
        RentDAO rentDAO = new RentDAO();
        rentDAO.updateEntry(reb);
    }
}
