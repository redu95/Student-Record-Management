import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.io.*;

public class Main {
    private static final String MAIN_PANEL = "Main Panel";
    private static final String STUDENT_PANEL = "Student Panel";
    private static final String TEACHER_PANEL = "Teacher Panel";
    public Color backgroundColor = Color.WHITE;
    public Color buttonColor = new Color(255, 204, 204);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Trial Windows");
        frame.setLayout(new CardLayout());
        frame.setSize(500, 250);

        // Create the main panel
        Color backgroundColor = Color.WHITE;
        Color buttonColor = new Color(255, 204, 204);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel, MAIN_PANEL);

        // Create a label
        JLabel label = new JLabel("I am a");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.setBackground(backgroundColor);
        mainPanel.add(label);

        // Create a student button
        JButton studentButton = new JButton("Student");
        studentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentButton.setBackground(buttonColor);
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), STUDENT_PANEL);
            }
        });
        mainPanel.add(studentButton);

        // Create a teacher button
        JButton teacherButton = new JButton("Teacher");
        teacherButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        teacherButton.setBackground(buttonColor);
        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), TEACHER_PANEL);
            }
        });
        mainPanel.add(teacherButton);
        mainPanel.add(Box.createVerticalGlue());

        // Create the student panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());
        frame.add(studentPanel, STUDENT_PANEL);

        // Create a back button for the student panel
        JButton studentBackButton = new JButton("Back");
        studentBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), MAIN_PANEL);
            }
        });
        studentPanel.add(studentBackButton, BorderLayout.NORTH);

        // Create buttons for student functions
        JPanel studentButtonPanel = new JPanel();
        studentButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton registerButton = new JButton("Register");
        JButton viewGradeButton = new JButton("View Grade");
        registerButton.setBackground(buttonColor);
        viewGradeButton.setBackground(buttonColor);
        studentBackButton.setBackground(buttonColor);
        studentPanel.setBackground(backgroundColor);
        studentButtonPanel.add(registerButton);
        studentButtonPanel.add(viewGradeButton);
        studentPanel.add(studentButtonPanel, BorderLayout.CENTER);

        // Create the teacher panel
        JPanel teacherPanel = new JPanel();
        teacherPanel.setLayout(new BorderLayout());
        teacherPanel.setBackground(backgroundColor);
        frame.add(teacherPanel, TEACHER_PANEL);

        // Create a back button for the teacher panel
        JButton teacherBackButton = new JButton("Back");
        teacherBackButton.setBackground(buttonColor);
        teacherBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                cardLayout.show(frame.getContentPane(), MAIN_PANEL);
            }
        });
        teacherPanel.add(teacherBackButton, BorderLayout.NORTH);

        // Create buttons for teacher functions
        JPanel teacherButtonPanel = new JPanel();
        teacherButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.setBackground(buttonColor);
        JButton searchButton = new JButton("Search");
        teacherButtonPanel.add(assignGradeButton);
        teacherButtonPanel.add(searchButton);
        teacherPanel.add(teacherButtonPanel, BorderLayout.CENTER);

        //Action listener for ViewGradeBUtton
        viewGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradeViewer GV = new GradeViewer();
                GV.frame1.setVisible(true);
            }
        });
        //ACtion listener fo assign Grade button
        assignGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradeAssignmentForm gradeAssignmentForm = new GradeAssignmentForm();
                gradeAssignmentForm.setVisible(true);
            }
        });
        searchButton.setBackground(buttonColor);
        //Action listener for the register button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchForm searchForm = new SearchForm();
                searchForm.setVisible(true);
            }
        });
        // Action listener for the Register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
            }
        });

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

