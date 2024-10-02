package entities;

import exceptions.ExpenseNotFoundException;
import exceptions.IllegalArgumentException;
import exceptions.NotNullException;
import interfaces.ExpenseValidator;
import interfaces.ExpensesOperable;

import javax.swing.text.html.Option;
import java.util.*;

public class User implements ExpensesOperable {
    private String name;
    private String email;
    private List<Expense> expenses;


    public User(String name, String email) throws NotNullException
    {
        if(name == null || email == null){
            throw new NotNullException("name, email can not be null");
        }
        this.name = name;
        this.email = email;
        this.expenses = new ArrayList<>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    @Override
    public double calculateTotalExpenses() {
       return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    @Override
    public double calculateExpensesByCategory(Category category) {
        return expenses.stream().filter(c ->c.getCategory() == category)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public Map<Category, Double> getTotalExpensesByCategory() {
        Map<Category, Double> expensesByCategory = new HashMap<>();
        expenses.forEach(e -> {
            expensesByCategory.put(e.getCategory(),
                    expensesByCategory.getOrDefault(e.getCategory(),0.0)
                            +e.getAmount());
        });

        return expensesByCategory;
    }

    @Override
    public List<Expense> validateExpenses(String MayorOMenor, Double value) {
        List<Expense> newExpenses = new ArrayList<>();

        ExpenseValidator validator = (expense, condition) -> {
            if (condition.equals(">")) {
                return expense.getAmount() > value;
            } else if (condition.equals("<")) {
                return expense.getAmount() < value;
            }
            return false;
        };

        for(Expense e : expenses){
           if(validator.validate(e,MayorOMenor)){
               newExpenses.add(e);
           }
        }

        return newExpenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void addExpense(Expense newExpense) throws IllegalArgumentException, NotNullException{
        if (newExpense == null){
            throw  new NotNullException("expense can not be null");
        }
        if(newExpense.getAmount() < 0){
            throw new IllegalArgumentException("Amount should be greater than 0");
        }
        expenses.add(newExpense);
    }
    public void removeExpense(int id) {
       try{
            Optional<Expense> expToRemove = expenses.stream().filter(e-> e.getId() == id).findFirst();

            if(expToRemove.isEmpty()){
                throw new ExpenseNotFoundException("Expense not found");
            }
                expenses.remove(expToRemove.get());
                System.out.println("Expense deleted " + id);
       }catch (ExpenseNotFoundException e){
           System.err.println(e.getMessage());
       }
    }
    public void updateExpense(int id, Expense newExpense) throws IllegalArgumentException {
        try{
            if(newExpense.getAmount() < 0){
                throw new IllegalArgumentException("Amount coulbe greater than 0");
            }

           Optional<Expense> expenseToUpdate = expenses.stream().filter(e-> e.getId() == id).findFirst();
            if(expenseToUpdate.isEmpty()) {
                throw new ExpenseNotFoundException("Expense not found");
            }

            Expense existingExpense = expenseToUpdate.get();

            existingExpense.setAmount(newExpense.getAmount());
            existingExpense.setCategory(newExpense.getCategory());
            existingExpense.setDate(newExpense.getDate());
            existingExpense.setDescription(newExpense.getDescription());
            System.out.println("Expense updated " + id);
        }catch (ExpenseNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}
