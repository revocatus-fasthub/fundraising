package tz.co.fasthub.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

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

    private String category;

    private String type;

    private String description;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDateTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDateTime;

    private BigDecimal amount;

    private String city;

    public boolean isSelected(Long campaignId){
        if (campaignId != null) {
            return campaignId.equals(id);
        }
        return false;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
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

    public Campaign() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setStartDateTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        setEndDateTime(calendar.getTime());
    }
}
