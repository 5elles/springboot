package by.academy.springboot.service.impl;

import by.academy.springboot.dto.PersonDTO;
import by.academy.springboot.dto.UserDTO;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.UserMapper;
import by.academy.springboot.model.entity.Person;
import by.academy.springboot.model.entity.User;
import by.academy.springboot.model.entity.UserRole;
import by.academy.springboot.model.entity.enums.Role;
import by.academy.springboot.model.repository.PersonRepository;
import by.academy.springboot.model.repository.UserRepository;
import by.academy.springboot.model.repository.UserRolesRepository;
import by.academy.springboot.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserRolesRepository userRolesRepository;

    /**
     * @return new user`s id
     */
    @Override
    @Transactional
    public int save(UserDTO dto) throws IncorrectParameterException {
        if (isNotReadyForSave(dto)) {
            throw new IncorrectParameterException("Exception: check user parameters");
        }
        if (dto.getPassword() != null) {
            dto.setPassword(encrypt(dto.getPassword()));
        }
        return userRepository.save(UserMapper.INSTANCE.toModel(dto)).getId();
    }

    @Override
    public boolean isNotReadyForSave(UserDTO dto) {
        return dto == null
                || dto.getPersonId() == null
                || userRepository.getUserByPersonId(dto.getPersonId()) != null
                || personRepository.findByCitizenIdNumber(dto.getUsername()) == null;
    }

    @Override
    public String encrypt(CharSequence charSequence) {
        return new BCryptPasswordEncoder().encode(charSequence);
    }

    @Override
    @Transactional
    public void deleteRole(UserDTO dto, Role role){
        if (dto != null){
            List<Role> list = dto.getRoles().stream()
                    .filter(role1 -> role1 != role)
                    .toList();
            dto.setRoles(Set.copyOf(list));
            userRepository.save(UserMapper.INSTANCE.toModel(dto));
        }
    }

    @Override
    @Transactional
    public void addRole(UserDTO dto, Role role) throws ForbiddenActionException{
        if (dto == null){
            throw new ForbiddenActionException("There is no user to add the role");
        }
        Set<Role> roles = dto.getRoles();
        if (roles == null || roles.isEmpty() || !roles.contains(role)){
            UserRole userRole = UserRole.builder()
                    .user(UserMapper.INSTANCE.toModel(dto))
                    .role(role.toString())
                    .build();
            userRolesRepository.save(userRole);
        }
    }

    @Transactional
    public Integer createUser(PersonDTO dto) throws ForbiddenActionException {
        if (userRepository.getUserByPersonId(dto.getId()) != null){
            throw new ForbiddenActionException("User has already been created");
        }
        UserDTO newUserDto = UserDTO.builder()
                .username(dto.getCitizenIdNumber())
                .enabled(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .personId(dto.getId())
                .build();
        User saved = userRepository.save(UserMapper.INSTANCE.toModel(newUserDto));
        return saved.getId();
    }
}
