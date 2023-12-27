package com.example.foodmachine.repository;

import com.example.foodmachine.dom.PersonDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonDTO, String>, PagingAndSortingRepository<PersonDTO, String> {

}