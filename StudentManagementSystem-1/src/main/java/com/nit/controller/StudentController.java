package com.nit.controller;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.nit.entity.Student;
import com.nit.service.StudentService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

 private final StudentService service;

 public StudentController(StudentService service) { this.service = service; }

 @GetMapping
 public String list(
         @RequestParam(defaultValue = "") String q,
         @PageableDefault(size = 6, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
         Model model) {

     Page<Student> page = service.search(q, pageable);
     model.addAttribute("page", page);
     model.addAttribute("q", q);
     return "students/list";
 }

 @GetMapping("/new")
 public String createForm(Model model) {
     model.addAttribute("student", new Student());
     model.addAttribute("title", "Add New Student");
     return "students/form";
 }

 @PostMapping
 public String create(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
     if (result.hasErrors()) {
         model.addAttribute("title", "Add New Student");
         return "students/form";
     }
     service.save(student);
     return "redirect:/students";
 }

 @GetMapping("/{id}/edit")
 public String editForm(@PathVariable Long id, Model model) {
     model.addAttribute("student", service.get(id));
     model.addAttribute("title", "Edit Student");
     return "students/form";
 }

 @PostMapping("/{id}")
 public String update(@PathVariable Long id,
                      @Valid @ModelAttribute("student") Student student,
                      BindingResult result, Model model) {
     if (result.hasErrors()) {
         model.addAttribute("title", "Edit Student");
         return "students/form";
     }
     student.setId(id);
     service.save(student);
     return "redirect:/students";
 }

 @PostMapping("/{id}/delete")
 public String delete(@PathVariable Long id) {
     service.delete(id);
     return "redirect:/students";
 }
}
