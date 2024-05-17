package Tugas;

import java.io.*;
import java.util.*;

public class HospitalQueue {
    private static final String FILE_PATH = "D:\\CODE\\Java OOP\\Java Operation File\\Tugas\\HospitalData.txt";

    public boolean runQueue(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<String> patientQueue = new ArrayDeque<>();
        boolean continueLoop = true;

        loadQueueFromFile(patientQueue);

        while (continueLoop) {
            System.out.println(new File(FILE_PATH).exists() ? "\nNotification: Database found" : "\nNotification: Database not found");
            System.out.println("=====================================");
            System.out.println("HOSPITAL QUEUE SYSTEM");
            System.out.println("=====================================");
            System.out.println("1. Add Patient");
            System.out.println("2. Remove Patient");
            System.out.println("3. View Patients");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();
            System.out.println("\n=====================================");

            switch (choice) {
                case 1:
                    System.out.println("ADDING PATIENT TO QUEUE");
                    System.out.println("=====================================");
                    System.out.print("Enter the patient name: ");
                    String patientName = input.nextLine();
                    patientQueue.add(patientName);
                    appendPatientToFile(patientName);
                    break;
                case 2:
                    System.out.println("REMOVING PATIENTS FROM QUEUE");
                    System.out.println("=====================================");
                    System.out.print("Enter the number of patients to remove: ");
                    int amount = input.nextInt();
                    removePatientsFromQueue(patientQueue, amount);
                    saveQueueToFile(patientQueue);
                    break;
                case 3:
                    System.out.println("VIEWING PATIENTS IN QUEUE");
                    System.out.println("=====================================");
                    viewPatientsInQueue(patientQueue);
                    break;
                case 4:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        input.close();
        return false;
    }

    private void loadQueueFromFile(Queue<String> queue) {
        try (BufferedReader bfReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bfReader.readLine()) != null) {
                queue.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }

    private void appendPatientToFile(String patientName) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bfWriter.write(patientName);
            bfWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void saveQueueToFile(Queue<String> queue) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String patient : queue) {
                bfWriter.write(patient);
                bfWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private void removePatientsFromQueue(Queue<String> queue, int amount) {
        for (int i = 0; i < amount; i++) {
            String removed = queue.poll();
            if (removed == null) {
                System.out.println("Queue is empty.");
                break;
            }
            System.out.printf("%s is removed from queue\n", removed);
        }
    }

    private void viewPatientsInQueue(Queue<String> queue) {
        int count = 1;
        for (String patient : queue) {
            System.out.printf("%d. %s\n", count++, patient);
        }
    }
}
