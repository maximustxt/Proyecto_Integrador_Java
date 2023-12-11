package com.API.Expenses.services;

import com.API.Expenses.models.Expenses;

import java.util.List;

public interface ExpensesServices {
    List<Expenses> GetExpenses();
    Expenses GetDetailExpense(Long id);
    double GetExpensesTotal();
    Expenses GetMayorExpenses();
    String DeleteExpenses(Long id);
    String PostExpenses(Expenses expenses);
    String PutExpenses(Expenses expenses , Long id);
    Long MetodoParaComprobarSiExisteElID(Long id);
}
