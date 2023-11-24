package by.academy.springboot.service;

import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.dto.UserDTO;
import by.academy.springboot.model.entity.enums.Role;

public interface RegistrationService {
    int save(UserDTO dto);
    String encrypt(CharSequence charSequence);
    boolean isNotReadyForSave(UserDTO dto);
    void deleteRole(UserDTO dto, Role role);
    public void addRole(UserDTO dto, Role role);
    Integer createUser(PersonDTO dto);
}
