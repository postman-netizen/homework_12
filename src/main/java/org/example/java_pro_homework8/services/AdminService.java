package org.example.java_pro_homework8.services;

import jakarta.transaction.Transactional;
import org.example.java_pro_homework8.model.Admin;
import org.example.java_pro_homework8.repo.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Transactional
    public void addAdmin(String login, String password , String role) {
        Admin admin = adminRepository.findByLogin(login);
        if(admin==null) {
            admin = new Admin();
            admin.setLogin(login);
            admin.setPassword(password);
            admin.setRole(role);
            adminRepository.save(admin);
        }else{
            throw new RuntimeException("Admin already exists");
        }
    }

    @Transactional
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }


}
