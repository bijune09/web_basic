package com.basic.demo.repository;

import com.basic.demo.model.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEntryRepository extends JpaRepository<Entry,Integer> {

    @Query(value = "select e.entry_id, e.create_date, e.entry_body, e.entry_img, e.entry_title, e.user_id\n" +
            "from entry e \n" +
            "where e.user_id = ?1\n" +
            "or e.user_id in (\n" +
            "select f.user_id \n" +
            "from follow f\n" +
            "where f.follower_id = ?1)",
            countQuery = "select count(*)\n" +
                    "from entry e \n" +
                    "where e.user_id = ?1\n" +
                    "or e.user_id in (\n" +
                    "select f.user_id \n" +
                    "from follow f\n" +
                    "where f.follower_id = ?1)"
            ,nativeQuery = true)
    Page<Entry> findAllByUser(Integer id, Pageable pageable);
}
