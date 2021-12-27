package com.basic.demo.repository;

import com.basic.demo.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EntryRepositoryImpl {
    @Autowired
    private IEntryRepository entryRepository;

    public void createEntry(Entry entry) {
        this.entryRepository.save(entry);
    }

    public Page<Entry> getAll(Pageable pageable){
        return this.entryRepository.findAll(pageable);
    }
}
