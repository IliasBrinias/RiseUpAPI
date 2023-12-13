package com.unipi.msc.raiseupapi.Repository;

import com.unipi.msc.raiseupapi.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findById(Long id);
}