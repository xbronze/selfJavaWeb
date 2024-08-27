package com.dingli.chapter3.cart.entity;

/**
 * @author: xbronze
 * @date: 2024-08-27 19:07
 * @description: TODO
 */
public class Cake {

    private String id;
    private String name;

    public Cake(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
