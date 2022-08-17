package org.entity;

import java.util.Objects;

public class Patient extends Person {

    public Patient(String username, String password) {
        super(username, password);
    }
    public Patient() {}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return getId() == patient.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
