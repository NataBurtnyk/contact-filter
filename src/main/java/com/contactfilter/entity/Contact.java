package com.contactfilter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;

    public Contact() {}

    public Contact(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id " + id +
                "name" + name +
                '}';
    }
}
