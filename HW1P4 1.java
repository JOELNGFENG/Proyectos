//links or videos used for following code//
//https://www.chegg.com/homework-help/questions-and-answers/java-import-javaxswing-import-javaawt-import-javaawtevent-import-javaxswingevent-import-ja-q84045760
//https://www.youtube.com/watch?v=0FDofJf11jU
//https://stackoverflow.com/questions/76425958/getting-an-error-message-in-java-while-using-java-swing
//https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
//https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/using.html
//Also used chatGPT to fix some problems that i got for the following code.


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class HW1P4 extends JFrame {
    private JTextField descripcion, importe;
    private JComboBox<String> categoryBox;
    private JTextArea expenseList;
    private ArrayList<Expense> expenses = new ArrayList<>();

    public HW1P4() {
        super("Expense Tracker");

        setLayout(new FlowLayout());

        add(new JLabel("Description:"));
        descripcion = new JTextField(20);
        add(descripcion);

        add(new JLabel("Amount:"));
        importe = new JTextField(10);
        add(importe);

        add(new JLabel("Category:"));
        categoryBox = new JComboBox<>(new String[]{"Groceries", "Utilities", "Entertainment"});
        add(categoryBox);

        JButton addB = new JButton("Add Expense");
        addB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });
        add(addB);

        JButton totalB = new JButton("Total Expenses");
        totalB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateT();
            }
        });
        add(totalB);

        expenseList = new JTextArea(10, 40);
        expenseList.setEditable(false);
        add(new JScrollPane(expenseList));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void addExpense() {
        try {
            String description = descripcion.getText();
            double amount = Double.parseDouble(importe.getText());
            String category = (String) categoryBox.getSelectedItem();
            Expense expense = new Expense(description, amount, category);
            expenses.add(expense);
            expenseList.append(expense + "\n");
            descripcion.setText("");
            importe.setText("");
            saveExpenses();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding expense", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateT() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        JOptionPane.showMessageDialog(this, "Total Expenses: $" + total, "Total", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveExpenses() {
        try (PrintWriter out = new PrintWriter(new FileWriter("expenses.txt"))) {
            for (Expense expense : expenses) {
                out.println(expense);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving expenses to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new HW1P4();
    }
}

class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return description + " - $" + amount + " [" + category + "]";
    }
}
