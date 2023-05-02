package com.db.theaterinformationsystem.mappers;

import com.db.theaterinformationsystem.dto.ActorDTO;
import com.db.theaterinformationsystem.dto.CategoryDTO;
import com.db.theaterinformationsystem.dto.EmployeeDTO;
import com.db.theaterinformationsystem.dto.FullNameDTO;
import com.db.theaterinformationsystem.model.Actor;
import com.db.theaterinformationsystem.model.Category;
import com.db.theaterinformationsystem.model.Employee;
import com.db.theaterinformationsystem.model.FullName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommonMapper {


    //@Mapping(source = "name", target = "name")
    FullNameDTO map(FullName fullName);

    //@Mapping(target = "id", ignore = true)
    FullName map(FullNameDTO dto);

    CategoryDTO map(Category category);

   // @Mapping(target = "id", ignore = true)
    Category map(CategoryDTO dto);

    EmployeeDTO map(Employee employee);

   //@Mapping(target = "id", ignore = true)
    Employee map(EmployeeDTO dto);

}
