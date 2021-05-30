package com.eximbay.wookim.document;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document("sales")
@Getter
@Setter
public class SalesDoc {
    @Id
    private String id;

    private String title;
    private String interfaceType;
    private Integer price;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date regDate;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date modDate;

    @Override
    public String toString(){
        String memberInfoStr = "{id : " + this.id
                + ", \n title : " + this.title
                + ", \n interfaceType : " + this.interfaceType
                + ", \n price : " + this.price
                + ", \n regDate : " + this.regDate
                + ", \n modDate : " + this.modDate
                + "}";
        return memberInfoStr;
    }
}
