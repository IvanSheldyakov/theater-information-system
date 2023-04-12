package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Employee e")
    List<Map<String, String>> findAllEmployees();

    long count();

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.standing = ?1")
    long countByStanding(String standing);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Employee e WHERE e.standing = ?1")
    List<Map<String, String>> findAllByStanding(String standing);

}