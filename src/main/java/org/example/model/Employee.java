package org.example.model;

public class Employee implements  Comparable<Employee>{

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String salary;
    private String department;
    private String field;
    private String position;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String gender, String email, String salary, String department,String field ,String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.field = field;
        this.position = position;
        
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getField() {
        return field;
    }

    public void setField(String position) {
        this.field = field;
    }

    public String getPositin() {
        return position;
    }

    public void setPosition(String jobTitle) {
        this.position = position;
    }

   

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", salary='" + salary + '\'' +
                ", department='" + department + '\'' +
                ", jobTitle='" + field + '\'' +
                ", position='" + position + '\'' +
                
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return firstName.equals(employee.firstName);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode();
    }

    @Override
    public int compareTo(Employee o) {
        return this.getFirstName().compareToIgnoreCase(o.getFirstName());
    }
}
