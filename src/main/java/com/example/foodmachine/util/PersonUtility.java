package com.example.foodmachine.util;

import com.example.foodmachine.dom.PersonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@UtilityClass
public class PersonUtility {

	public static PersonDTO getPersonDTO(MultipartFile file, ObjectMapper objectMapper) throws IOException {
		return objectMapper.readValue(file.getBytes(), PersonDTO.class);
	}
}
