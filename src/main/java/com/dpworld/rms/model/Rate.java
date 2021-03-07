package com.dpworld.rms.model;


import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Rate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rateId;
    private String rateDescription;
    @Column(nullable=false)
    private Date rateEffectiveDate;
    @Column(nullable=false)
    private Date rateExpirationDate;
    @Column(nullable=false)
    private Integer amount;

    public Rate(){}

    public Rate(Long rateId, String rateDescription, Date rateEffectiveDate, Date rateExpirationDate, Integer amount) {
        this.rateId = rateId;
        this.rateDescription = rateDescription;
        this.rateEffectiveDate = rateEffectiveDate;
        this.rateExpirationDate = rateExpirationDate;
        this.amount = amount;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public String getRateDescription() {
        return rateDescription;
    }

    public void setRateDescription(String rateDescription) {
        this.rateDescription = rateDescription;
    }

    public Date getRateEffectiveDate() {
        return rateEffectiveDate;
    }

    public void setRateEffectiveDate(Date rateEffectiveDate) {
        this.rateEffectiveDate = rateEffectiveDate;
    }

    public Date getRateExpirationDate() {
        return rateExpirationDate;
    }

    public void setRateExpirationDate(Date rateExpirationDate) {
        this.rateExpirationDate = rateExpirationDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
