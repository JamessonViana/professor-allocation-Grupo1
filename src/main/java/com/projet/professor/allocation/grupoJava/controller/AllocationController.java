package com.projet.professor.allocation.grupoJava.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.professor.allocation.grupoJava.entity.Allocation;
import com.projet.professor.allocation.grupoJava.entity.Course;
import com.projet.professor.allocation.grupoJava.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Allocation")

@RestController

@RequestMapping(path = "/allocation")
public class AllocationController {
	
	private final AllocationService service;

	public AllocationController(AllocationService service) {
		this.service = service;
	}
	
 
	@Operation(summary = "Liste todas as alocações")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocation = service.findAll();
		return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Busque uma alocação")
	@GetMapping(path = "/{alocação_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({

			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Allocation> findById(@PathVariable(name = "alocação_id") Long id) {
		Allocation allocation = service.findById(id);
		if (allocation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		else {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		}
	}
	
	@Operation(summary = "Exclua uma alocação")
	@DeleteMapping(path = "/{alocação_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "alocação_id") Long id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	

}
