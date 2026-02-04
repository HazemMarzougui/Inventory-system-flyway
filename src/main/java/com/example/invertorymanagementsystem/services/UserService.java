package com.example.invertorymanagementsystem.services;

import com.example.invertorymanagementsystem.dtos.UserDTO;
import com.example.invertorymanagementsystem.entities.Role;
import com.example.invertorymanagementsystem.entities.User;
import com.example.invertorymanagementsystem.repositories.RoleRepository;
import com.example.invertorymanagementsystem.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // CREATE USER
    public UserDTO createUser(UserDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        // 🔑 HASH PASSWORD
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Assign roles
        Set<Role> roles = new HashSet<>(roleRepository.findByNameIn(dto.getRoles()));
        if (roles.isEmpty()) {
            throw new RuntimeException("Invalid roles provided");
        }
        user.setRoles(roles);

        User saved = userRepository.save(user);

        return toDTO(saved);
    }


    // ADMIN ENDPOINT - CREATE STAFF USER
    public UserDTO createStaff(UserDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role staffRole = roleRepository.findByName("ROLE_STAFF")
                .orElseThrow(() -> new RuntimeException("ROLE_STAFF not found"));

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRoles(Set.of(staffRole));

        return toDTO(userRepository.save(user));
    }


    /// ADMIN ENDPOINT - PROMOTE USER TO ADMIN
    public void promoteToAdmin(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        user.getRoles().add(adminRole);
    }


    // GET ALL USERS
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // HELPER: Convert User to UserDTO (output)
    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        // Never send password back
        dto.setPassword(null);
        dto.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}
