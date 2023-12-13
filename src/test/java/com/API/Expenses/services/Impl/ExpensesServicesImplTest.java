package com.API.Expenses.services.Impl;

import com.API.Expenses.DTO.ExpensesDTO;
import com.API.Expenses.models.Expenses;
import com.API.Expenses.repository.InterfaceExpensesRepository;
import com.API.Expenses.services.ExpensesServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExpensesServicesImplTest {


    @Mock
    private InterfaceExpensesRepository interfaceExpensesRepository;

    @Mock
    //* Esta modificación usa ArgumentCaptor para capturar el objeto Expenses pasado a save. Le permite inspeccionar el objeto real y afirmar sus propiedades, asegurándose de que coincida con sus expectativas.
    ArgumentCaptor<Expenses> expensesCaptor;

    @InjectMocks
    private ExpensesServicesImpl expensesServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); //* Esto inicializa y permite el funcionamiento de @Mock , @InjectMocks , etc.
        expensesCaptor = ArgumentCaptor.forClass(Expenses.class);
    }

    @Test
    void getExpensesTest() {
        //* GIVEN :
        List<Expenses> expensesList = new ArrayList<>();
        expensesList.add(new Expenses(1L, 100.0, "Food", "2023-12-13"));
        expensesList.add(new Expenses(2L, 50.0, "Transportation", "2023-12-14"));


        //* WHEN :
        when(interfaceExpensesRepository.findAll()).thenReturn(expensesList);
        List<ExpensesDTO> result = expensesServices.GetExpenses();


        //* THEN :
        assertEquals(2, result.size());
    }

    @Test
    void getDetailExpense() {
        //* GIVEN :
        ExpensesDTO expensesDetailDTO = new ExpensesDTO(1L, 500.5, "Zapatillas" , "13/12/2023");


        //* Lista Mock :
        List<Expenses> ListExpensesMock = new ArrayList<>();
        ListExpensesMock.add(new Expenses(1L, 500.5, "Zapatillas" , "13/12/2023"));
        ListExpensesMock.add(new Expenses(2L, 600.5, "Pantalones" , "13/12/2023"));
        ListExpensesMock.add(new Expenses(3L, 700.5, "Remeras" , "13/12/2023"));
        ListExpensesMock.add(new Expenses(4L, 800.5, "Zandalias" , "13/12/2023"));

        //* WHEN :
        when(interfaceExpensesRepository.findAll()).thenReturn(ListExpensesMock);
        ExpensesDTO result = expensesServices.GetDetailExpense(expensesDetailDTO.getId());


        //* THEN :
        assertEquals(expensesDetailDTO.getId(),result.getId());
        assertEquals(expensesDetailDTO.getAmount(),result.getAmount());
        assertEquals(expensesDetailDTO.getCategory(),result.getCategory());
        assertEquals(expensesDetailDTO.getDate(),result.getDate());
    }

    @Test
    void getExpensesTotal() {
        //* GIVEN :
        List<Expenses> ListExpensesMock = new ArrayList<>();
        ListExpensesMock.add(new Expenses(1L, 500.5, "Zapatillas" , "13/12/2023"));
        ListExpensesMock.add(new Expenses(2L, 600.5, "Pantalones" , "13/12/2023"));
        ListExpensesMock.add(new Expenses(3L, 700.5, "Remeras" , "13/12/2023"));
        ListExpensesMock.add(new Expenses(4L, 800.5, "Zandalias" , "13/12/2023"));

        double TotalExpenses = 0.0;

        for(int i = 0; i<ListExpensesMock.size(); i++){
            TotalExpenses = TotalExpenses + ListExpensesMock.get(i).getAmount();
        }


        //* WHERE :
        when(interfaceExpensesRepository.findAll()).thenReturn(ListExpensesMock);
        Double result = expensesServices.GetExpensesTotal();


        //* THEN :
        assertEquals(TotalExpenses , result);
    }

    @Test
    void getMayorExpenses() {
        // GIVEN
        List<Expenses> listExpensesMock = new ArrayList<>();
        listExpensesMock.add(new Expenses(1L, 500.5, "Zapatillas", "13/12/2023"));
        listExpensesMock.add(new Expenses(2L, 600.5, "Pantalones", "13/12/2023"));
        listExpensesMock.add(new Expenses(3L, 700.5, "Remeras", "13/12/2023"));
        listExpensesMock.add(new Expenses(4L, 800.5, "Zandalias", "13/12/2023"));

        ExpensesDTO expectedExpensesDTO = new ExpensesDTO();
        expectedExpensesDTO.setId(4L);
        expectedExpensesDTO.setAmount(800.5);
        expectedExpensesDTO.setCategory("Zandalias");
        expectedExpensesDTO.setDate("13/12/2023");

        // WHEN
        when(interfaceExpensesRepository.findAll()).thenReturn(listExpensesMock);
        ExpensesDTO result = expensesServices.GetMayorExpenses();

        // THEN
        assertEquals(expectedExpensesDTO.getId(), result.getId());
        assertEquals(expectedExpensesDTO.getAmount(), result.getAmount());
        assertEquals(expectedExpensesDTO.getCategory(), result.getCategory());
        assertEquals(expectedExpensesDTO.getDate(), result.getDate());
    }

    @Test
    void deleteExpenses() {
        //* GIVEN :
        String ResultadoQueDebeDevolver = "El gasto fue eliminado con exito!";
        Expenses expenses = new Expenses(1L, 500.5, "Zapatillas", "13/12/2023");

        //* WHEN :
        String result = expensesServices.DeleteExpenses(expenses.getId());

        //* THEN :
        //* Se verifica que el metodo DeleteById fue llamado y se le pasa el parametro esperado :
        verify(interfaceExpensesRepository).deleteById(expenses.getId());
        assertEquals(ResultadoQueDebeDevolver , result);
    }

    @Test
    void postExpenses() {
        //* GIVEN :
        String ResultadoQueDebeDevolver = "El gasto fue agregado con exito!";
        Expenses expenses = new Expenses(1L, 500.5, "Zapatillas", "13/12/2023");
        //* WHEN :
        String result = expensesServices.PostExpenses(expenses);
        //* THEN :
        verify(interfaceExpensesRepository).save(expenses);
        assertEquals(ResultadoQueDebeDevolver , result);
    }

    @Test
    void putExpenses() {
        // GIVEN
        Long idToUpdate = 1L;
        Expenses existingExpenses = new Expenses(idToUpdate, 500.5, "Zapatillas", "13/12/2023");
        Expenses updatedExpenses = new Expenses(idToUpdate, 600.5, "Pantalones", "14/12/2023");

        when(interfaceExpensesRepository.findById(eq(idToUpdate))).thenReturn(Optional.of(existingExpenses));


        // WHEN
        String result = expensesServices.PutExpenses(updatedExpenses, idToUpdate);

        // THEN
        verify(interfaceExpensesRepository).findById(eq(idToUpdate));
        verify(interfaceExpensesRepository).save(expensesCaptor.capture());

        // Check if the update was successful
        assertEquals("El gasto fue actualizado con exito!", result);

        // Assert on the captured Expenses object
        Expenses capturedExpenses = expensesCaptor.getValue();
        assertEquals(updatedExpenses.getAmount(), capturedExpenses.getAmount());
        assertEquals(updatedExpenses.getCategory(), capturedExpenses.getCategory());
        assertEquals(updatedExpenses.getDate(), capturedExpenses.getDate());
    }

    @Test
    void metodoParaComprobarSiExisteElID() {
        //* GIVEN :
        Long Id = 2L;
        List<Expenses> listExpensesMock = new ArrayList<>();
        listExpensesMock.add(new Expenses(1L, 500.5, "Zapatillas", "13/12/2023"));
        listExpensesMock.add(new Expenses(2L, 600.5, "Pantalones", "13/12/2023"));
        listExpensesMock.add(new Expenses(3L, 700.5, "Remeras", "13/12/2023"));
        listExpensesMock.add(new Expenses(4L, 800.5, "Zandalias", "13/12/2023"));

        //* WHEN :
        when(interfaceExpensesRepository.findAll()).thenReturn(listExpensesMock);
        Long result = expensesServices.MetodoParaComprobarSiExisteElID(Id);

        //* THEN :
        assertEquals(Id , result);
    }
}