package org.service;

import org.entity.Drug;
import org.repository.drug.DrugRepositoryImpl;

import java.sql.SQLException;

public class DrugService {
    DrugRepositoryImpl itemRepositoryImpl = new DrugRepositoryImpl();

    public void add(Drug drug, int prescriptionId) throws SQLException {
        itemRepositoryImpl.add(drug, prescriptionId);
    }
    public void remove(int id) throws SQLException {
        itemRepositoryImpl.remove(id);
    }
    public void edit(Drug drug) throws SQLException {
        itemRepositoryImpl.update(drug);
    }
}
