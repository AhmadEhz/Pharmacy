package org.service;

import org.entity.Drug;
import org.entity.DrugList;
import org.repository.drug.DrugRepositoryImpl;

import java.sql.SQLException;

public class DrugService {
    private final DrugRepositoryImpl drugRepository = new DrugRepositoryImpl();

    public void add(Drug drug, long prescriptionId) throws SQLException {
        drugRepository.add(drug, prescriptionId);
    }
    public Drug load(Drug drug) {
        try {
            return drugRepository.read(drug);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remove(Drug drug) {
        try {
            drugRepository.delete(drug);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remove(DrugList drugList) {
        for (int i = 0; i<drugList.length(); i++) {
            remove(drugList.load(i));
        }
    }
    public void editName(Drug drug, String name) {
        drug.setName(name);
        edit(drug);
    }
    private void edit(Drug drug) {

        try {
            this.drugRepository.update(drug);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrice(Drug drug, int price) {
        drug.setPrice(price);
        edit(drug);

    }

    public void setDoesExist(Drug drug, boolean exist) {
        drug.setDoesExist(exist);
        edit(drug);
    }
    public boolean isExist(Drug drug) {
        try {
            return drugRepository.read(drug)!=null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
