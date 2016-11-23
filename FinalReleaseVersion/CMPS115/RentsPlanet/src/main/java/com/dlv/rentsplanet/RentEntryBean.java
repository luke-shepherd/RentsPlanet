package com.dlv.rentsplanet;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bicboi on 10/19/16.
 */
@XmlRootElement
public class RentEntryBean {
    private String address;
    private String rent;
    private String landlord;
    private String review;

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

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) { this.landlord = landlord; }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
