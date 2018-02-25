package hiberlabvis;

class Stud {
    private String fio = "default";
    private int age = 0;

    public Stud() {}

    public Stud(String fio, int age) {
        this.fio = fio;
        this.age = age;
    } 
    
    public int getAge() {
        return age;
    }

    public String getFio() {
        return fio;
    }
    
    public void setFio(String fio) {
        this.fio = fio;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
