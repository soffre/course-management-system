import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeTableExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grade Table Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Student ID");
            model.addColumn("First Name");
            model.addColumn("Last Name");
            model.addColumn("Grade");  // Single column for Grade
            model.addColumn("Course");

            // Create a combobox with grade options
            JComboBox<String> gradeComboBox = new JComboBox<>(new String[]{"A", "B", "C", "D", "F"});

            JTable table = new JTable(model);

            // Set the combobox as the editor for the "Grade" column
            table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(gradeComboBox));

            // Pre-fill Grade for row 4 (index 3)
            model.addRow(new Object[]{1, "John", "Doe", "A", "Math"});
            model.addRow(new Object[]{2, "Alice", "Smith", "B", "English"});
            model.addRow(new Object[]{3, "Bob", "Johnson", "C", "History"});
            model.addRow(new Object[]{4, "Eve", "Brown", "D", "Science"});

            JButton saveButton = new JButton("Save");

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int row = 0; row < model.getRowCount(); row++) {
                        int studentID = (int) model.getValueAt(row, 0);
                        String firstName = (String) model.getValueAt(row, 1);
                        String lastName = (String) model.getValueAt(row, 2);
                        String grade = (String) model.getValueAt(row, 3);
                        String course = (String) model.getValueAt(row, 4);
                        System.out.println("Student ID: " + studentID + ", First Name: " + firstName + ", Last Name: " + lastName + ", Grade: " + grade + ", Course: " + course);
                    }
                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            panel.add(saveButton, BorderLayout.SOUTH);

            frame.add(panel);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
