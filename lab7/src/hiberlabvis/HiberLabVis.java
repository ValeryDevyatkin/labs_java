package hiberlabvis;

import java.awt.Button;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import javax.swing.JFrame;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HiberLabVis extends JFrame implements ActionListener {

    // JDBC URL, username and password of MySQL server
    String url = "jdbc:derby://localhost:1527/myjdb";
    String user = "root";
    String password = "vjzrjhjdf";
    Connection con;
    Session session;

    Button ball = new Button("показать таблицу");
    Button bdel = new Button("удалить");
    Button badd = new Button("добавить");
    Button bfix = new Button("изменить");
    Button bfind = new Button("найти");
    Label lfio = new Label("Имя");
    Label lage = new Label("Возраст");
    TextField tfio = new TextField();
    TextField tage = new TextField();
    TextArea txa = new TextArea(1, 1);
    Font font = new Font("arial", Font.PLAIN, 20);
    GridBagLayout lay = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    public HiberLabVis() {
        super("lab7");
        setSize(600, 300);
        getContentPane().setBackground(new Color(0, 41, 81));
        setLayout(lay);
        ball.addActionListener(this);
        badd.addActionListener(this);
        bdel.addActionListener(this);
        bfix.addActionListener(this);
        bfind.addActionListener(this);
        ball.setName("name_ball");
        badd.setName("name_badd");
        bdel.setName("name_bdel");
        bfix.setName("name_bfix");
        bfind.setName("name_bfind");
        ball.setFont(font);
        badd.setFont(font);
        bdel.setFont(font);
        bfix.setFont(font);
        bfind.setFont(font);
        lfio.setFont(font);
        lage.setFont(font);
        tfio.setFont(font);
        tage.setFont(font);
        txa.setFont(font);
        ball.setBackground(new Color(32, 3, 2));
        badd.setBackground(new Color(32, 3, 2));
        bdel.setBackground(new Color(32, 3, 2));
        bfix.setBackground(new Color(32, 3, 2));
        bfind.setBackground(new Color(32, 3, 2));
        lfio.setBackground(new Color(0, 41, 81));
        lage.setBackground(new Color(0, 41, 81));
        tfio.setBackground(new Color(30, 163, 98));
        tage.setBackground(new Color(30, 163, 98));
        txa.setBackground(new Color(30, 163, 98));
        ball.setForeground(new Color(0253, 182, 21));
        badd.setForeground(new Color(30, 163, 98));
        bdel.setForeground(new Color(30, 163, 98));
        bfix.setForeground(new Color(30, 163, 98));
        bfind.setForeground(new Color(30, 163, 98));
        lfio.setForeground(new Color(252, 190, 23));
        lage.setForeground(new Color(252, 190, 23));
        tfio.setForeground(new Color(32, 3, 2));
        tage.setForeground(new Color(32, 3, 2));
        txa.setForeground(new Color(32, 3, 2));

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        add(lfio, c);
        c.gridy = 1;
        add(lage, c);
        c.gridy = 2;
        add(badd, c);
        c.gridy = 3;
        add(bfix, c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 150;
        add(tfio, c);
        c.gridy = 1;
        add(tage, c);
        c.ipadx = 0;
        c.gridy = 2;
        add(bdel, c);
        c.gridy = 3;
        add(bfind, c);
        
        c.gridx = 2;
        c.gridy = 0;
        c.ipady = 150;
        c.ipadx = 150;
        c.gridheight = 3;
        add(txa, c);
        c.gridheight = 0;
        c.ipady = 0;
        c.gridy = 3;
        c.ipadx = 0;
        add(ball, c);
        
        show();
        setLocationRelativeTo(null);
        setResizable(false);
        txa.setEditable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try { con.close(); } catch (Exception ex) { ex.printStackTrace(); }
                System.exit(0);
            }
        });
        try {
            //connecting driver
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            // opening database connection to server
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) { ex.printStackTrace();}
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Button x = (Button)ae.getSource();
        switch (x.getName()) {
            case "name_ball": {
                    session = new Configuration().configure().buildSessionFactory().openSession();
                    session.beginTransaction();
                    List<Stud> result = session.createQuery("from Stud").list();
                    String text = "";
                    for (Stud s : result) {
                        text += (s.getFio() + "  " + s.getAge() + "\n");
                    }
                    txa.setText(text);
                    session.getTransaction().commit();
                }
                break;
            case "name_badd":
                if (!tfio.getText().trim().isEmpty() && !tage.getText().trim().isEmpty()) {
                    session = new Configuration().configure().buildSessionFactory().openSession();
                    session.beginTransaction();
                    //Stud s = new Stud();//tfio.getText().trim(), Integer.valueOf(tage.getText().trim())
                    //session.save(s);
                    Query sqlq = session.createSQLQuery("insert into Stud values (:fioParam, :ageParam)");
                    sqlq.setParameter("fioParam", tfio.getText().trim());
                    sqlq.setParameter("ageParam", Integer.valueOf(tage.getText().trim()));
                    sqlq.executeUpdate();
                    session.getTransaction().commit();
                }
                break;
            case "name_bdel":
                if (!tfio.getText().trim().isEmpty()) {
                    session = new Configuration().configure().buildSessionFactory().openSession();
                    session.beginTransaction();
                    Stud s = new Stud();
                    s.setFio(tfio.getText().trim());
                    session.delete(s);
                    session.getTransaction().commit();
                }
                break;
            case "name_bfix":
                if (!tfio.getText().trim().isEmpty() && !tage.getText().trim().isEmpty()) {
                    session = new Configuration().configure().buildSessionFactory().openSession();
                    session.beginTransaction();
                    Stud s = new Stud(tfio.getText().trim(), Integer.valueOf(tage.getText().trim()));
                    session.update(s);
                    session.getTransaction().commit();
                }
                break;
            case "name_bfind": 
                if (!tage.getText().trim().isEmpty()) {
                    session = new Configuration().configure().buildSessionFactory().openSession();
                    session.beginTransaction();
                    Query query = session.createQuery("from Stud where age = :ageParam");
                    query.setParameter("ageParam", Integer.valueOf(tage.getText().trim()));
                    List<Stud> result = query.list();
                    String text = "";
                    for (Stud s : result) {
                      text += (s.getFio() + "  " + s.getAge() + "\n");
                    }
                    txa.setText(text);
                    session.getTransaction().commit();
                }
                break;
            default:
        }
    }

    public static void main(String[] args) {
        new HiberLabVis();
    }
}
