package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.CategoryDTO;
import com.db.theaterinformationsystem.dto.EmployeeDTO;
import com.db.theaterinformationsystem.dto.FullNameDTO;
import com.db.theaterinformationsystem.model.Category;
import com.db.theaterinformationsystem.model.Employee;
import com.db.theaterinformationsystem.model.FullName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommonMapper {

    FullNameDTO map(FullName fullName);

    @Mapping(target = "employee", ignore = true)
    FullName map(FullNameDTO dto);

    CategoryDTO map(Category category);

    Category map(CategoryDTO dto);

    EmployeeDTO map(Employee employee);

    Employee map(EmployeeDTO dto);

}
