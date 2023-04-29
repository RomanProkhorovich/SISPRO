package com.example.Store.Repos;

import com.example.Store.Model.PlainTextRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TXTRepo extends JpaRepository<PlainTextRecord, Long> {
}
