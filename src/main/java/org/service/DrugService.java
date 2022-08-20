package org.service;

import org.entity.Drug;
import org.entity.DrugList;
import org.repository.DrugRepository;

import java.sql.SQLException;

public class DrugService {
    private final DrugRepository drugRepository = new DrugRepository();

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
        if(drugList==null)
            return;
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
    public boolean isExist(Drug drug) {
        try {
            return drugRepository.read(drug)!=null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Drug drug) {
        try {
            drugRepository.update(drug);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
