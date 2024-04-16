package com.projet.professor.allocation.grupoJava.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.professor.allocation.grupoJava.entity.Course;
import com.projet.professor.allocation.grupoJava.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Course")

@RestController

@RequestMapping(path = "/courses")

public class CourseController {
	private final CourseService service;

	public CourseController(CourseService service) {

		super();
		this.service = service;
	}

	@Operation(summary = "Liste todos os cursos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({

			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<List<Course>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Course> course = service.findAll(name);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@Operation(summary = "Busque um curso")
	@GetMapping(path = "/{curso_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({

			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Course> findById(@PathVariable(name = "curso_id") Long id) {
		Course course = service.findById(id);
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		else {
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
	}

	@Operation(summary = "Crie um curso")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({

			@ApiResponse(responseCode = "201", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Course> save(@RequestBody Course course) {
		try {
			Course cours = service.save(course);
			return new ResponseEntity<>(cours, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Altere um curso")
	@PutMapping(path = "/{curso_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Course> updat(@PathVariable(name = "curso_id") Long id, @RequestBody Course course) {
		course.setId(id);
		try {

			Course cours = service.update(course);
			if (cours == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(cours, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Exclua um curso")
	@DeleteMapping(path = "/{curso_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "curso_id") Long id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}