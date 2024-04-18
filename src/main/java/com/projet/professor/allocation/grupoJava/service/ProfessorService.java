package com.projet.professor.allocation.grupoJava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.professor.allocation.grupoJava.entity.Department;
import com.projet.professor.allocation.grupoJava.entity.Professor;
import com.projet.professor.allocation.grupoJava.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	
	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll(String name) {
		if (name == null) {
			return professorRepository.findAll();
		} else {
			return professorRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}
	public List<Professor> findByDepartment(Long departmentId) {
		Department department = new Department();
		department.setId(departmentId);
		return professorRepository.findByDepartment(department);
	}
//
	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	private Professor saveInternal(Professor professor) {
		professor = professorRepository.save(professor);

		Department department = departmentService.findById(professor.getDepartment().getId());
		professor.setDepartment(department);

		return professor;
	}
}