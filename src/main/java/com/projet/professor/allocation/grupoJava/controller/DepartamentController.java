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

import com.projet.professor.allocation.grupoJava.entity.Department;
import com.projet.professor.allocation.grupoJava.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Departament")

@RestController
@RequestMapping(path = "/departaments")
public class DepartamentController {
	private final DepartmentService service;

	public DepartamentController(DepartmentService service) {
		super();
		this.service = service;

	}

	@Operation(summary = "Liste todos departamentos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({

			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })

	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "name", required = false) String name) {

		List<Department> departament = service.findAll(name);
		return new ResponseEntity<>(departament, HttpStatus.OK);
	}

	@Operation(summary = "Busque um departamento")
	@GetMapping(path = "/{departament_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
	public ResponseEntity<Department> findById(@PathVariable(name = "departament_id") Long id) {
		Department departament = service.findById(id);
		if (departament == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(departament, HttpStatus.OK);
		}
	}

	@Operation(summary = "Crie um departamento")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
	public ResponseEntity<Department> save(@RequestBody Department department) {
		try {
			Department dept = service.save(department);
			return new ResponseEntity<>(dept, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Altere um departamento")
	@PutMapping(path = "/{departament_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),

			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
	public ResponseEntity<Department> updat(@PathVariable(name = "departament_id") Long id,
			@RequestBody Department department) {
		department.setId(id);
		try {

			Department dept = service.update(department);
			if (dept == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {

				return new ResponseEntity<>(dept, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Exclua um professor")
	@DeleteMapping(path = "/{departament_id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "departament_id") Long id) {

		service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}