package main.java.com.dl.rentsplanet;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Created by bicboi on 10/15/16.
 */

@XmlRootElement
public class RentEntryBean {
    private String address;
    private String landLord;
    private String rent;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandLord() {
        return landLord;
    }

    public void setLandLord(String landLord) {
        this.landLord = landLord;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }
}
