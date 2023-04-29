package com.example.filesdb;

import com.example.filesdb.Store.Model.CSVRecord;
import com.example.filesdb.Store.Repos.CSVRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVController {
    private final CSVRepo repo;

    public CSVController(CSVRepo repo) {
        this.repo = repo;
    }

    public CSVRecord save(CSVRecord record){
        return repo.save(CSVRecord.builder().fileRecord("record1").
                name("name1").version(1).build());
    }

    public List<CSVRecord> getAll(){
        return repo.findAll();
    }
}
