package org.repository.drug;

import org.entity.Drug;
import org.entity.DrugList;

import java.sql.SQLException;

public interface DrugRepository {
void add(Drug drug, int prescriptionId) throws SQLException;
    void remove(int id) throws SQLException;
    void update(Drug drug) throws SQLException;
    DrugList read(long prescriptionId) throws SQLException;

}
