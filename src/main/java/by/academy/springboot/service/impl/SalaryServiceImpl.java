package by.academy.springboot.service.impl;

import by.academy.springboot.service.SalaryService;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Override
    public Integer getSalary(Integer salary) {
        return salary + 100;
    }
}
