package org.example.java_pro_homework8.utils;

import org.example.java_pro_homework8.model.Admin;
import org.example.java_pro_homework8.repo.AdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService {
    private final AdminRepository adminRepository;

    public CustomUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByLogin(login);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found: " + login);
        }
        return User.builder()
                .username(admin.getLogin())
                .password(admin.getPassword())
                .build();
    }
}
