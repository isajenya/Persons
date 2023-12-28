package com.example.foodmachine.controller;

import com.example.foodmachine.dom.PersonDTO;
import com.example.foodmachine.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.example.foodmachine.util.PersonUtility.getPersonDTO;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@Api(tags = "Person Controller", description = "Endpoints related to managing persons")
public class PersonController {
	private final PersonRepository repository;
	private final ObjectMapper objectMapper;

	public PersonController(PersonRepository repository, ObjectMapper objectMapper) {
		this.repository = repository;
		this.objectMapper = objectMapper;
	}

	@ApiOperation("Upload a Person")
	@PostMapping("api/upload")
	public void addPerson(@RequestBody MultipartFile file) throws IOException {
		PersonDTO person = getPersonDTO(file, objectMapper);
		repository.save(person);
	}


	@ApiOperation("Delete a Person by ID")
	@DeleteMapping("/{id}")
	public void deletePerson(@PathVariable String id) {
		repository.deleteById(id);
	}

	@ApiOperation("Get Person by ID")
	@GetMapping("/{id}")
	public Optional<PersonDTO> getPersonById(@PathVariable String id) {
		return repository.findById(id);
	}

	@ApiOperation("Download JSON by Person ID")
	@GetMapping("/api/getJson/{personId}")
	public ResponseEntity<PersonDTO> downloadJson(@PathVariable String personId) {
		return repository.findById(personId)
				.map(personDTO -> ResponseEntity.ok().body(personDTO))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@ApiOperation("Get JSON data with pagination and sorting")
	@GetMapping("/api/getJson")
	public ResponseEntity<Page<PersonDTO>> getJson(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy
	) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		Page<PersonDTO> personPage = repository.findAll(pageable)
				.map(personDTO -> objectMapper.convertValue(personDTO, PersonDTO.class));

		return ResponseEntity.ok().body(personPage);
	}
}
