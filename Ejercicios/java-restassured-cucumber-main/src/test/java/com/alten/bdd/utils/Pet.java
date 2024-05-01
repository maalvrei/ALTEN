package com.alten.bdd.utils;

import java.util.HashMap;
import java.util.List;

public class Pet {

    private String name;
    private long id;
    private List<HashMap<String,String>> tags;

    public Pet(String name,List<HashMap<String, String>> tags) {
        this.tags = tags;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public List<HashMap<String, String>> getTags() {
        return tags;
    }
}
