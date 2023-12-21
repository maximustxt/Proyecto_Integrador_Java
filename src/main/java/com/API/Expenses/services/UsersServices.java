package com.API.Expenses.services;

import com.API.Expenses.DTO.UsersDTO;
import com.API.Expenses.models.Users;
import java.util.List;

public interface UsersServices {
    List<UsersDTO> GetUsers();
    UsersDTO GetDetailUser(Long id);
    String DeleteUsers(Long id);
    String PostUsers(Users user);
    String PutUsers(Users user , Long id);
    Long MetodoParaComprobarSiExisteElID(Long id);
}
