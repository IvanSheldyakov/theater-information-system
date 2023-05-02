package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Список работников по половому признаку
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic" +
            " FROM Employee e JOIN EmployeeAttribute ea ON e.id = ea.employee.id WHERE ea.attribute.attribute = 'пол' AND ea.value.value = :sex")
    List<Map<String, String>> findEmployeesBySex(@Param("sex") String sex);

    // Список работников по году рождения
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic" +
            " FROM Employee e WHERE e.birthYear = :birthYear")
    List<Map<String, String>> findEmployeesByBirthYear(@Param("birthYear") Integer birthYear);

    // Список работников по возрасту
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic" +
            " FROM Employee e JOIN EmployeeAttribute ea ON e.id = ea.employee.id WHERE ea.attribute.attribute = 'возраст' AND ea.value.value = :age")
    List<Map<String, String>> findEmployeesByAge(@Param("age") String age);

    // Список работников по наличию детей
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic" +
            " FROM Employee e WHERE e.children = :hasChildren")
    List<Map<String, String>> findEmployeesByChildren(@Param("hasChildren") Boolean hasChildren);

    // Список работников по кол-ву детей
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic" +
            " FROM Employee e WHERE e.childrenNumber = :childrenNumber")
    List<Map<String, String>> findEmployeesByChildrenNumber(@Param("childrenNumber") Integer childrenNumber);

    // Список работников по размеру зарплаты
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic" +
            " FROM Employee e WHERE e.payment = :payment")
    List<Map<String, String>> findEmployeesByPayment(@Param("payment") BigDecimal payment);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic FROM Employee e")
    List<Map<String, String>> findAllEmployees();

    // Количество работников с определенным стажем
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.standing = :standing")
    long countEmployeesByStanding(@Param("standing") String standing);

    // Список работников с определенным стажем
    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic FROM Employee e WHERE e.standing = :standing")
    List<Map<String, String>> findEmployeesByStanding(@Param("standing") String standing);

}