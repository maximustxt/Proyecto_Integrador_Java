package com.API.Expenses.controllers;

import com.API.Expenses.DTO.ExpensesDTO;
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


    //* RUTA PARA AGREGAR GASTO :

    @PostMapping
    public ResponseEntity<String> PostExpenses(@RequestBody Expenses expenses){
        if(expenses.getAmount() == null || expenses.getDate() == null || expenses.getCategory() == null  ) {
            throw  new ModelNotFoundException("Faltan datos para poder crear un gasto 😐!");
        } else {
            return ResponseEntity.ok(expensesServices.PostExpenses(expenses));
        }
    }

    //* RUTA PARA OBTENER LOS GASTOS :

    @GetMapping
    public ResponseEntity<List<ExpensesDTO>> GetExpenses(){
        return ResponseEntity.ok(expensesServices.GetExpenses());
    }

    //* RUTA PARA OBTENER EL TOTAL DE LOS GASTOS :

    @GetMapping("/TotalExpenses")
    public ResponseEntity<Double> GetExpensesTotal(){
        return ResponseEntity.ok(expensesServices.GetExpensesTotal());
    }

    //* RUTA PARA OBTENER EL MAYOR GASTO :

    @GetMapping("/MayorExpenses")
    public ResponseEntity<ExpensesDTO> GetMayorExpense(){
        return ResponseEntity.ok(expensesServices.GetMayorExpenses());
    }

    //* RUTA PARA OBTENER EL DETAIL :

    @GetMapping("/Detail/{id}")
    public ResponseEntity<ExpensesDTO> GetDetailExpense(@PathVariable Long id){
        if(expensesServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun gasto con esta característica 😐!");
        } else {
            return ResponseEntity.ok(expensesServices.GetDetailExpense(id));
        }
    }

    //* RUTA PARA ELIMINAR EL GASTO :

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteExpenses(@PathVariable Long id){
       if(expensesServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun gasto con esta característica 😐!");
        } else {
           return ResponseEntity.ok(expensesServices.DeleteExpenses(id));
       }
    }


    //* RUTA PARA ACTUALIZAR EL GASTO :

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
