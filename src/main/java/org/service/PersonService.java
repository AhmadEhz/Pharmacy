package org.service;

import org.entity.AccessModifier;

public class PersonService {
    public AccessModifier setAccessModifier() {
        return AccessModifier.PATIENT;
    }

    public String setName() {
        return null;
    }

    public long setId() {
        return 1;
    }
}
