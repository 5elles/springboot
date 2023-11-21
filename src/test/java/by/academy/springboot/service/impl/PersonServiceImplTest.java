package by.academy.springboot.service.impl;

import by.academy.springboot.dto.*;
import by.academy.springboot.exception.ForbiddenActionException;
import by.academy.springboot.exception.IncorrectParameterException;
import by.academy.springboot.mapper.PaymentOrderMapper;
import by.academy.springboot.mapper.PersonMapper;
import by.academy.springboot.model.entity.*;
import by.academy.springboot.model.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonServiceImpl personService;

    @DisplayName("test for isForbiddenForCreation method")
    @Test
    void givenEmailDto_whenSsForbiddenForCreation_thenFalse() {
        EmailDTO dto = EmailDTO.builder()
                .id(1)
                .personId(1)
                .build();
        assertThat(personService.isForbiddenForCreation(dto)).isFalse();
    }

    @DisplayName("test for isForbiddenForCreation method")
    @Test
    void givenEmailDto_whenIsForbiddenForCreation_thenTrue() {
        EmailDTO dto = EmailDTO.builder()
                .id(1)
                .build();
        assertThat(personService.isForbiddenForCreation(dto)).isTrue();
    }


}