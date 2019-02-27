package com.stash.photometadata.repository.mongo.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface PatientMongoRepository extends MongoRepository<Patient, String>{
}
