package com.example.filesdb.Store.Repos;

import com.example.filesdb.Store.Model.CSVRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSVRepo extends JpaRepository<CSVRecord,Long> {
}
