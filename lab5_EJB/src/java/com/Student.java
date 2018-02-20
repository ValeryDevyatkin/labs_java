package com;

public class Student {
    private String fio;
    private String department;
    private String group;
  
    public Student (String fio, String department, String group) {
        this.fio = fio;
        this.department = department;
        this.group = group;
    }
 
    public String getFio() {
        return fio;
    }
 
    public String getDepartment() {
        return department;
    }
 
    public String getGroup() {
        return group;
    }
 
}
