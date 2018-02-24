package client_pack;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import service_pack.*;

public class myclient extends JFrame implements ActionListener {
    
    Font font;
    Button blist, bbook;
    TextArea txa;
    JComboBox cbox;
    GridBagLayout lay;
    GridBagConstraints c;
    Myservice_Service serv;
    
    public myclient() {
        
        super("web-service-client");
        setSize(380, 580);
        setResizable(false);
        setLocationRelativeTo(null);
        
        font = new Font("arial", Font.PLAIN, 20);
        blist = new Button("Show Book List");
            blist.setName("name_blist");
        bbook = new Button("Get Book");
            bbook.setName("name_bbook");
        txa = new TextArea(1,1);
        cbox = new JComboBox();
        serv = new Myservice_Service();
        
        blist.addActionListener(this);
        bbook.addActionListener(this);
        
        blist.setFont(font);
        bbook.setFont(font);
        txa.setFont(font);
        cbox.setFont(font);
        
        blist.setBackground(new Color(198,229,166));
        bbook.setBackground(new Color(198,229,166));
        txa.setBackground(new Color(46,51,64));
        cbox.setBackground(new Color(176,45,70));
        getContentPane().setBackground(new Color(60,63,65));
        
        blist.setForeground(new Color(46,51,64));
        bbook.setForeground(new Color(46,51,64));
        txa.setForeground(new Color(20,138,191));
        cbox.setForeground(new Color(46,51,64));
        
        lay = new GridBagLayout();
	c = new GridBagConstraints();
	setLayout(lay);
        
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        add(blist, c);
        
	c.ipadx = 220;
        c.gridy = 1;
        c.weighty = 0;
        add(cbox, c);
        
        c.ipadx = 300;
        c.ipady = 300;
        c.gridy = 2;
        c.weighty = 1;
        add(txa, c);
       
        c.ipady = 0;
        c.ipadx = 0;
        c.gridy = 3;
        c.weighty = 1;
        add(bbook, c);
        
        show();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    public static void main(String []args) {
        new myclient();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Button x =(Button)ae.getSource();
        switch(x.getName()) {
            case "name_blist": 
                List<String> bookList = serv.getMyservicePort().getBookList();
                Iterator<String> iter = bookList.listIterator();
                while (iter.hasNext()) {
                    cbox.addItem(iter.next());
                }
                break;
            case "name_bbook": 
                if (cbox.getSelectedIndex() != -1)
                    txa.setText(serv.getMyservicePort().getBook(cbox.getSelectedItem().toString()));
                break;
            default: return;
        }
    }
}

