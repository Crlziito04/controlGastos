import entities.Category;
import entities.Expense;
import entities.User;
import exceptions.IllegalArgumentException;
import exceptions.NotNullException;

import java.util.Date;
import java.util.List;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
       try {
           Category cat = new Category("uno", "uno desc");
           Category cat2 = new Category("uno2", "uno2 desc2");
           User user = new User("carlos", "ca");
           User user2 = new User("carlos2", "ca2");
           Expense exp = new Expense(32, new Date(), "new expense", cat);
           Expense exp2 = new Expense(322, new Date(), "new expense2", cat2);
           Expense exp3 = new Expense(325, new Date(), "new expense3", cat2);

           user2.addExpense(exp);
           List<Expense> expenses = user2.getExpenses();
           for (Expense e : expenses){
               System.out.println(e.getId() + " soy user 2");
           }

           user.addExpense(exp3);
           user.addExpense(exp2);

           List<Expense> exps1 = user.getExpenses();
           user.removeExpense(1);
           user.updateExpense(2, exp2);
           for (Expense e : exps1) {
               System.out.println(e.getAmount() + " " + e.getId());
           }
       }catch (NotNullException | IllegalArgumentException e){
           System.err.println(e.getMessage());
       }
    }
}