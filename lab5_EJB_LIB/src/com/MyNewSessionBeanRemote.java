package com;

import javax.ejb.Remote;

@Remote
public interface MyNewSessionBeanRemote {

    String getStudInfo(String fio);

    void addStudent();

    void delStudent(String stud);

    String showAll();

    String showOnDepartment(String department);

    int amountInGroup(String group);
    
    void setFio(String fio);
    
    void setDepartment(String department);
    
    void setGroup(String group);

}
