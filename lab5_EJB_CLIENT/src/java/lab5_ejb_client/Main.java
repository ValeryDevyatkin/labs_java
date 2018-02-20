package lab5_ejb_client;

import com.MyNewSessionBeanRemote;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.ejb.EJB;
import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener{

    @EJB
    private static MyNewSessionBeanRemote myNewSessionBean;
    
    Font font;
    TextArea lbl;
    TextArea txa;
    Button ball, bdep, bgroup, binfo, bdel, badd;
    GridBagLayout lay;
    GridBagConstraints c;
    
    public Main() {
        super("ejb-client");
        setSize(250, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        
        font = new Font("arial", Font.PLAIN, 20);
        ball = new Button("show All");
            ball.setName("name_ball");
        bdep = new Button("show (department)");
            bdep.setName("name_bdep");
        bgroup = new Button("show (group)");
            bgroup.setName("name_bgroup");
        binfo = new Button("info (fio)");
            binfo.setName("name_binfo");
        bdel = new Button("delete (fio)");
            bdel.setName("name_bdel");
        badd = new Button("add (fio, dep., gr.)");
            badd.setName("name_badd");
        txa = new TextArea(1, 1);
        lbl = new TextArea(1, 1);
        lbl.setEditable(false);
            
        ball.setFont(font);
        bdep.setFont(font);
        bgroup.setFont(font);
        binfo.setFont(font);
        bdel.setFont(font);
        badd.setFont(font);
        lbl.setFont(font);
        txa.setFont(font);
        
        ball.addActionListener(this);
        bdep.addActionListener(this);
        bgroup.addActionListener(this);
        binfo.addActionListener(this);
        bdel.addActionListener(this);
        badd.addActionListener(this);
        
        ball.setBackground(new Color(198,229,166));
        bdep.setBackground(new Color(198,229,166));
        bgroup.setBackground(new Color(198,229,166));
        binfo.setBackground(new Color(198,229,166));
        bdel.setBackground(new Color(198,229,166));
        badd.setBackground(new Color(198,229,166));
        lbl.setBackground(new Color(46,51,64));
        txa.setBackground(new Color(176,45,70));
        getContentPane().setBackground(new Color(60,63,65));
        
        ball.setForeground(new Color(46,51,64));
        bdep.setForeground(new Color(46,51,64));
        bgroup.setForeground(new Color(46,51,64));
        binfo.setForeground(new Color(46,51,64));
        bdel.setForeground(new Color(46,51,64));
        badd.setForeground(new Color(46,51,64));
        lbl.setForeground(new Color(20,138,191));
        txa.setForeground(new Color(46,51,64));
        
        lay = new GridBagLayout();
	c = new GridBagConstraints();
	setLayout(lay);
        
        c.ipadx = 150;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        add(txa, c);
        
        c.ipadx = 200;
        c.ipady = 200;
        c.gridy = 1;
        add(lbl, c);
        c.ipadx = 0;
        c.ipady = 0;
        
        c.gridy = 2;
        add(ball, c);
        
        c.gridy = 3;
        add(bdep, c);
        
        c.gridy = 4;
        add(bgroup, c);
        
        c.gridy = 5;
        add(binfo, c);
        
        c.gridy = 6;
        add(bdel, c);
        
        c.gridy = 7;
        add(badd, c);
        
        show();
    }
    
    public static void main(String[] args) {
        new Main();
    }
    
    public void actionPerformed(ActionEvent ae) {
        Button x =(Button)ae.getSource();
        switch(x.getName()) {
            case "name_ball": 
                lbl.setText(myNewSessionBean.showAll());
                break;
            case "name_bdep": 
                lbl.setText(myNewSessionBean.showOnDepartment(txa.getText().trim()));
                break;
            case "name_bgroup":
                lbl.setText(String.valueOf(myNewSessionBean.amountInGroup(txa.getText().trim())));
                break;
            case "name_binfo": 
                lbl.setText(myNewSessionBean.getStudInfo("Ivanov"));
                break;
            case "name_bdel": 
                myNewSessionBean.delStudent(txa.getText().trim());
                break;
            case "name_badd": 
                String str[] = txa.getText().trim().split("\n");
                if (str.length > 1) {
                    str[0] = str[0].trim();
                    str[1] = str[1].trim();
                    str[2] = str[2].trim();
                    myNewSessionBean.setFio(str[0]);
                    myNewSessionBean.setDepartment(str[1]);
                    myNewSessionBean.setGroup(str[2]);
                    myNewSessionBean.addStudent();
                }
                break;
            default:
        }
    }
}
