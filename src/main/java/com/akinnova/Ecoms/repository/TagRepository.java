package com.akinnova.Ecoms.repository;

import com.akinnova.Ecoms.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, UUID> {

}
