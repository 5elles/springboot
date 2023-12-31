package by.academy.springboot.model.repository;

import by.academy.springboot.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    List<Currency> findAllByCurrencyAbbreviationIsNot(String excludeCurrencyAbbr);
    @Query("from Currency order by currencyAbbreviation")
    List<Currency> findAllOrderByCurrencyAbbreviation();
}
