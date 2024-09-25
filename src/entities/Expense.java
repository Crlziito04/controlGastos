package entities;

import java.util.Date;

public class Expense {
    private int amount;
    private Date date;
    private Category category;
    private String description;

    public Category getCategory() {
        return category;
    }

    public Expense(int amount, Date date, String description, Category category) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
