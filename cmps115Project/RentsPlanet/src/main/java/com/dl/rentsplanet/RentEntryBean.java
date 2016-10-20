package com.dl.rentsplanet;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bicboi on 10/19/16.
 */
@XmlRootElement
public class RentEntryBean {
    private String address;
    private String rent;
    private String landLord;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getLandLord() {
        return landLord;
    }

    public void setLandLord(String landLord) {
        this.landLord = landLord;
    }
}
