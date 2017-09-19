package tz.co.fasthub.model;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Created by Revocatus Nyaindi on 9/12/2017.
 */

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String cam_name;

    private String description;

    private BigDecimal amount;

    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCam_name() {
        return cam_name;
    }

    public void setCam_name(String cam_name) {
        this.cam_name = cam_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
