package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class mancomp {

    private static final ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(
            new Student("Ivanov", "Marketing", "10"),
            new Student("Petrov", "Marketing", "10"),
            new Student("Sidorov", "Professor", "12"),
            new Student("Mishin", "Smith", "12"),
            new Student("Vasin", "Programmer", "14")
    ));

    private String output = "";
    private String inFio;
    private String inDepartment;
    private String inGroup;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void findStudent() {
        if (!inFio.equals(""))
            output = "";
            for (Iterator<Student> it = students.iterator(); it.hasNext();) {
                Student st = it.next();
                if (st.getFio().equals(inFio)) {
                    output += (st.getFio() + " " + st.getDepartment() + " " + st.getGroup() + "/   ");
                }
            }
    }

    public void addStudent() {
        if (!inFio.equals("") && !inDepartment.equals("") && !inGroup.equals("")) {
            Student std = new Student(inFio, inDepartment, inGroup);
            students.add(std);
        }
    }

    public void delStudent() {
        if (!inFio.equals("") && !inDepartment.equals("") && !inGroup.equals(""))
            for (Iterator<Student> it = students.iterator(); it.hasNext();) {
                Student st = it.next();
                if (st.getFio().equals(inFio) && st.getDepartment().equals(inDepartment) && st.getGroup().equals(inGroup)) {
                    students.remove(st);
                    return;
                }
            }
    }

    public String getInFio() {
        return inFio;
    }
    public void setInFio(String input) {
        this.inFio = input.trim();
    }

    public String getInDepartment() {
        return inDepartment;
    }
    public void setInDepartment(String input) {
        this.inDepartment = input.trim();
    }

    public String getInGroup() {
        return inGroup;
    }
    public void setInGroup(String input) {
        this.inGroup = input.trim();
    }

    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output.trim();
    }
}
