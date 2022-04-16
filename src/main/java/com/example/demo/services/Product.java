package com.example.demo.services;

import java.math.BigDecimal;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
//import java.time.ZonedDateTime;

//import com.fasterxml.jackson.annotation.JsonFormat;

public class Product implements java.io.Serializable {

    //http://itdoc.hitachi.co.jp/manuals/3020/30203V0300e/BV030040.HTM
    //https://docs.datastax.com/en/developer/java-driver/4.2/manual/core/temporal_types/
    
    private String description;   // {"name":"id","typeDefinition":"uuid","static":false}
    private String productname;   // {"name":"productname","typeDefinition":"text","static":false} 
    private String id;            // {"name":"description","typeDefinition":"text","static":false} 
    private BigDecimal price;     // {"name":"price","typeDefinition":"decimal","static":false}

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSXX")
    private Date created;        //{"name":"created","typeDefinition":"timestamp","static":false}]
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    
    /*
    public String toString() {
        ZoneId zone = ZoneId.of("Asia/Singapore");

        return "{ \"id\" : \"" + this.id + "\", \"productname\" : \"" + this.productname + "\", \"description\" : \"" + this .description + "\", \"price\" : " + this.price 
               +  ", \"created\" : \"" + this.created.atZone(zone) + "\" } ";
    }*/

}



   
   
   
   
   
