package com.nit.repo;




import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
 Page<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrCourseContainingIgnoreCase(
         String name, String email, String course, Pageable pageable);
}

