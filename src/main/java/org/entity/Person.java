package org.entity;

import org.service.PersonService;

public abstract class Person {
    private long id;
    private String name;
    private String username;
    private String password;

    public Person() {
    }

    public Person(String username, String password) {
        PersonService personService = new PersonService();
        this.id = personService.setId();
        this.name = personService.setName();
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
