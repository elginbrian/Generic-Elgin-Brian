import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class HospitalQueueGUI {
    private static final String FILE_PATH = "D:\\Java GUI\\HospitalData.txt";
    private Queue<String> patientQueue = new ArrayDeque<>();
    private JTextArea displayArea;

    public HospitalQueueGUI() {
        loadQueueFromFile(patientQueue);

        JFrame frame = new JFrame("Hospital Queue System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton addButton = new JButton("Add Patient");
        JButton removeButton = new JButton("Remove Patient");
        JButton viewButton = new JButton("View Patients");
        JButton exitButton = new JButton("Exit");

        addButton.addActionListener(e -> addPatient());
        removeButton.addActionListener(e -> removePatients());
        viewButton.addActionListener(e -> viewPatients());
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(viewButton);
        panel.add(exitButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);

        displayNotification(new File(FILE_PATH).exists() ? "Database found" : "Database not found");
    }

    private void displayNotification(String message) {
        displayArea.append("Notification: " + message + "\n");
    }

    private void addPatient() {
        String patientName = JOptionPane.showInputDialog("Enter the patient name:");
        if (patientName != null && !patientName.trim().isEmpty()) {
            patientQueue.add(patientName);
            appendPatientToFile(patientName);
            displayNotification("Patient " + patientName + " added to the queue.");
        } else {
            displayNotification("Invalid patient name.");
        }
    }

    private void removePatients() {
        String input = JOptionPane.showInputDialog("Enter the number of patients to remove:");
        try {
            int amount = Integer.parseInt(input);
            StringBuilder removedPatients = new StringBuilder();
            for (int i = 0; i < amount; i++) {
                String removed = patientQueue.poll();
                if (removed == null) {
                    displayNotification("Queue is empty.");
                    break;
                }
                removedPatients.append(removed).append(" is removed from queue\n");
            }
            saveQueueToFile(patientQueue);
            displayNotification(removedPatients.toString());
        } catch (NumberFormatException e) {
            displayNotification("Invalid number.");
        }
    }

    private void viewPatients() {
        StringBuilder patients = new StringBuilder();
        int count = 1;
        for (String patient : patientQueue) {
            patients.append(count++).append(". ").append(patient).append("\n");
        }
        displayNotification(patients.length() > 0 ? patients.toString() : "Queue is empty.");
    }

    private void loadQueueFromFile(Queue<String> queue) {
        try (BufferedReader bfReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bfReader.readLine()) != null) {
                queue.add(line);
            }
        } catch (IOException e) {
            displayNotification("Error loading from file: " + e.getMessage());
        }
    }

    private void appendPatientToFile(String patientName) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bfWriter.write(patientName);
            bfWriter.newLine();
        } catch (IOException e) {
            displayNotification("Error writing to file: " + e.getMessage());
        }
    }

    private void saveQueueToFile(Queue<String> queue) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String patient : queue) {
                bfWriter.write(patient);
                bfWriter.newLine();
            }
        } catch (IOException e) {
            displayNotification("Error saving to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalQueueGUI::new);
    }
}

