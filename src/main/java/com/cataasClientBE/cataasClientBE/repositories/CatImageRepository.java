package com.cataasClientBE.cataasClientBE.repositories;

import com.cataasClientBE.cataasClientBE.model.entity.CatFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CatImageRepository extends CrudRepository<CatFile, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CatFile SET deleted=true")
    void deleteFiles();
}

