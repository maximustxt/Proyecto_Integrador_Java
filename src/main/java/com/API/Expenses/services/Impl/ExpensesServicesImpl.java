package com.API.Expenses.services.Impl;

import com.API.Expenses.ExceptionCustom.ModelNotFoundException;
import com.API.Expenses.models.Expenses;
import com.API.Expenses.repository.InterfaceExpensesRepository;
import com.API.Expenses.services.ExpensesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesServicesImpl implements ExpensesServices {

    @Autowired
    private InterfaceExpensesRepository interfaceExpensesRepository;

    @Override
    public List<Expenses> GetExpenses() {
        return interfaceExpensesRepository.findAll();
    }


    @Override
    public Expenses GetDetailExpense(Long id) {
        Expenses DetailExpenses = new Expenses();

        List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

        for(int i = 0; i<listExpenses.size(); i++){
            if(listExpenses.get(i).getId() == id){
                DetailExpenses = listExpenses.get(i);
            }
        }

        return DetailExpenses;
    }



    @Override
    public double GetExpensesTotal() {

     double TotalGastos = 0.0;

     List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

     for(int i = 0; i<listExpenses.size(); i++){
         TotalGastos = TotalGastos + listExpenses.get(i).getAmount();
     }
        return TotalGastos;
    }

    @Override
    public Expenses GetMayorExpenses() {

      double MayorExpenses = 0;

      Expenses mayorExpenses = new Expenses();

      List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

        for(int i = 0; i<listExpenses.size(); i++){
            if(MayorExpenses < listExpenses.get(i).getAmount()){
                mayorExpenses.setDate(listExpenses.get(i).getDate());
                mayorExpenses.setAmount(listExpenses.get(i).getAmount());
                mayorExpenses.setCategory(listExpenses.get(i).getCategory());
                mayorExpenses.setId(listExpenses.get(i).getId());
            }
        }

        return mayorExpenses;
    }

    @Override
    public String DeleteExpenses(Long id) {
        interfaceExpensesRepository.deleteById(id);
        return "El gasto fue eliminado con exito!";
    }

    @Override
    public String PostExpenses(Expenses expenses) {
        interfaceExpensesRepository.save(expenses);
        return "El gasto fue agregado con exito!";
    }

    @Override
    public String PutExpenses(Expenses expenses, Long id) {
     Expenses expenses1 = interfaceExpensesRepository.findById(id).get();

         expenses1.setAmount(expenses.getAmount());
         expenses1.setCategory(expenses.getCategory());
         expenses1.setDate(expenses.getDate());
         interfaceExpensesRepository.save(expenses1);
         return "El gasto fue actualizado con exito!";

    }

    @Override
    public Long MetodoParaComprobarSiExisteElID(Long id) {
        Expenses DetailExpenses = new Expenses();

        List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

        for(int i = 0; i<listExpenses.size(); i++){
            if(listExpenses.get(i).getId() == id){
                DetailExpenses = listExpenses.get(i);
            }
        }

        return DetailExpenses.getId();
    }
}
