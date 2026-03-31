package com.project.webProject.services;

import com.project.webProject.entities.Role;
import com.project.webProject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // CREATE
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // READ - Get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // READ - Get role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // READ - Get role by name
    public Optional<Role> getRoleByName(Role.RoleName name) {
        return roleRepository.findByName(name);
    }

    // UPDATE
    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role existingRole = role.get();
            if (roleDetails.getName() != null) {
                existingRole.setName(roleDetails.getName());
            }
            return roleRepository.save(existingRole);
        }
        return null;
    }

    // DELETE
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    // DELETE all
    public void deleteAllRoles() {
        roleRepository.deleteAll();
    }
}

