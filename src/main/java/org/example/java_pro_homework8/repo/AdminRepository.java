package org.example.java_pro_homework8.repo;

import org.example.java_pro_homework8.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByLogin(String login);
}
