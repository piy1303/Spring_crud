package org.example.model;

public class User {

    private String name;
    private Integer id;
    private String email;
    private String mobile;

    public User(String name, Integer id, String email, String mobile) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.mobile = mobile;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
