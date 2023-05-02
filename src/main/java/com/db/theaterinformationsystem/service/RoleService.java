package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.RoleDTO;
import com.db.theaterinformationsystem.mappers.RoleMapper;
import com.db.theaterinformationsystem.model.Role;
import com.db.theaterinformationsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    @Transactional
    public Long save(RoleDTO dto) {
        Role role = roleMapper.map(dto);
        Role newRole = roleRepository.save(role);
        return newRole.getId();
    }

    public RoleDTO find(Long id) {
        return roleMapper.map(roleRepository.findById(id).orElse(null));
    }

    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::map).collect(Collectors.toList());
    }
}