package com.cataasClientBE.cataasClientBE.repositories;

import com.cataasClientBE.cataasClientBE.model.CatFile;
import org.springframework.data.repository.CrudRepository;

public interface CatImageRepository extends CrudRepository<CatFile, Long> {
}

