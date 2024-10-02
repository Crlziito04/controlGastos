package entities;

import exceptions.IllegalArgumentException;
import exceptions.NotNullException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Expense {
    private static int contador = 0;
    private Double amount;
    private Date date;
    private Category category;
    private String description;
    private int id = 0;


    public Expense(Double amount, Date date, String description, Category category) throws IllegalArgumentException,NotNullException {
        if(amount == null){
            throw new IllegalArgumentException("amount, can not be null");
        }
        if(amount < 0) {
            throw new IllegalArgumentException("amount, can not be negative");
        }
       if(Objects.isNull(date)) {
            throw new NotNullException("Date, cant be null");
        }
       if (Objects.isNull(description)){
           throw new NotNullException("string, can not be null");
       }
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) throws  IllegalArgumentException {
        if(amount < 0){
            throw  new IllegalArgumentException("amount should be greater than 0");
        }
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
