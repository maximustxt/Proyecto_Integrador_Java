package com.API.Expenses.repository;

import com.API.Expenses.models.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceExpensesRepository extends JpaRepository<Expenses , Long> {
}
