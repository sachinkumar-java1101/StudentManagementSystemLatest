package com.nit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Student {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @NotBlank(message = "Name is required")
 private String name;

 @Email @NotBlank(message = "Email is required")
 private String email;

 @NotBlank(message = "Course is required")
 private String course;

 @Min(1) @Max(120)
 private int age;

 // getters & setters
 public Long getId() { return id; }
 public void setId(Long id) { this.id = id; }
 public String getName() { return name; }
 public void setName(String name) { this.name = name; }
 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }
 public String getCourse() { return course; }
 public void setCourse(String course) { this.course = course; }
 public int getAge() { return age; }
 public void setAge(int age) { this.age = age; }
}
