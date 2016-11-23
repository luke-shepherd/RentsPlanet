package com.dlv.rentsplanet;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by bicboi on 10/19/16.
 */
@Entity(name = "Houses")
public class RentEntry {
    /*private int id;*/
    /* Random ass comment */
    @Id
    private String address;
    private String rent;
    private String landlord;
    private String review;

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public RentEntryBean toRentEntryBean(RentEntry re) {
        RentEntryBean reb = new RentEntryBean();
        reb.setAddress(re.getAddress());
        reb.setRent(re.getRent());
        reb.setLandlord(re.getLandlord());
        reb.setReview(re.getReview());
        return reb;

    }

}
