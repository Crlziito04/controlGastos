package entities;

import exceptions.ExpenseNotFoundException;
import exceptions.IllegalArgumentException;
import exceptions.NullPointerException;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private List<Expense> expenses;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.expenses = new ArrayList<>();
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
    public void addExpense(Expense newExpense) throws NullPointerException,IllegalArgumentException{
        if (newExpense == null){
            throw  new NullPointerException("expense no puede se nulo");
        }
        if(newExpense.getAmount() < 0){
            throw new IllegalArgumentException("Amount coulbe greater than 0");
        }
        expenses.add(newExpense);
    }
    public void removeExpense(int id) {
       try{
           Expense expenseToRemove = null;
            for (Expense e : expenses){
             if(e.getId() == id) {
                 expenseToRemove = e;
                 break;
             }
            }
            if(expenseToRemove == null){
                throw new ExpenseNotFoundException("Expense not found");
            }
                expenses.remove(expenseToRemove);
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

            Expense expenseToUpdate = null;
            for (Expense e : expenses){
                if(e.getId() == id) {
                    expenseToUpdate = e;
                    break;
                }
            }
            if(expenseToUpdate == null) {
                throw new ExpenseNotFoundException("Expense not found");
            }
            expenseToUpdate.setAmount(newExpense.getAmount());
            expenseToUpdate.setCategory(newExpense.getCategory());
            expenseToUpdate.setDate(newExpense.getDate());
            expenseToUpdate.setDescription(newExpense.getDescription());
            System.out.println("Expense updated " + id);
        }catch (ExpenseNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}
