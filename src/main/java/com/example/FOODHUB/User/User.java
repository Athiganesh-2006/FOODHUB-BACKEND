package com.example.FOODHUB.User;

public class User {
    private Long  id;
    private String Name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String Company;

    public User(Long id, String name, String email, String password, String role, String phone, String company) {
        this.id = id;
        this.Name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        Company = company;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return Company;
    }
}
