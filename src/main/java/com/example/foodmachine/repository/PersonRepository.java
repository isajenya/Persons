package com.example.foodmachine.repository;

import com.example.foodmachine.dom.PersonDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<PersonDTO, String> {
	Optional<PersonDTO> findByName(String name);

}