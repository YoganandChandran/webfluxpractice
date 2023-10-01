package com.example.webfluxpractice.entity;


import java.util.Objects;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name ="users")
public class Users {
	@Id
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, department, id, name, salary);
	}
	public Users() {
		super();
	}
	public Users(int id, String name, int age, Double salary, String department) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.department = department;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return age == other.age && Objects.equals(department, other.department) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(salary, other.salary);
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	private int age;
	private Double salary;
	private String department;
}
