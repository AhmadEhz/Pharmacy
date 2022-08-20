package org.repository.drug;

import org.entity.Drug;
import org.entity.DrugList;

import java.sql.SQLException;

public interface DrugRepository {
void add(Drug drug, long prescriptionId) throws SQLException;
    void delete(Drug drug) throws SQLException;
    void update(Drug drug) throws SQLException;
    DrugList read(long prescriptionId) throws SQLException;

}
