
public class Record {
    private String fio;
    private int pay;
    private int days;
    private String date;
    private String card;
    
    private Record() {}
    
    
    public Record(String fio, int pay, int days, String date, String card) {
        this.fio = fio;
        this.pay = pay;
        this.days = days;
        this.date = date;
        this.card = card;
    }
    
    
    public String Fio() {
        return fio;
    }
    
    
    public int Pay() {
        return pay;
    }
    
    
    public String Date() {
        return date;
    }
    
    
    public int Days() {
        return days;
    }
    
    
    public String Card() {
        return card;
    }
    
    
    public String Line() {
        return fio + " " + String.valueOf(pay) + " " +
                String.valueOf(days) + " " +
                date + " " + card + "\n";
    }
}
