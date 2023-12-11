package com.API.Expenses.services.Impl;

import com.API.Expenses.DTO.ExpensesDTO;
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


    //* OBTENER TODOS LOS GASTOS :


    @Override
    public List<ExpensesDTO> GetExpenses() {
        List<Expenses> listExpenses = interfaceExpensesRepository.findAll();
        List<ExpensesDTO> ListExpensesDTO = listExpenses.stream().map(expenses -> new ExpensesDTO(expenses.getId(),expenses.getAmount() , expenses.getCategory() , expenses.getDate())).toList();
        return ListExpensesDTO;
    }


    //* OBTENER DETALLE DEL GASTO :


    @Override
    public ExpensesDTO GetDetailExpense(Long id) {
        ExpensesDTO detailExpensesDTO = new ExpensesDTO();

        List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

        for(int i = 0; i<listExpenses.size(); i++){
            if(listExpenses.get(i).getId() == id){
                detailExpensesDTO.setCategory(listExpenses.get(i).getCategory());
                detailExpensesDTO.setDate(listExpenses.get(i).getDate());
                detailExpensesDTO.setAmount(listExpenses.get(i).getAmount());
                detailExpensesDTO.setId(listExpenses.get(i).getId());
            }
        }

        return detailExpensesDTO;
    }


    //* OBTENER EL TOTAL DE LOS GASTOS :

    @Override
    public double GetExpensesTotal() {

     double TotalGastos = 0.0;

     List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

     for(int i = 0; i<listExpenses.size(); i++){
         TotalGastos = TotalGastos + listExpenses.get(i).getAmount();
     }
        return TotalGastos;
    }


    //* OBTENER EL MAYOR GASTO :

    @Override
    public ExpensesDTO GetMayorExpenses() {

      double MayorExpenses = 0;

      ExpensesDTO mayorExpensesDTO = new ExpensesDTO();

      List<Expenses> listExpenses = interfaceExpensesRepository.findAll();

        for(int i = 0; i<listExpenses.size(); i++){
            if(MayorExpenses < listExpenses.get(i).getAmount()){
                mayorExpensesDTO.setDate(listExpenses.get(i).getDate());
                mayorExpensesDTO.setAmount(listExpenses.get(i).getAmount());
                mayorExpensesDTO.setCategory(listExpenses.get(i).getCategory());
                mayorExpensesDTO.setId(listExpenses.get(i).getId());
            }
        }
        return mayorExpensesDTO;
    }


    //* ELIMINAR LOS GASTOS :

    @Override
    public String DeleteExpenses(Long id) {
        interfaceExpensesRepository.deleteById(id);
        return "El gasto fue eliminado con exito!";
    }

    //* CREAR LOS GASTOS :

    @Override
    public String PostExpenses(Expenses expenses) {
        interfaceExpensesRepository.save(expenses);
        return "El gasto fue agregado con exito!";
    }


    //* ACTUALIZAR LOS GASTOS :

    @Override
    public String PutExpenses(Expenses expenses, Long id) {
     Expenses expenses1 = interfaceExpensesRepository.findById(id).get();

         expenses1.setAmount(expenses.getAmount());
         expenses1.setCategory(expenses.getCategory());
         expenses1.setDate(expenses.getDate());
         interfaceExpensesRepository.save(expenses1);
         return "El gasto fue actualizado con exito!";

    }

    //* METODO PARA VERIFICAR ERRORES :

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
