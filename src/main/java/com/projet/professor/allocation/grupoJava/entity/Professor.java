package com.projet.professor.allocation.grupoJava.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Professor {
	@JsonProperty(access = Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@Column(length = 70,nullable = false)
	private String name;
	
	@Column(length = 11,nullable = false, unique = true)
	private String cpf;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Department department;

	public Professor() {

	}

	public Professor(Long id, String name, String cpf) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartmentId(Long id) {
		Department department = new Department();
		department.setId(id);
		this.setDepartment(department);
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", name=" + name + ", cpf=" + cpf + ", department=" + department + "]";
	}
	
	
	
}
