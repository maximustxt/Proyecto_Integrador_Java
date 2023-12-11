package com.API.Expenses.controllers;

import com.API.Expenses.ExceptionCustom.ModelNotFoundException;
import com.API.Expenses.models.Expenses;
import com.API.Expenses.services.ExpensesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Expenses")
public class ExpensesController {

    @Autowired
    private ExpensesServices expensesServices;


    @PostMapping
    public ResponseEntity<String> PostExpenses(@RequestBody Expenses expenses){
        if(expenses.getAmount() == null || expenses.getDate() == null || expenses.getCategory() == null  ) {
            throw  new ModelNotFoundException("Faltan datos para poder crear un gasto 😐!");
        } else {
            return ResponseEntity.ok(expensesServices.PostExpenses(expenses));
        }
    }


    @GetMapping
    public ResponseEntity<List<Expenses>> GetExpenses(){
        return ResponseEntity.ok(expensesServices.GetExpenses());
    }


    @GetMapping("/TotalExpenses")
    public ResponseEntity<Double> GetExpensesTotal(){
        return ResponseEntity.ok(expensesServices.GetExpensesTotal());
    }



    @GetMapping("/MayorExpenses")
    public ResponseEntity<Expenses> GetMayorExpense(){
        return ResponseEntity.ok(expensesServices.GetMayorExpenses());
    }



    @GetMapping("/Detail/{id}")
    public ResponseEntity<Expenses> GetMayorExpense(@PathVariable Long id){
        if(expensesServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun gasto con esta característica 😐!");
        } else {
            return ResponseEntity.ok(expensesServices.GetDetailExpense(id));
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteExpenses(@PathVariable Long id){
       if(expensesServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun gasto con esta característica 😐!");
        } else {
           return ResponseEntity.ok(expensesServices.DeleteExpenses(id));
       }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> PostExpenses(@RequestBody Expenses expenses,@PathVariable Long id){
        if(expensesServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun gasto con esta característica 😐!");
        } else if(expenses.getAmount() == null || expenses.getDate() == null || expenses.getCategory() == null  ) {
            throw  new ModelNotFoundException("Faltan datos para poder actualizar un gasto 😐!");
        } else {
            return ResponseEntity.ok(expensesServices.PutExpenses(expenses , id));
        }
    }
}
