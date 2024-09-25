import entities.Category;
import entities.Expense;
import entities.User;
import exceptions.IllegalArgumentException;
import exceptions.NullPointerException;

import java.util.Date;
import java.util.List;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
       try {
           Category cat = new Category("uno", "uno desc");
           Category cat2 = new Category("uno2", "uno2 desc2");
           User user = new User("carlos", "ca");
           Expense exp = new Expense(32, new Date(), "new expense", cat);
           Expense exp2 = new Expense(322, new Date(), "new expense2", cat2);
           Expense exp3 = new Expense(325, new Date(), "new expense3", cat2);
           System.out.println(exp.getId());
           System.out.println(exp2.getId());
           user.addExpense(exp3);

           List<Expense> exps = user.getExpenses();
           //user.removeExpense(1);
           user.updateExpense(2, exp3);
           for (Expense e : exps) {
               System.out.println(e.getAmount() + " " + e.getId());
           }
       }catch (NullPointerException | IllegalArgumentException e){
           System.err.println(e.getMessage());
       }
    }
}