package com.pancou.ad.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



/**
 * PayType entity. @author MyEclipse Persistence Tools
 */

@Entity
public class PayType  implements java.io.Serializable {


    // Fields    

     @Id
	@GeneratedValue
	private Integer payTypeId;
     private String payTypeName;


    // Constructors

    /** default constructor */
    public PayType() {
    }

    
    /** full constructor */
    public PayType(String payTypeName) {
        this.payTypeName = payTypeName;
    }

   
    // Property accessors

    public Integer getPayTypeId() {
        return this.payTypeId;
    }
    
    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public String getPayTypeName() {
        return this.payTypeName;
    }
    
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }
   








}