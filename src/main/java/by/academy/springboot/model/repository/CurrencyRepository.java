package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Currency;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Currency findCurrencyByCurrencyAbbreviationIgnoreCase(String string);
    List<Currency> findCurrenciesByCurrencyAbbreviationIsNotOrderByCurrencyAbbreviation(String abbreviation);
}
