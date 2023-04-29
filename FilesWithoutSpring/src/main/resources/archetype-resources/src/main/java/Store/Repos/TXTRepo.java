package com.example.filesdb.Store.Repos;

import com.example.filesdb.Store.Model.PlainTextRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TXTRepo extends JpaRepository<PlainTextRecord,Long> {
}
