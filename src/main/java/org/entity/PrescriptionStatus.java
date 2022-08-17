package org.entity;

public enum PrescriptionStatus {
    PENDING, CONFIRMED, REJECTED;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
