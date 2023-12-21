package com.API.Expenses.repository;

import com.API.Expenses.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceUsersRepository extends JpaRepository<Users , Long> {
}
