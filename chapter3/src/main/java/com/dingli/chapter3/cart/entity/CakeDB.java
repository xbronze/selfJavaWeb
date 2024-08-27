package com.dingli.chapter3.cart.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xbronze
 * @date: 2024-08-27 19:08
 * @description: TODO
 */
public class CakeDB {

    private static Map<String, Cake> cake = new HashMap<String, Cake>();

    static {
        cake.put("1", new Cake("1", "A类蛋糕"));
        cake.put("2", new Cake("2", "B类蛋糕"));
        cake.put("3", new Cake("3", "C类蛋糕"));
        cake.put("4", new Cake("4", "D类蛋糕"));
        cake.put("5", new Cake("5", "E类蛋糕"));
    }

    public static Collection<Cake> getAll() {
        return cake.values();
    }

    public static Cake getCake(String id) {
        return cake.get(id);
    }
}
