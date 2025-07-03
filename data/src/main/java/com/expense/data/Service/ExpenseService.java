package com.expense.data.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.data.Model.Expense;
import com.expense.data.Repository.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return repository.findById(id);
    }

    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    public Expense updateExpense(Long id, Expense expense) {
        expense.setId(id);
        return repository.save(expense);
    }

    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    public List<Expense> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Expense> getByMonth(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return repository.findByDateBetween(start, end);
    }
}
