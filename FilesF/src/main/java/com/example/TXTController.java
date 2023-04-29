package com.example;

import com.example.Store.Model.PlainTextRecord;
import com.example.Store.Repos.TXTRepo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TXTController {
    private final TXTRepo repo;

    public TXTController(TXTRepo repo) {
        this.repo = repo;
    }


    public PlainTextRecord save(PlainTextRecord record) {
        return repo.save(PlainTextRecord.builder().
                isPublic(true).path("path1").
                accessDate(new Date()).build());
    }

    public List<PlainTextRecord> getAll() {
        return repo.findAll();
    }
}
