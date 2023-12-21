package com.API.Expenses.services.Impl;

import com.API.Expenses.DTO.UsersDTO;
import com.API.Expenses.models.Expenses;
import com.API.Expenses.models.Users;
import com.API.Expenses.repository.InterfaceUsersRepository;
import com.API.Expenses.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServicesImpl implements UsersServices {

    @Autowired
    private InterfaceUsersRepository interfaceUsersRepository;

    @Override
    public List<UsersDTO> GetUsers() {
        List<Users> listUsers = interfaceUsersRepository.findAll();
        List<UsersDTO> listUsersDTO = listUsers.stream()
                .map(user -> {
                    // Mapear a UsersDTO
                    return new UsersDTO(user.getId(), user.getGmail(), user.getNombre(), user.getEdad() , user.getExpenses());
                })
                .toList();
        return listUsersDTO;
    }

    @Override
    public UsersDTO GetDetailUser(Long id) {
        UsersDTO detailUserDTO = new UsersDTO();

        List<Users> listUsers = interfaceUsersRepository.findAll();

        for(int i = 0; i<listUsers.size(); i++){
            if(listUsers.get(i).getId().equals(id)){
                detailUserDTO.setNombre(listUsers.get(i).getNombre());
                detailUserDTO.setGmail(listUsers.get(i).getGmail());
                detailUserDTO.setEdad(listUsers.get(i).getEdad());
                detailUserDTO.setId(listUsers.get(i).getId());
                detailUserDTO.setExpenses(listUsers.get(i).getExpenses());
            }
        }

        return detailUserDTO;
    }

    @Override
    public String DeleteUsers(Long id) {
        interfaceUsersRepository.deleteById(id);
        return "El usuario fue eliminado con exito!";
    }

    @Override
    public String PostUsers(Users user) {
        interfaceUsersRepository.save(user);
        return "El usuario fue agregado con exito!";
    }

    @Override
    public String PutUsers(Users user, Long id) {
        Users user1 = interfaceUsersRepository.findById(id).get();

        user1.setNombre(user.getNombre());
        user1.setGmail(user.getGmail());
        user1.setEdad(user.getEdad());
        interfaceUsersRepository.save(user1);
        return "El usuario fue actualizado con exito!";
    }

    @Override
    public Long MetodoParaComprobarSiExisteElID(Long id) {
        Users DetailUser = new Users();

        List<Users> listUsers = interfaceUsersRepository.findAll();

        for(int i = 0; i<listUsers.size(); i++){
            if(listUsers.get(i).getId().equals(id)){
                DetailUser = listUsers.get(i);
            }
        }
        return DetailUser.getId();
    }

}
