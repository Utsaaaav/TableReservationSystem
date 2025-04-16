package com.cord.trs.repository;

import com.cord.trs.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepo extends JpaRepository<Tables, Long> {

    Optional<Object> findByTableNumber(String tableNumber);
}
