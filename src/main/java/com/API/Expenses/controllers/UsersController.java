package com.API.Expenses.controllers;


import com.API.Expenses.DTO.UsersDTO;
import com.API.Expenses.ExceptionCustom.ModelNotFoundException;
import com.API.Expenses.models.Expenses;
import com.API.Expenses.models.Users;
import com.API.Expenses.services.ExpensesServices;
import com.API.Expenses.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private ExpensesServices expensesServices;


    //* RUTA PARA AGREGAR USUARIO :

    @PostMapping
    public ResponseEntity<String> PostUsers(@RequestBody Users user){
        if(user.getEdad() == 0 || user.getGmail() == null || user.getNombre() == null  ) {
            throw  new ModelNotFoundException("Faltan datos para poder crear un usuario 😐!");
        } else {
            return ResponseEntity.ok(usersServices.PostUsers(user));
        }
    }


    //* RUTA PARA OBTENER LOS USUARIOS :

    @GetMapping
    public ResponseEntity<List<UsersDTO>> GetUsers(){
        System.out.println(usersServices.GetUsers());
        return ResponseEntity.ok(usersServices.GetUsers());
    }

    //* RUTA PARA OBTENER EL DETAIL :

    @GetMapping("/Detail/{id}")
    public ResponseEntity<UsersDTO> GetDetailUser(@PathVariable Long id){
        if(usersServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun usuario con esta característica 😐!");
        } else {
            return ResponseEntity.ok(usersServices.GetDetailUser(id));
        }
    }

    @GetMapping("/ExpensesUser/{id}")
    public ResponseEntity<List<Expenses>> GetExpensesUser(@PathVariable Long id){
        UsersDTO usuarioDTO = usersServices.GetDetailUser(id);

        if (usuarioDTO != null && usuarioDTO.getId() != null) {
            List<Expenses> gastos = expensesServices.obtenerGastosPorUsuario(usuarioDTO);
            return new ResponseEntity<>(gastos, HttpStatus.OK);
        } else {
            throw  new ModelNotFoundException("No se encontro ningun usuario con esta característica 😐!");
        }
    }


    //* RUTA PARA ELIMINAR EL USUARIO :

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable Long id){
        if(usersServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun usuario con esta característica 😐!");
        } else {
            return ResponseEntity.ok(usersServices.DeleteUsers(id));
        }
    }


    //* RUTA PARA ACTUALIZAR EL USUARIO :

    @PutMapping("/{id}")
    public ResponseEntity<String> PutUser(@RequestBody Users user,@PathVariable Long id){
        if(usersServices.MetodoParaComprobarSiExisteElID(id) == null){
            throw  new ModelNotFoundException("No se encontro ningun usuario con esta característica 😐!");
        } else if(user.getNombre() == null || user.getEdad() == 0 || user.getGmail() == null  ) {
            throw  new ModelNotFoundException("Faltan datos para poder actualizar el usuario 😐!");
        } else {
            return ResponseEntity.ok(usersServices.PutUsers(user , id));
        }
    }
}
