package com.example.be.repository;

import com.example.be.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT  e FROM Employee e WHERE  e.fullname LIKE :fullname AND e.phone LIKE :phone")
    Page<Employee> findAll(@Param("fullname")String fullname, @Param("phone")String phone, Pageable pageable);
    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.employeeId = :id")
    void deleteEmployeeById(@Param("id") int id);

    @Query("SELECT e FROM Employee e WHERE e.employeeId = :id")
    Employee findEmployeeById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "insert into employee(employee_iden, image, birth_day, gender, " +
            "fullname, id_card, email, phone, address) values (:employee_iden, :image," +
            ":birth_day, :gender, :fullname, :id_card, :email, :phone, :address)", nativeQuery = true)
    void createNewEmployee(@Param("employee_iden") String employeeIden, @Param("image") String image, @Param("birth_day") Date birthday,
                           @Param("gender") boolean gender, @Param("fullname") String fullname, @Param("id_card") String idCard, @Param("email") String email,
                           @Param("phone") String phone, @Param("address") String address);

    @Modifying
    @Transactional
    @Query("update Employee e set e.employeeIden = :employeeIden, e.image = :image, e.birthday = :birthday, e.gender = :gender, " +
            "e.fullname = :fullname, e.idCard = :idCard, e.email = :email, e.phone = :phone, e.address = :address where e.employeeId = :employeeId")
    void updateEmployee(@Param("employeeId") int employeeId, @Param("employeeIden") String employeeIden, @Param("image") String image, @Param("birthday") Date birthday,
                        @Param("gender") boolean gender, @Param("fullname") String fullname, @Param("idCard") String idCard, @Param("email") String email,
                        @Param("phone") String phone, @Param("address") String address);
}
