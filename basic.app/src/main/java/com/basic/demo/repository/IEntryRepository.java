package com.basic.demo.repository;

import com.basic.demo.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEntryRepository extends JpaRepository<Entry,Integer> {
}
