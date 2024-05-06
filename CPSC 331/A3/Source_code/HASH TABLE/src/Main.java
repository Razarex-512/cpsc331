import java.util.ArrayList;

class Student {
    int studentId;
    String studentName;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }
}

class HashTable {
    private final static int TABLE_CAPACITY = 8;
    private ArrayList<Student>[] bucketArray;

    public HashTable() {
        bucketArray = new ArrayList[TABLE_CAPACITY];
        for (int i = 0; i < TABLE_CAPACITY; i++) {
            bucketArray[i] = new ArrayList<>();
        }
    }

    private int hash(int studentId) {
        return studentId % TABLE_CAPACITY;
    }

    public boolean locate(int studentId) {
        int bucketIndex = hash(studentId);
        for (Student s : bucketArray[bucketIndex]) {
            if (s.studentId == studentId) {
                return true;
            }
        }
        return false;
    }

    public String getName(int studentId) {
        int bucketIndex = hash(studentId);
        for (Student s : bucketArray[bucketIndex]) {
            if (s.studentId == studentId) {
                return s.studentName;
            }
        }
        return "Name not found";
    }

    public void addOrUpdateStudent(int studentId, String studentName) {
        int bucketIndex = hash(studentId);
        for (Student s : bucketArray[bucketIndex]) {
            if (s.studentId == studentId) {
                s.studentName = studentName;
                return;
            }
        }
        bucketArray[bucketIndex].add(new Student(studentId, studentName));
    }

    public void removeStudent(int studentId) {
        int bucketIndex = hash(studentId);
        bucketArray[bucketIndex].removeIf(s -> s.studentId == studentId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TABLE_CAPACITY; i++) {
            sb.append("Index ").append(i).append(": ");
            for (Student s : bucketArray[i]) {
                sb.append("[").append(s.studentId).append(", ").append(s.studentName).append("] ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable studentsTable = new HashTable();

        // Add student records
        studentsTable.addOrUpdateStudent(20500120, "Bob");
        studentsTable.addOrUpdateStudent(20700200, "Alice");
        studentsTable.addOrUpdateStudent(30100230, "Cathy");
        studentsTable.addOrUpdateStudent(20200156, "Ali");

        System.out.println("Initial student records:");
        System.out.println(studentsTable);

        // Update a student's name
        studentsTable.addOrUpdateStudent(20500120, "Bobby");
        System.out.println("Records after update:");
        System.out.println(studentsTable);

        // Check if a student ID is present
        System.out.println("Is student ID 20500120 present? " + studentsTable.locate(20500120));

        // Get a student's name by ID
        System.out.println("Student with ID 20700200: " + studentsTable.getName(20700200));

        // Remove a student
        studentsTable.removeStudent(20700200);
        System.out.println("Records after removal:");
        System.out.println(studentsTable);

        // Attempt to remove the same student again
        studentsTable.removeStudent(20700200);

        // Try to get the name of a removed student
        System.out.println("Attempt to find removed student with ID 20700200: " + studentsTable.getName(20700200));

        // Display final records
        System.out.println("Final student records:");
        System.out.println(studentsTable);
    }
}
