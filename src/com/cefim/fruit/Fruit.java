package com.cefim.fruit;

import java.util.Date;

public class Fruit {

    private Long id;
    private String name;
    private Date expirationDate;

    public Fruit (Long id, String name, Date expirationDate) {
        this.id = id;
        this.name = name;
        this.expirationDate= expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
