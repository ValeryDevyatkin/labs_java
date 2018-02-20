package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.ejb.Stateless;

@Stateless
public class MyNewSessionBean implements MyNewSessionBeanRemote {

    private static final long serialVersionUID = 1L;
 
    private String fio;
    private String department;
    private String group;
  
    private static final ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(
        new Student("Ivanov", "Marketing", "10"),
        new Student("Petrov", "Marketing", "10"),
        new Student("Sidorov", "Professor", "12"),
        new Student("Mishin", "Smith", "12"),
        new Student("Vasin", "Programmer", "14")
    ));   
 
    @Override
    public String getStudInfo(String name) {
        String result = "";
        for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student st = it.next();
            if(st.getFio().equals(name)) {
                result += (st.getDepartment() + " " + st.getGroup());
            }
        }
        return result;    
    }
 
    @Override
    public void setGroup(String gr) {
        this.group = gr;
    }
    
    @Override
    public void setDepartment(String dep) {
        this.department = dep;
    }
    
    @Override
    public void setFio(String name) {
        this.fio = name;
    }

    @Override
    public void addStudent() {
        Student std = new Student(fio, department, group);
        students.add(std);
    }

    @Override
    public void delStudent(String name) {
        for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student st = it.next();
            if(st.getFio().equals(name)) {
                students.remove(st);
            }
        }   
    }

    @Override
    public String showAll() {
        String result = "";
        for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student st = it.next();
            result += (st.getFio() + " " + st.getDepartment() + " " + st.getGroup() + "\n");
        }
        return result;
    }

    @Override
    public String showOnDepartment(String dep) {
        String result = "";
        for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student st = it.next();
            if (st.getDepartment().equals(dep)) {
                result += (st.getFio() + " " + st.getGroup() + "\n");
            }
        }
        return result;
    }

    @Override
    public int amountInGroup(String gr) {
        int result = 0;
        for (Iterator<Student> it = students.iterator(); it.hasNext();) {
            Student st = it.next();
            if (st.getGroup().equals(gr)) {
                result ++;
            }
        }
        return result;
    }

}
