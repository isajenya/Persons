package com.example.foodmachine.dom;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "persons")
public class PersonDTO {
	@Id
	private String id;
	private String name;
	private String description;
	private int age;
}
