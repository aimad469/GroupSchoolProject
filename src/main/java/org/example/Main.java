package org.example;

import org.example.model.Employee;
import org.example.model.EmployeeTree;
import org.example.model.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public final static String FILE_PATH = "src/main/resources";
    public static void main(String[] args) {

        System.out.println("Please enter the filename to read: ");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        System.out.println("file name to read : " + fileName);
        List<String> linesList = readFile(fileName);
        List<Employee> employeeList = parseList(linesList);
        System.out.println("employee list: " + employeeList);
        List<Employee> sortedEmployeeList = new ArrayList<>();
         boolean exitMenu = false;
        displayMenu();


        while(!exitMenu) {
            String choice = input.next();
            switch (choice) {
                case "1" -> {
                    System.out.println(Menu.SORT + " selected");
                    sortedEmployeeList = sort(employeeList);
                    display20firstNames(sortedEmployeeList);
                    displayMenu();
                }
                case "2" -> {
                    System.out.println(Menu.SEARCH + " selected");
                    searchIn(sortedEmployeeList);
                    displayMenu();
                }
                case "3" -> {
                    System.out.println(Menu.ADD_RECORDS + " selected");
                    addRecord(sortedEmployeeList);
                    displayMenu();
                }
                case "4" -> {
                    System.out.println(Menu.CREATE_BINARY_TREE + " selected");
                    createEmployeeTree(sortedEmployeeList);
                    displayMenu();
                }
                case "5" -> {
                    System.out.println(Menu.EXIT + " selected, Bye Bye !!!");
                    exitMenu = true;
                }
                default -> {
                    System.out.println("choice unrecognized!! please enter a number between 1 and 5");
                }

            }
        }
    }
     public static void displayMenu(){
        System.out.println("Do You wish to SORT or SEARCH: ");
        System.out.println("1. " + Menu.SORT);
        System.out.println("2. " + Menu.SEARCH);
        System.out.println("3. " + Menu.ADD_RECORDS);
        System.out.println("4. " + Menu.CREATE_BINARY_TREE);
        System.out.println("5. " + Menu.EXIT);
    }
    public static List<String> readFile(String fileName){
        List<String> linesList = new ArrayList<>();

        try {

            // Read all lines into a List
            linesList = Files.readAllLines(Paths.get(FILE_PATH,fileName));
            System.out.println("File read successfully");

            System.out.println("File contents :");
            for (int i = 1; i < linesList.size(); i++) {
                System.out.printf("Line %d: %s%n", i , linesList.get(i));
            }
        } catch (IOException e) {
            System.err.println("issue while reading file : " + fileName);
        }
        return linesList;

    }

    public static List<Employee> parseList(List<String> linesList){
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i < linesList.size(); i++) {
            String line = linesList.get(i);
            if(line.isBlank()){continue;}
            String[] columns = line.split(",");
            Employee emp = new Employee(columns[0],columns[1],columns[2],columns[3],columns[4],columns[5],columns[6],columns[7]);
            employeeList.add(emp);
        }

        return employeeList;
    }

    public static List<Employee> sort(List<Employee> employeeList){
        recursiveSort(employeeList, employeeList.size());
        return employeeList;
    }

    public static void recursiveSort(List<Employee> employeeList, int n){

        if (n <= 1) return;
        Employee emp = employeeList.get(n - 1);
        for (int i = 0; i < n - 1; i++) {
            if (employeeList.get(i).compareTo(employeeList.get(i + 1)) > 0) {
                // Swap if out of order
                Employee temp = employeeList.get(i);
                employeeList.set(i, employeeList.get(i + 1));
                employeeList.set(i + 1, temp);
            }
        }
        recursiveSort(employeeList, n - 1); // Sort first n-1 elements
    }

    public static void display20firstNames(List<Employee> employeeList){
        System.out.println("20 first names after sort: ");
        for (int i =0; i< 20; i++) {
            System.out.println(i+1 +"- First name: " +employeeList.get(i).getFirstName() + ", Last name: " + employeeList.get(i).getLastName());
        }
    }

    public static void searchIn(List<Employee> sortedEmployeeList){
        if(sortedEmployeeList.isEmpty()){
            System.out.println("you should sort the list first !! ");
            return;
        }
        System.out.println("Please enter the First name: ");
        Scanner input1 = new Scanner(System.in);
        String firstName = input1.nextLine().trim();
        System.out.println("Please enter the Last name: ");
        Scanner input2 = new Scanner(System.in);
        String lastName = input2.nextLine().trim();
        Employee emp = recursiveSearch(firstName, lastName, sortedEmployeeList);
        if(emp.getFirstName()== null){
            System.out.println("Employee with First name: " + firstName + " and Last name: " + lastName + " is not found !!!");
        }
        else{
            System.out.println("Employee with First name: " + firstName + " and Last name: " + lastName + " is found");
            System.out.println(emp);
        }
    }
    public static Employee recursiveSearch(String firstName, String lastName, List<Employee> sortedEmployeeList){

        Employee emp = new Employee();
        if(sortedEmployeeList.isEmpty()){
            return emp;
        }
        else {
            int middleIndex = sortedEmployeeList.size() / 2;
            Employee middle = sortedEmployeeList.get(middleIndex);

            if (middle.getFirstName().compareToIgnoreCase(firstName) == 0 && middle.getLastName().compareToIgnoreCase(lastName) == 0) {
                emp = middle;
            } else if (firstName.compareToIgnoreCase(middle.getFirstName()) <= 0) {
                emp = recursiveSearch(firstName, lastName, sortedEmployeeList.subList(0, middleIndex ));
            } else {
                emp = recursiveSearch(firstName, lastName, sortedEmployeeList.subList(middleIndex , sortedEmployeeList.size() - 1));
            }
        }
        return  emp;
    }

    public static void addRecord(List<Employee> sortedEmployeeList){

        if(sortedEmployeeList.isEmpty()){
            System.out.println("you should sort the list first !! ");
            return;
        }

        List<String> fields = getFields(sortedEmployeeList);
        List<String> departments = getDepartments(sortedEmployeeList);
        List<String> positions = getPositions(sortedEmployeeList);
        Employee emp = new Employee();

        System.out.println("Adding a new Employee ");
        System.out.println("Please enter the First name: ");
        Scanner input = new Scanner(System.in);
        String firstName = input.nextLine().trim();
        emp.setFirstName(firstName);
        System.out.println("Please enter the Last name: ");
        String lastName = input.nextLine().trim();
        emp.setLastName(lastName);
        boolean exitFieldsMenu = false;
        while(!exitFieldsMenu){
            exitFieldsMenu = true;
            displayFieldsMenu(fields);
            int choice = input.nextInt();
            if(choice < 1 || choice >= fields.size()){
                System.out.println("Please choose a number between 1 and " + fields.size());
                exitFieldsMenu = false;
            }
            else{
                int index = Integer.valueOf(choice) -1;
                emp.setField(fields.get(index));
            }
        }
        boolean exitDepartmentsMenu = false;
        while(!exitDepartmentsMenu){
            exitDepartmentsMenu = true;
            displayDepartmentsMenu(departments);
            int choice = input.nextInt();
            if(choice < 1 || choice >=  departments.size()){
                System.out.println("Please choose a number between 1 and " + departments.size());
                exitDepartmentsMenu = false;
            }
            else{
                int index = Integer.valueOf(choice) -1;
                emp.setDepartment(departments.get(index));
            }
        }
 boolean exitPositionsMenu = false;
        while(!exitPositionsMenu){
            exitPositionsMenu = true;
            displayPositionsMenu(positions);
            int choice = input.nextInt();
            if(choice < 1 || choice >= positions.size()){
                System.out.println("Please choose a number between 1 and " + positions.size());
                exitPositionsMenu = false;
            }
            else{
                int index = Integer.valueOf(choice) -1
;                emp.setPosition(positions.get(index));
            }
        }
        sortedEmployeeList.add(emp);
        sort(sortedEmployeeList);
        System.out.println(emp.getFirstName() + " " + emp.getLastName() + " has been added as " + emp.getField() + " to " + emp.getDepartment() + " successfully");
        display20firstNames(sortedEmployeeList);

    }

    private static List<String> getFields(List<Employee> sortedEmployeeList){
        List<String> fields = new ArrayList<>();
        for (Employee emp: sortedEmployeeList) {
            if(!fields.contains(emp.getField().trim())) {
                fields.add(emp.getField().trim());
            }
        }
        return fields;
    }

    private static List<String> getDepartments(List<Employee> sortedEmployeeList){
        List<String> departments = new ArrayList<>();
        for (Employee emp: sortedEmployeeList) {
            if(!departments.contains(emp.getDepartment())) {
                departments.add(emp.getDepartment().trim());
            }
        }
        return departments;
    }
    
    private static List<String> getPositions(List<Employee> sortedEmployeeList){
        List<String> positions = new ArrayList<>();
        for (Employee emp: sortedEmployeeList) {
            if(!positions.contains(emp.getDepartment())) {
                positions.add(emp.getDepartment().trim());
            }
        }
        return positions;
    }
    
  

    private static void displayFieldsMenu(List<String> fields){
        System.out.println("Please select from the following fields: ");
        for (int i = 0; i < fields.size(); i++) {
            System.out.println(i+1 +". " + fields.get(i));
        }
    }

    private static void displayDepartmentsMenu(List<String> departments){
        System.out.println("Please select from the following Departments: ");
        for (int i = 0; i < departments.size(); i++) {
            System.out.println(i+1 +". " + departments.get(i));
        }
    }

    
   private static void displayPositionsMenu(List<String> positions){
        System.out.println("Please select from the following positions: ");
        for (int i = 0; i < positions.size(); i++) {
            System.out.println(i+1 +". " + positions.get(i));
        }
    } 
   
    private static void createEmployeeTree(List<Employee> sortedEmployeeList){

        if(sortedEmployeeList.isEmpty()){
            System.out.println("you should sort the list first !! ");
            return;
        }

        Node root = new Node(sortedEmployeeList.get(0));
        root.setLeft(new Node(sortedEmployeeList.get(1)));
        root.setRight(new Node(sortedEmployeeList.get(2)));
        root.getLeft().setLeft(new Node(sortedEmployeeList.get(3)));
        root.getLeft().setRight(new Node(sortedEmployeeList.get(4)));
        root.getRight().setLeft(new Node(sortedEmployeeList.get(5)));
        root.getRight().setRight(new Node(sortedEmployeeList.get(6)));
        root.getLeft().getLeft().setLeft(new Node(sortedEmployeeList.get(7)));
        root.getLeft().getLeft().setRight(new Node(sortedEmployeeList.get(8)));
        root.getLeft().getRight().setLeft(new Node(sortedEmployeeList.get(9)));
        root.getLeft().getRight().setRight(new Node(sortedEmployeeList.get(10)));
        root.getRight().getLeft().setLeft(new Node(sortedEmployeeList.get(11)));
        root.getRight().getLeft().setRight(new Node(sortedEmployeeList.get(12)));
        root.getRight().getRight().setLeft(new Node(sortedEmployeeList.get(13)));
        root.getRight().getRight().setRight(new Node(sortedEmployeeList.get(14)));
        root.getLeft().getLeft().getLeft().setLeft(new Node(sortedEmployeeList.get(15)));
        root.getLeft().getLeft().getLeft().setRight(new Node(sortedEmployeeList.get(16)));
        root.getLeft().getLeft().getRight().setLeft(new Node(sortedEmployeeList.get(17)));
        root.getLeft().getLeft().getRight().setRight(new Node(sortedEmployeeList.get(18)));
        root.getLeft().getRight().getLeft().setLeft(new Node(sortedEmployeeList.get(19)));
        root.getLeft().getRight().getLeft().setRight(new Node(sortedEmployeeList.get(20)));

        EmployeeTree tree = new EmployeeTree();

        ArrayList<ArrayList<Node>> res = tree.levelOrder(root);
        int totalNodes = 0;

        for (ArrayList<Node> level : res) {
            System.out.print("[ ");
            for (Node node : level) {
                totalNodes++;
                System.out.print( "(" + node.getEmployee().getFirstName()+ " " + node.getEmployee().getLastName() + ") ");
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println(" tree height : " + res.size() + ", and total nodes : " + totalNodes);

    }

}