class SearchForm extends JFrame{
    private JTextField idField;
    Color backgroundColor = Color.WHITE;
    Color buttonColor = new Color(255, 204, 204);
    private JLabel command;
    public SearchForm() {
        setTitle("Search");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(null);
        setBackground(backgroundColor);
        idField = new JTextField("use ID to search");
        idField.setBounds(20, 20, 100, 30);
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(100, 20, 80, 30);
        searchButton.setBackground(buttonColor);
        JButton cancel = new JButton("cancel");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idToSearch = JOptionPane.showInputDialog("Enter the Name to search:");
                searchStudentById(idToSearch);
            }
        });
        add(new JLabel());
        add(searchButton);
    }
    private void searchStudentById(String idToSearch) {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_data.txt"))) {
            String line;
            StringBuilder foundStudents = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (line.contains("Name: " + idToSearch)) {
                    foundStudents.append(line).append("\n");
                    foundStudents.append(reader.readLine()).append("\n");
                    foundStudents.append(reader.readLine()).append("\n");
                    foundStudents.append(reader.readLine()).append("\n");
                    foundStudents.append(reader.readLine()).append("\n");
                }
            }

            if (foundStudents.length() > 0) {
                JOptionPane.showMessageDialog(null, "Student Information:\n" + foundStudents.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No student found with the provided ID.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField idField, contactField, addressField;
    private JTextArea coursesField;

    public RegistrationForm() {
        Color backgroundColor = Color.WHITE;
        Color buttonColor = new Color(255, 204, 204);
        setTitle("Registration Form");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));
        setBackground(backgroundColor);

        JLabel nameLabel = new JLabel("Full Name: ");
        nameField = new JTextField();
        JLabel idLabel = new JLabel("ID: ");
        idField = new JTextField();
        JLabel coursesLabel = new JLabel("Course: ");
        coursesField = new JTextArea();
        JLabel addressLabel = new JLabel("Address");
        addressField = new JTextField();
        JLabel contactLabel = new JLabel("Contact");
        contactField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(buttonColor);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();
                String contact = contactField.getText();
                String address = addressField.getText();
                String courses = coursesField.getText();

                saveStudentData(name, id, contact, address, courses);

                JOptionPane.showMessageDialog(null, "Data saved successfully!");

                dispose(); // Close the registration form
            }
        });

        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);
        add(contactLabel);
        add(contactField);
        add(addressLabel);
        add(addressField);
        add(coursesLabel);
        add(coursesField);
        add(new JLabel()); // Empty label for spacing
        add(saveButton);
    }

    private void saveStudentData(String name, String id, String contact, String address, String courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt", true))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("ID: " + id);
            writer.newLine();
            writer.write("Contact: " + contact);
            writer.newLine();
            writer.write("address: " + address);
            writer.newLine();
            writer.write("Course: " + courses);
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class GradeAssignmentForm extends JFrame {
    private JTextField studentIdField;
    private JTextField courseField;
    private JTextField gradeField;
    private JLabel gpaLabel;

    public GradeAssignmentForm() {
        Color backgroundColor = Color.WHITE;
        Color buttonColor = new Color(255, 204, 204);
        setTitle("Grade Assignment Form");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));
        setBackground(backgroundColor);
        JLabel studentIdLabel = new JLabel("Student ID: ");
        studentIdField = new JTextField();
        JLabel courseLabel = new JLabel("Course: ");
        courseField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade: ");
        gradeField = new JTextField();
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(buttonColor);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String course = courseField.getText();
                String grade = gradeField.getText();

                if (isStudentTakingCourse(studentId, course)) {
                    saveGrade(studentId, course, grade);

                    //double gpa = calculateGPA(grade);
                    //gpaLabel.setText("Total GPA: " + gpa);

                    JOptionPane.showMessageDialog(null, "Grade assigned successfully!");

                    dispose(); // Close the grade assignment form
                } else {
                    JOptionPane.showMessageDialog(null, "The student is not taking the course!");
                }
            }
        });

        gpaLabel = new JLabel();

        add(studentIdLabel);
        add(studentIdField);
        add(courseLabel);
        add(courseField);
        add(gradeLabel);
        add(gradeField);
        add(new JLabel());
        add(saveButton);
        add(gpaLabel);
    }

    private boolean isStudentTakingCourse(String studentId, String course) {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_data.txt"))) {
            String line;
            boolean foundStudent = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ID: " + studentId)) {
                    foundStudent = true;
                } else if (foundStudent && line.contains("Course:")) {
                    String[] courses = line.replace("Course:", "").trim().split(",");
                    for (String courseEntry : courses) {
                        if (courseEntry.trim().equals(course)) {
                            return true;
                        }
                    }
                } else if (line.equals("--------------------------------------------------")) {
                    foundStudent = false; // Reset foundStudent if dashed line encountered
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveGrade(String studentId, String course, String grade) {
        try {
            File inputFile = new File("student_data.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean foundStudent = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ID: " + studentId)) {
                    foundStudent = true;
                }

                if (foundStudent && line.contains("Course: " + course)) {
                    writer.write("Course: " + course + ": " + grade);
                    writer.flush();
                    writer.newLine();
                    System.out.println("Written successfully");
                } else {
                    writer.write(line);
                    writer.newLine();
                }

                if (line.equals("--------------------------------------------------")) {
                    foundStudent = false;
                }
            }

            reader.close();
            writer.close();

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Error occurred while renaming the temporary file.");
                }
            } else {
                System.out.println("Error occurred while deleting the original file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class GradeViewer {

    JFrame frame1;
    private JTextField studentIdField;
    private JTextField courseField;

    private JLabel resultLabel, guide, cgpa;
    private JButton viewGradeButton;
    //private JTextArea studentIdField;

    public GradeViewer() {
        // Initialize UI components (e.g., studentIdField, courseField, studentIdField)
        courseField = new JTextField();
        cgpa = new JLabel();
        guide = new JLabel("Type in ID and Course respectively");
        guide.setBounds(10, 5, 250, 30);
        //courseField.setBounds(10, 90, 200, 30);
        frame1 = new JFrame();
        Color backgroundColor = Color.WHITE;
        Color buttonColor = new Color(255, 204, 204);
        //frame1.add(courseField);
        frame1.setBackground(backgroundColor);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setSize(300, 300);
        viewGradeButton = new JButton("View Grade");
        viewGradeButton.setBackground(buttonColor);
        viewGradeButton.setBounds(90, 150, 130, 30);
        viewGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                int id =Integer.parseInt(studentId);
                String course = courseField.getText();
                viewGrade(studentId, course);
                double gpa = calculateGPA(id);
                String courseGrade = getCourseGrade(id, course);
                cgpa.setText(""+gpa);
            }
        });
        studentIdField = new JTextField();
        studentIdField.setBounds(10, 30, 200, 30);
        courseField.setBounds(10, 60, 200, 30);

        resultLabel = new JLabel("-");
        resultLabel.setBounds(20, 100, 200, 30);
        cgpa.setBounds(20, 130, 200, 30);
        frame1.add(resultLabel);
        frame1.add(courseField);
        frame1.add(cgpa);
        frame1.add(studentIdField);
        frame1.add(guide);
        frame1.add(viewGradeButton);
    }
    public double calculateGPA(int studentId) {
        try (Scanner scanner = new Scanner(new File("student_data.txt"))) {
            String line;
            int totalCredits = 0;
            int totalGradePoints = 0;
            boolean isStudentFound = false;
            boolean isEndOfStudentData = false;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (!isEndOfStudentData) {
                    if (line.contains("ID: " + studentId)) {
                        isStudentFound = true;
                        continue;
                    }
                    if (isStudentFound && line.contains("Course:")) {
                        String[] parts = line.split(":");
                        int credits = 1;
                        int grade = convertGradeToNumber(parts[2].trim());
                        //System.out.println(parts[2].trim());
                        totalCredits += credits;
                        totalGradePoints += (credits * grade);
                    }
                    if (line.startsWith("----")) {
                        isEndOfStudentData = true;
                    }
                } else {
                    if (isStudentFound) {
                        //break;
                    }
                    isEndOfStudentData = false;
                    isStudentFound = false;
                }
            }

            if (!isStudentFound) {
                return 0.0;
            }

            if (totalCredits == 0) {
                return 0.0;
            }

            return (double) totalGradePoints / totalCredits;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private int convertGradeToNumber(String grade) {
        switch (grade) {
            case "A":
                return 4;
            case "B":
                return 3;
            case "C":
                return 2;
            case "D":
                return 1;
            case "F":
                return 0;
            default:
                return 0;
        }
    }

    public static String getCourseGrade(int studentId, String courseName) {
        try (Scanner scanner = new Scanner(new File("student_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("studentId:" + studentId) && line.contains("course:" + courseName)) {
                    String[] data = line.split(":");
                    for (int i = 0; i < data.length; i++) {
                        if (data[i].trim().equalsIgnoreCase(courseName)) {
                            if (i + 1 < data.length) {
                                return data[i + 1].trim();
                            }
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "N/A";
    }
    public static double getGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            default:
                return 0.0;
        }
    }
    public void viewGrade(String studentId, String course) {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_data.txt"))) {
            String line;
            boolean foundStudent = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ID: " + studentId.trim())) {
                    foundStudent = true;
                } else if (foundStudent && line.contains("Course:")) {
                    String[] courses = line.replace("Course:", "").trim().split(",");
                    for (String courseEntry : courses) {
                        String[] courseData = courseEntry.split(":");
                        String courseName = courseData[0].trim();
                        if (courseName.equals(course)) {
                            if (courseData.length > 1) {
                                String grade = courseData[1].trim();
                                resultLabel.setText("Grade for " + course + " is : " + grade);
                            } else {
                                resultLabel.setText("No grade available for " + course);
                            }
                            return;
                        }
                    }
                } else if (line.equals("--------------------------------------------------")) {
                    foundStudent = false; // Reset foundStudent if dashed line encountered
                }
            }
            resultLabel.setText("Student ID " + studentId + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}