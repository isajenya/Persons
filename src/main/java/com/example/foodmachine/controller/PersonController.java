package com.example.foodmachine.controller;

import com.example.foodmachine.dom.PersonDTO;
import com.example.foodmachine.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class PersonController {
	private final PersonRepository repository;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public PersonController(PersonRepository repository) {
		this.repository = repository;
	}

	@PostMapping("api/upload")
	public void addPerson(@RequestBody MultipartFile file) throws IOException {
		PersonDTO person = objectMapper.readValue(file.getBytes(), PersonDTO.class);
		repository.save(person);
	}

	@PostMapping("/{id}")
	public void deletePerson(@PathVariable String id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public Optional<PersonDTO> getPersonById(@PathVariable String id) {
		return repository.findById(id);
	}

	@GetMapping("/api/getJson/{personId}")
	public ResponseEntity<PersonDTO> downloadJson(@PathVariable String personId) {
		return repository.findById(personId)
				.map(dto -> ResponseEntity.ok().body(dto))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/api/getJson")
	public ResponseEntity<Page<PersonDTO>> getJson(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy
	) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		Page<PersonDTO> personPage = repository.findAll(pageable)
				.map(dto -> objectMapper.convertValue(dto, PersonDTO.class));

		return ResponseEntity.ok().body(personPage);
	}
}
