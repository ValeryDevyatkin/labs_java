import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MyForm extends JFrame implements ActionListener{
    
    // JDBC URL, username and password of MySQL server
    String url = "jdbc:mysql://localhost:3306/java_task_db";
    String user = "root";
    String password = "xxx";
    // JDBC variables for opening and managing connection
    Connection con;
    Statement stmt;
    ResultSet rs;
    String query;
    Vector<Record> records = new Vector<Record>();

    Button ball = new Button("показать таблицу");
    Button bdel = new Button("удалить");
    Button badd = new Button("добавить");
    Button bfix = new Button("изменить");
    Button bfind = new Button("найти");
    Button bfile = new Button("в файл");
    Label lfio = new Label("Фио");
    Label lpay = new Label("Выплата");
    Label ldays = new Label("Кол-во дней болезни");
    Label ldate = new Label("Дата выплаты");
    Label lcard = new Label("Больничная карта");
    TextField tfio = new TextField();
    TextField tpay = new TextField();
    TextField tdays = new TextField();
    TextField tdate = new TextField();
    TextField tcard = new TextField();
    TextArea txa = new TextArea(1, 1);
    Font font = new Font("arial", Font.PLAIN, 20);
    GridBagLayout lay = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    
    Choice choice = new Choice();
    

    public MyForm() {
        super("lab7");
        setSize(800, 500);
        getContentPane().setBackground(new Color(0, 41, 81));
        setLayout(lay);
        
        choice.add("фио");
        choice.add("выплата");
        choice.add("дни");
        choice.add("дата");
        choice.add("карта");
        choice.select("фио");
        
        ball.addActionListener(this);
        badd.addActionListener(this);
        bdel.addActionListener(this);
        bfix.addActionListener(this);
        bfind.addActionListener(this);
        bfile.addActionListener(this);
        ball.setName("name_ball");
        badd.setName("name_badd");
        bdel.setName("name_bdel");
        bfix.setName("name_bfix");
        bfind.setName("name_bfind");
        bfile.setName("name_bfile");
        bfile.setFont(font);
        ball.setFont(font);
        badd.setFont(font);
        bdel.setFont(font);
        bfix.setFont(font);
        bfind.setFont(font);
        lfio.setFont(font);
        lpay.setFont(font);
        ldate.setFont(font);
        ldays.setFont(font);
        lcard.setFont(font);
        tfio.setFont(font);
        tpay.setFont(font);
        tdays.setFont(font);
        tcard.setFont(font);
        tdate.setFont(font);
        txa.setFont(font);
        choice.setFont(font);
        choice.setBackground(new Color(32, 3, 2));
        bfile.setBackground(new Color(32, 3, 2));
        ball.setBackground(new Color(32, 3, 2));
        badd.setBackground(new Color(32, 3, 2));
        bdel.setBackground(new Color(32, 3, 2));
        bfix.setBackground(new Color(32, 3, 2));
        bfind.setBackground(new Color(32, 3, 2));
        lfio.setBackground(new Color(0, 41, 81));
        lpay.setBackground(new Color(0, 41, 81));
        ldate.setBackground(new Color(0, 41, 81));
        ldays.setBackground(new Color(0, 41, 81));
        lcard.setBackground(new Color(0, 41, 81));
        tfio.setBackground(new Color(30, 163, 98));
        tpay.setBackground(new Color(30, 163, 98));
        tdate.setBackground(new Color(30, 163, 98));
        tdays.setBackground(new Color(30, 163, 98));
        tcard.setBackground(new Color(30, 163, 98));
        txa.setBackground(new Color(30, 163, 98));
        bfile.setForeground(new Color(0253, 182, 21));
        ball.setForeground(new Color(0253, 182, 21));
        badd.setForeground(new Color(30, 163, 98));
        bdel.setForeground(new Color(30, 163, 98));
        bfix.setForeground(new Color(30, 163, 98));
        bfind.setForeground(new Color(30, 163, 98));
        lfio.setForeground(new Color(252, 190, 23));
        lpay.setForeground(new Color(252, 190, 23));
        ldate.setForeground(new Color(252, 190, 23));
        ldays.setForeground(new Color(252, 190, 23));
        lcard.setForeground(new Color(252, 190, 23));
        choice.setForeground(new Color(252, 190, 23));
        tfio.setForeground(new Color(32, 3, 2));
        tpay.setForeground(new Color(32, 3, 2));
        tdays.setForeground(new Color(32, 3, 2));
        tdate.setForeground(new Color(32, 3, 2));
        tcard.setForeground(new Color(32, 3, 2));
        txa.setForeground(new Color(32, 3, 2));

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        add(lfio, c);
        c.gridy = 1;
        add(lpay, c);
        c.gridy = 2;
        add(ldays, c);
        c.gridy = 3;
        add(ldate, c);
        c.gridy = 4;
        add(lcard, c);
        c.gridy = 5;
        add(badd, c);
        c.gridy = 6;
        add(bfix, c);
        c.gridy = 7;
        add(bfile, c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 150;
        add(tfio, c);
        c.gridy = 1;
        add(tpay, c);
        c.gridy = 2;
        add(tdays, c);
        c.gridy = 3;
        add(tdate, c);
        c.gridy = 4;
        add(tcard, c);
        c.ipadx = 0;
        c.gridy = 5;
        add(bdel, c);
        c.gridy = 6;
        add(bfind, c);
        c.gridy = 7;
        add(choice, c);
        
        c.gridx = 2;
        c.gridy = 0;
        c.ipady = 300;
        c.ipadx = 300;
        c.gridheight = 5;
        add(txa, c);
        c.gridheight = 0;
        c.ipady = 0;
        c.gridy = 5;
        c.ipadx = 0;
        add(ball, c);
        
        show();
        setLocationRelativeTo(null);
        setResizable(false);
        txa.setEditable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try { con.close(); } catch (Exception ex) {
                    System.out.print("ошибка при выходе");
                }
                System.exit(0);
            }
        });
        
        try {
            // opening database connection to server
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.print("ошибка в конструкторе");
        }
    }

    
    void SortAndShow(String criteria) {
        txa.setText("");
        Record t;
        
        for (int i = 0; i < records.size() - 1; i++)
            for (int j = i; j < records.size(); j++) {
                
                switch(criteria) {
                    case "фио":
                    {
                        String a = records.elementAt(i).Fio();
                        String b = records.elementAt(j).Fio();
                        if (a.compareTo(b) == 1) {
                            t = records.elementAt(i);
                            records.set(i, records.elementAt(j));
                            records.set(j, t);
                        }
                        break;
                    }
                        
                        
                    case "дни":
                    {
                        int a = records.elementAt(i).Days();
                        int b = records.elementAt(j).Days();
                        if (a > b) {
                            t = records.elementAt(i);
                            records.set(i, records.elementAt(j));
                            records.set(j, t);
                        }
                        break;
                    }
                        
                    case "выплата":
                    {
                        int a = records.elementAt(i).Pay();
                        int b = records.elementAt(j).Pay();
                        if (a > b) {
                            t = records.elementAt(i);
                            records.set(i, records.elementAt(j));
                            records.set(j, t);
                        }
                        break;
                    }
                        
                    case "дата":
                    {
                        String a = records.elementAt(i).Date();
                        String b = records.elementAt(j).Date();
                        if (a.compareTo(b) == 1) {
                            t = records.elementAt(i);
                            records.set(i, records.elementAt(j));
                            records.set(j, t);
                        }
                        break;
                    }
                        
                    case "карта":
                    {
                        String a = records.elementAt(i).Card();
                        String b = records.elementAt(j).Card();
                        if (a.compareTo(b) == 1) {
                            t = records.elementAt(i);
                            records.set(i, records.elementAt(j));
                            records.set(j, t);
                        }
                        break;
                    }
                        
                    default:
                }
                
            }
        
        for (Record x : records) {
            txa.setText(txa.getText() + x.Line());
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Button x = (Button)ae.getSource();
        
        switch (x.getName()) {
            case "name_bfile":
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("doc", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(this);
                
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    try(FileWriter writer = new FileWriter(f, false))
                    {
                        // запись всей строки
                        writer.write(txa.getText());
                        writer.flush();
                    }
                    catch(IOException e){}
                }
                break;
                
                
            case "name_ball":
                records.clear();
                try {
                    query = "select * from payments";
                    rs = stmt.executeQuery(query);
                    
                    while (rs.next()) {
                        records.add(new Record(
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6)
                        ));
                    }
                    
                    SortAndShow(choice.getSelectedItem());
                } catch(Exception e) {}
                break;
                
                
            case "name_badd":
                if (
                        !tfio.getText().trim().equals("") &&
                        !tpay.getText().trim().equals("") &&
                        !tcard.getText().trim().equals("") &&
                        !tdays.getText().trim().equals("")
                ) {
                    try {
                        query = "insert into payments (fio, payment, illness_days, date_of_payment, hospital_card)" +
                                " values('" + 
                                tfio.getText().trim() + "','" +
                                tpay.getText().trim() + "','" +
                                tdays.getText().trim() + "',curdate(),'" +
                                tcard.getText().trim() + "')";
                        stmt.execute(query);
                    } catch(Exception e){System.out.print("ошибка добавления");}
                }
                break;
                
                
            case "name_bdel":
                if (!tfio.getText().trim().equals("")) {
                    try {
                        query = "delete from payments where fio = '" + tfio.getText().trim() + "'";
                        stmt.execute(query);
                    } catch(Exception e){System.out.print("ошибка удаления");}
                }
                break;
                
                
            case "name_bfix":
                if (!tfio.getText().trim().equals("")) {
                    try {
                        if (!tpay.getText().trim().equals(""))  {
                            query = "update payments set payment = '" + tpay.getText().trim() +
                                    "' where fio = '" + tfio.getText().trim() + "'";
                            stmt.execute(query);
                        }
                        if (!tdate.getText().trim().equals(""))  {
                            query = "update payments set date_of_payment = '" + tdate.getText().trim() +
                                    "' where fio = '" + tfio.getText().trim() + "'";;
                            stmt.execute(query);
                        }
                        if (!tdays.getText().trim().equals(""))  {
                            query = "update payments set illness_days = '" + tdays.getText().trim() +
                                    "' where fio = '" + tfio.getText().trim() + "'";;
                            stmt.execute(query);
                        }
                        if (!tcard.getText().trim().equals(""))  {
                            query = "update payments set hospital_card = '" + tcard.getText().trim() +
                                    "' where fio = '" + tfio.getText().trim() + "'";;
                            stmt.execute(query);
                        }
                    } catch(Exception e){System.out.print("ошибка обновления");}
                }
                break;
                
                
            case "name_bfind": 
                if (!tfio.getText().trim().equals(""))
                {
                    records.clear();
                    try {
                        query = "select * from payments where fio = '" + tfio.getText().trim() + "'";
                        rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            records.add(new Record(
                                    rs.getString(2),
                                    rs.getInt(3),
                                    rs.getInt(4),
                                    rs.getString(5),
                                    rs.getString(6)
                            ));
                        }
                        
                        SortAndShow(choice.getSelectedItem());
                    } catch(Exception e){}
                }
                break;
            default:
        }
    }
    
    
    public static void main(String[] args) {
        new MyForm();
    } 
}


