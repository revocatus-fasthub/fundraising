package tz.co.fasthub.fundraising.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Revocatus Nyaindi on 9/13/2017.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;
    private String medical;
    private String wedding;
    private String funeral;
    private String education;
    private String emergency;
    private String community;
    private String others;

    public Category() {
    }

    public Category(String medical, String wedding, String funeral, String education, String emergency, String community, String others) {
        this.medical = medical;
        this.wedding = wedding;
        this.funeral = funeral;
        this.education = education;
        this.emergency = emergency;
        this.community = community;
        this.others = others;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getWedding() {
        return wedding;
    }

    public void setWedding(String wedding) {
        this.wedding = wedding;
    }

    public String getFuneral() {
        return funeral;
    }

    public void setFuneral(String funeral) {
        this.funeral = funeral;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
