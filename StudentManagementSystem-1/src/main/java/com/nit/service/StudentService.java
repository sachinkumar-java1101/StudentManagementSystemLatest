package com.nit.service;


import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.nit.entity.Student;
import com.nit.repo.StudentRepository;

@Service
public class StudentService {
 private final StudentRepository repo;

 public StudentService(StudentRepository repo) { this.repo = repo; }

 public Page<Student> search(String q, Pageable pageable) {
     String term = q == null ? "" : q.trim();
     if (term.isEmpty()) return repo.findAll(pageable);
     return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCourseContainingIgnoreCase(
             term, term, term, pageable);
 }

 public Student get(Long id) { return repo.findById(id).orElseThrow(); }
 public Student save(Student s) { return repo.save(s); }
 public void delete(Long id) { repo.deleteById(id); }
}
