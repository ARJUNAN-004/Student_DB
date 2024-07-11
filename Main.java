import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxSize = 100;
        Stu[] students = new Stu[maxSize];
        int size = 0;
        System.out.println("_________________DATABASE MANAGEMENT___________");

        while (true) {
            System.out.println("STUDENT");
            System.out.println("__________________________________");
            System.out.println("1) ADD STUDENT\n2) DISPLAY DETAILS\n3) UPDATE DATA\n4) DELETE DATA\n5) EXIT");
            System.out.println("__________________________________");
            int sOption = sc.nextInt();
            sc.nextLine();

            switch (sOption) {
                case 1:
                    try {
                        System.out.println("Enter student name:");
                        String name = sc.nextLine();
                        if (!name.matches("[a-zA-Z ]+")) {
                            throw new IllegalArgumentException("Invalid input format: Name should contain only alphabets.");
                        }

                        System.out.println("Enter student ID:");
                        String idStr = sc.nextLine();
                        if (!idStr.matches("\\d+")) {
                            throw new IllegalArgumentException("Invalid input format: ID should contain only numbers.");
                        }
                        long id = Long.parseLong(idStr);

                        System.out.println("Enter student contact (10 digits):");
                        String contactStr = sc.nextLine();
                        if (!contactStr.matches("\\d{10}")) {
                            throw new IllegalArgumentException("Invalid input format: Contact should be exactly 10 digits.");
                        }
                        long contact = Long.parseLong(contactStr);

                        System.out.println("Enter student gender (Male/Female/Other):");
                        String gender = sc.nextLine();
                        if (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female") && !gender.equalsIgnoreCase("Other")) {
                            throw new IllegalArgumentException("Invalid input format: Gender should be Male, Female, or Other.");
                        }

                        Stu newStudent = new Stu(name, id, contact, gender);
                        students[size] = newStudent;
                        ++size;
                        System.out.println("Student added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("DISPLAY DETAILS");
                    if (size == 0) {
                        System.out.println("__________________________________");
                        System.out.println("*****NO DETAILS PRESENT****");
                        System.out.println("__________________________________");
                    } else {
                        System.out.println("__________________________________");
                        for (int i = 0; i < size; ++i) {
                            students[i].displayDetails();
                            System.out.println("__________________________________");
                        }
                    }
                    break;

                case 3:
                    System.out.println("UPDATE DETAILS");
                    if (size == 0) {
                        System.out.println("__________________________________");
                        System.out.println("*****NO DETAILS PRESENT****");
                        System.out.println("__________________________________");
                    } else {
                        System.out.println("__________________________________");
                        System.out.println("UPDATE DATA");
                        System.out.println("__________________________________");
                        System.out.println("Enter student ID to update:");
                        long updateId = sc.nextLong();
                        sc.nextLine();
                        boolean found = false;

                        for (int i = 0; i < size; ++i) {
                            if (students[i].getId() == updateId) {
                                System.out.println("1) Update Name\n2) Update ID\n3) Update Contact\n4) Change Gender");
                                int updateOption = sc.nextInt();
                                sc.nextLine();
                                try {
                                    switch (updateOption) {
                                        case 1:
                                            System.out.println("Enter new name:");
                                            String newName = sc.nextLine();
                                            if (!newName.matches("[a-zA-Z ]+")) {
                                                throw new IllegalArgumentException("Invalid input format: Name should contain only alphabets.");
                                            }
                                            students[i].setName(newName);
                                            System.out.println("Name updated successfully!");
                                            break;
                                        case 2:
                                            System.out.println("Enter new ID:");
                                            String newIdStr = sc.nextLine();
                                            if (!newIdStr.matches("\\d+")) {
                                                throw new IllegalArgumentException("Invalid input format: ID should contain only numbers.");
                                            }
                                            long newId = Long.parseLong(newIdStr);
                                            students[i].setId(newId);
                                            System.out.println("ID updated successfully!");
                                            break;
                                        case 3:
                                            System.out.println("Enter new contact (10 digits):");
                                            String newContactStr = sc.nextLine();
                                            if (!newContactStr.matches("\\d{10}")) {
                                                throw new IllegalArgumentException("Invalid input format: Contact should be exactly 10 digits.");
                                            }
                                            long newContact = Long.parseLong(newContactStr);
                                            students[i].setContact(newContact);
                                            System.out.println("Contact updated successfully!");
                                            break;
                                        case 4:
                                            System.out.println("Enter new gender (Male/Female/Other):");
                                            String newGender = sc.nextLine();
                                            if (!newGender.equalsIgnoreCase("Male") && !newGender.equalsIgnoreCase("Female") && !newGender.equalsIgnoreCase("Other")) {
                                                throw new IllegalArgumentException("Invalid input format: Gender should be Male, Female, or Other.");
                                            }
                                            students[i].setGender(newGender);
                                            System.out.println("Gender updated successfully!");
                                            break;
                                        default:
                                            System.out.println("Invalid option!");
                                    }
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Student with ID " + updateId + " not found.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("DELETE DATA");
                    if (size == 0) {
                        System.out.println("__________________________________");
                        System.out.println("****NO CURRENT DATA***");
                        System.out.println("__________________________________");
                    } else {
                        System.out.println("__________________________________");
                        System.out.println("DELETE DATA");
                        System.out.println("__________________________________");
                        System.out.println("Enter student ID to delete:");
                        long deleteId = sc.nextLong();
                        sc.nextLine();
                        boolean found = false;

                        for (int i = 0; i < size; ++i) {
                            if (students[i].getId() == deleteId) {
                                for (int j = i; j < size - 1; ++j) {
                                    students[j] = students[j + 1];
                                }

                                --size;
                                System.out.println("Student deleted successfully!");
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Student with ID " + deleteId + " not found.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting student management.");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

class Stu {
    private String name;
    private long id;
    private long contact;
    private String gender;

    public Stu(String name, long id, long contact, String gender) {
        this.name = name;
        this.id = id;
        this.contact = contact;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Contact: " + contact);
        System.out.println("Gender: " + gender);
    }
}