package com.example.coruscatept;

/**
 * Created by HP on 06-Jan-18.
 */

public class Pojo {
    private String name;
    private String address;

    public Pojo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }


}
