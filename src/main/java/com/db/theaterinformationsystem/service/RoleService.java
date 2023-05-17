package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.RoleCreateDTO;
import com.db.theaterinformationsystem.dto.RoleDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
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
    private final AttributeService attributeService;

    @Transactional
    public Long save(RoleCreateDTO dto) {
        Role role = roleMapper.map(dto);
        Role newRole = roleRepository.save(role);
        attributeService.setDesAttribute(newRole, dto.getDescription());
        return newRole.getId();
    }

    @Transactional
    public Long update(RoleCreateDTO dto) {
        Role role = roleMapper.map(dto);
        Role newRole = roleRepository.save(role);
        attributeService.updateDesAttribute(newRole, dto.getDescription());
        return newRole.getId();
    }

    public RoleCreateDTO find(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        RoleCreateDTO dto = roleMapper.mapRole(role);
        dto.setDescription(attributeService.findAttributeValue(role, "описание роли"));
        return dto;
    }

    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::map).collect(Collectors.toList());
    }
}