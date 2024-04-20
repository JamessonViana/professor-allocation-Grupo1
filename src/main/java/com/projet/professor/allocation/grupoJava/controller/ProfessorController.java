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

import com.projet.professor.allocation.grupoJava.entity.Professor;
import com.projet.professor.allocation.grupoJava.service.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professors")
@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
	private final ProfessorService service;

	public ProfessorController(ProfessorService service) {
		super();
		this.service = service;
	}

	@Operation(summary = "Liste todos professores")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<Professor> professors = service.findAll(name);
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}

	@Operation(summary = "Busque um professor")
	@GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
		Professor professor = service.findById(id);
		if (professor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}

	@Operation(summary = "Crie um professor")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<Professor> save(@RequestBody Professor professor) {
		try {
			Professor prof = service.save(professor);
			return new ResponseEntity<>(prof, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Altere um professor")
	@PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
	public ResponseEntity<Professor> updat(@PathVariable(name = "professor_id") Long id,
			@RequestBody Professor professor) {
		professor.setId(id);
		try {

			Professor prof = service.update(professor);
			if (prof == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(prof, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Exclua um professor")
	@DeleteMapping(path = "/{professor_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "professor_id") Long id) {
		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Operation(summary = "Liste os professores pelo Id do departamento")
	@GetMapping(path = "/department/{department_id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
	public ResponseEntity<List<Professor>> findbyDepartmanet(@RequestParam(name = "departamen_id", required = false) Long id) {
		List<Professor> professors = service.findByDepartment(id);
		return new ResponseEntity<>(professors, HttpStatus.OK);
	}
	
 
	
}
 
