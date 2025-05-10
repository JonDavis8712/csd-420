/* Jonathan Davis Module 10 Assignment
 *5/09/2025
 * This Java program provides a simple graphical interface to view and update fan information stored in a MySQL database. Users can enter a fan ID to
 * display the record and then modify the first name, last name, and favorite team before updating the database. It also includes basic test methods to verify 
 * database connectivity and the view/update functionalities.
 * Tabninepro was used to assist in writing this code, more so the comments than the code itself. using it to
 * test if it can help me write better code, tabnine is still great, it did a lot of the work here, but I learned a lot from it.
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JonDavisFanDatabaseApp extends JFrame implements ActionListener {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String DB_USER = "student1";
    private static final String DB_PASSWORD = "pass";
    private static final String TABLE_NAME = "fans";

    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField favoriteTeamField;
    private JButton displayButton;
    private JButton updateButton;
    private JLabel messageLabel;

    public JonDavisFanDatabaseApp() {
        setTitle("Fan Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));
        setPreferredSize(new Dimension(400, 250));

        add(new JLabel("ID:"));
        idField = new JTextField(10);
        add(idField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField(25);
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField(25);
        add(lastNameField);

        add(new JLabel("Favorite Team:"));
        favoriteTeamField = new JTextField(25);
        add(favoriteTeamField);

        displayButton = new JButton("Display Record");
        displayButton.addActionListener(this);
        add(displayButton);

        updateButton = new JButton("Update Record");
        updateButton.addActionListener(this);
        add(updateButton);

        messageLabel = new JLabel("");
        add(messageLabel);
        add(new JLabel("")); // For spacing

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public FanRecord getFanRecord(int id) {
        FanRecord fan = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("SELECT firstname, lastname, favoriteteam FROM " + TABLE_NAME + " WHERE ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                fan = new FanRecord(id, rs.getString("firstname"), rs.getString("lastname"), rs.getString("favoriteteam"));
            }
        } catch (SQLException e) {
            messageLabel.setText("Error retrieving record: " + e.getMessage());
            e.printStackTrace();
        }
        return fan;
    }

    public boolean updateFanRecord(FanRecord fan) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE " + TABLE_NAME + " SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?")) {
            pstmt.setString(1, fan.getFirstName());
            pstmt.setString(2, fan.getLastName());
            pstmt.setString(3, fan.getFavoriteTeam());
            pstmt.setInt(4, fan.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                messageLabel.setText("Record updated successfully.");
                return true;
            } else {
                messageLabel.setText("No record found with ID: " + fan.getId());
                return false;
            }
        } catch (SQLException e) {
            messageLabel.setText("Error updating record: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayButton) {
            try {
                int id = Integer.parseInt(idField.getText());
                FanRecord fan = getFanRecord(id);
                if (fan != null) {
                    firstNameField.setText(fan.getFirstName());
                    lastNameField.setText(fan.getLastName());
                    favoriteTeamField.setText(fan.getFavoriteTeam());
                    messageLabel.setText("");
                } else {
                    messageLabel.setText("No fan found with ID: " + id);
                    firstNameField.setText("");
                    lastNameField.setText("");
                    favoriteTeamField.setText("");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid ID format.");
            }
        } else if (e.getSource() == updateButton) {
            try {
                int id = Integer.parseInt(idField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String favoriteTeam = favoriteTeamField.getText();
                FanRecord updatedFan = new FanRecord(id, firstName, lastName, favoriteTeam);
                updateFanRecord(updatedFan);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid ID format.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JonDavisFanDatabaseApp::new);
    }

    // Helper class to represent a Fan record
    static class FanRecord {
        private int id;
        private String firstName;
        private String lastName;
        private String favoriteTeam;

        public FanRecord(int id, String firstName, String lastName, String favoriteTeam) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.favoriteTeam = favoriteTeam;
        }

        public int getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFavoriteTeam() {
            return favoriteTeam;
        }
    }

    // Test methods
    public static class JonDavisFanDatabaseAppTest {

        private static JonDavisFanDatabaseApp app;

        public static void main(String[] args) {
            // Initialize the application
            // Run the application (for manual UI testing)
            SwingUtilities.invokeLater(() -> {
                app = new JonDavisFanDatabaseApp();
            });

            // Programmatic tests
            testDatabaseConnection();
            testGetExistingFanRecord();
            testUpdateExistingFanRecord();
            testUpdateNonExistingFanRecord();
        }

        public static void testDatabaseConnection() {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                System.out.println("Test: Database connection successful!");
            } catch (SQLException e) {
                System.err.println("Test Failed: Database connection error: " + e.getMessage());
            }
        }

        public static void testGetExistingFanRecord() {
            FanRecord fan = app.getFanRecord(1);
            if (fan != null) {
                System.out.println("Test: getFanRecord for existing ID successful. Found: " + fan.getFirstName() + " " + fan.getLastName());
            } else {
                System.err.println("Test Failed: getFanRecord for existing ID failed (null returned).");
            }
        }

        public static void testUpdateExistingFanRecord() {
            FanRecord initialFan = app.getFanRecord(2);
            if (initialFan != null) {
                FanRecord updatedFan = new FanRecord(2, "UpdatedFirstName", "UpdatedLastName", "UpdatedTeam");
                boolean result = app.updateFanRecord(updatedFan);
                if (result) {
                    FanRecord retrievedUpdatedFan = app.getFanRecord(2);
                    if (retrievedUpdatedFan != null && retrievedUpdatedFan.getFirstName().equals("UpdatedFirstName")) {
                        System.out.println("Test: updateFanRecord for existing ID successful.");
                        app.updateFanRecord(initialFan);
                    } else {
                        System.err.println("Test Failed: updateFanRecord for existing ID - update not reflected.");
                    }
                } else {
                    System.err.println("Test Failed: updateFanRecord for existing ID failed (updateFanRecord returned false).");
                }
            } else {
                System.err.println("Test Skipped: updateExistingFanRecord - No record with ID 2 found.");
            }
        }

        public static void testUpdateNonExistingFanRecord() {
            boolean result = app.updateFanRecord(new FanRecord(999, "NonExistent", "Fan", "Nowhere FC")); 
            if (!result) {
                System.out.println("Test: updateFanRecord for non-existing ID successful (returned false).");
            } else {
                System.err.println("Test Failed: updateFanRecord for non-existing ID - should have returned false.");
            }
        }
    }
}