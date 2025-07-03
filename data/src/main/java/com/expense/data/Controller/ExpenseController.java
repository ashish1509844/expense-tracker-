package com.expense.data.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.data.Model.Expense;
import com.expense.data.Service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return service.getAllExpenses();
    }

    @GetMapping("/{id}")
    public Optional<Expense> getExpenseById(@PathVariable Long id) {
        return service.getExpenseById(id);
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return service.addExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    @GetMapping("/month/{month}/year/{year}")
    public List<Expense> getByMonth(@PathVariable int month, @PathVariable int year) {
        return service.getByMonth(month, year);
    }
}
