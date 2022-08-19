package org.service;

import org.entity.Drug;
import org.repository.drug.DrugRepositoryImpl;

import java.sql.SQLException;

public class DrugService {
    private final DrugRepositoryImpl drugRepository = new DrugRepositoryImpl();

    public void add(Drug drug, long prescriptionId) throws SQLException {
        this.drugRepository.add(drug, prescriptionId);
    }

    public void remove(int id) throws SQLException {
        drugRepository.remove(id);
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
