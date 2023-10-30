package by.academy.springboot.mapper;

import by.academy.springboot.dto.CurrencyDTO;
import by.academy.springboot.model.entity.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyMapperTest {

    @Test
    void shouldMapCurrencyToDTO() {
        Currency currency = Currency.builder()
                .id(1)
                .currencyAbbreviation("TEST")
                .currencyCode("000")
                .currencyName("test")
                .currencyRate(7.0)
                .build();
        CurrencyDTO dto = CurrencyMapper.INSTANCE.toDTO(currency);
        assertNotNull(dto);
        assertAll("comparing the fields",
                () -> assertEquals(currency.getCurrencyAbbreviation(), dto.getCurrencyAbbreviation()),
                () -> assertEquals(currency.getCurrencyCode(), dto.getCurrencyCode()),
                () -> assertEquals(currency.getCurrencyName(), dto.getCurrencyName()),
                () -> assertEquals(currency.getCurrencyRate(), dto.getCurrencyRate()),
                () -> assertEquals(currency.getId(), dto.getId())
        );
    }
}