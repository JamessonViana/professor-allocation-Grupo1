package com.projet.professor.allocation.grupoJava.entity;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.projet.professor.allocation.grupoJava.enums.DayWeek;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Allocation {
	@JsonProperty(access = Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Professor professor;
	
	@JsonProperty(access = Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Course course;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayWeek day;
	
	@Column(name = "startHour", nullable = false)
	@Schema(example = "12:00:00", type = "string")
	private Time startHour;
 
	@Column(name = "endHour", nullable = false)
	@Schema(example = "13:00:00", type = "string")
	private Time endHour;

	public Allocation() {
		
	}
	public Allocation(Long id, Professor professor, Course course, DayWeek day, Time startHour, Time endHour) {
		this.id = id;
		this.professor = professor;
		this.course = course;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}

	public DayWeek getDay() {
		return day;
	}

	public void setDay(DayWeek day) {
		this.day = day;
	}


	public Time getStartHour() {
		return startHour;
	}

	public void setStartHour(Time startHour) {
		this.startHour = startHour;
	}

	public Time getEndHour() {
		return endHour;
	}

	public void setEndHour(Time endHour) {
		this.endHour = endHour;
	}
	
	@Schema(example = "1", type = "Long")            
	public void setProfessorId(Long id) {
		Professor professor = new Professor();
		professor.setId(id);
		this.setProfessor(professor);
	}
	
	@Schema(example = "1", type = "Long")
	public void setCourseId(Long id) {
		Course course = new Course();
		course.setId(id);
		this.setCourse(course);
	}

	@Override
	public String toString() {
		return "Allocation [id=" + id + ", professor=" + professor + ", course=" + course + ", day=" + day
				+ ", startHour=" + startHour + ", endHour=" + endHour + "]";
	}

	 
}