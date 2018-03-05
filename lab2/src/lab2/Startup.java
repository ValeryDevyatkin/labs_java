package lab2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Startup extends JFrame implements ActionListener{
	Server srv;
	Client cl1, cl2;
	Label lcl1, lsrv, lcl2;
	Button bcl1, bsrv, bcl2, scl1, scl2;
	TextArea tcl1, tcl2;
	
	public Startup() {
		super("lab2");
		setSize(450,450);
		setResizable(false);
		setLocationRelativeTo(null);
		
		lcl1 = new Label("Client_1");
		lsrv = new Label("Server");
		lcl2 = new Label("Client_2");
			lcl1.setAlignment(1);
			lsrv.setAlignment(1);
			lcl2.setAlignment(1);
			lcl1.setBackground(Color.RED);
			lsrv.setBackground(Color.RED);
			lcl2.setBackground(Color.RED);
		bcl1 = new Button("Connect");
		bsrv = new Button("Start");
		bcl2 = new Button("Connect");
		tcl1 = new TextArea(1,1);
		tcl2 = new TextArea(1,1);
			tcl1.setText(null);
			tcl2.setText(null);
		scl1 = new Button("Send");
		scl2 = new Button("Send");
				scl1.setEnabled(false);
				scl2.setEnabled(false);
			bsrv.addActionListener(this);
			bcl1.addActionListener(this);
			bcl2.addActionListener(this);
			scl1.addActionListener(this);
			scl2.addActionListener(this);
		bsrv.setName("bsrv");
		bcl1.setName("bcl1");
		bcl2.setName("bcl2");
		scl1.setName("scl1");
		scl2.setName("scl2");
		
		Font f=new Font("arial", Font.PLAIN, 20);
		lcl1.setFont(f);
		lsrv.setFont(f);
		lcl2.setFont(f);
		tcl1.setFont(f);
		tcl2.setFont(f);
		bcl1.setFont(f);
		bsrv.setFont(f);
		bcl2.setFont(f);
		scl1.setFont(f);
		scl2.setFont(f);
		
		GridBagLayout lay = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(lay);
		
		c.ipady=0;
		c.ipadx=100;
		
		c.gridx=0;
		c.gridy=5;
		add(scl1,c);

		c.gridx=1;
		c.gridy=5;
		add(scl2,c);
		
		c.gridx=0;
		c.gridy=3;
		add(bcl1,c);
		
		c.gridx=1;
		c.gridy=3;
		add(bcl2,c);
		
		c.gridx=0;
		c.gridy=2;
		add(lcl1,c);
		
		c.gridx=1;
		c.gridy=2;
		add(lcl2,c);
		
		c.ipady=200;
		c.ipadx=190;
		c.gridx=0;
		c.gridy=4;
		add(tcl1,c);
		
		c.gridx=1;
		c.gridy=4;
		add(tcl2,c);
		
		c.ipady=0;
		c.ipadx=190;
		c.gridwidth=2;
		c.gridx=0;
		c.gridy=0;
		add(lsrv,c);
		
		c.gridx=0;
		c.gridy=1;
		add(bsrv,c);
		
		
		show();
		
		srv = new Server();
		cl1 = new Client("1");
		cl2 = new Client("2");
	}

	public static void main(String[] args) {
		new Startup();
	}
	
	public void windowClosed(WindowEvent e) {
		srv.closeSocket();
	}
	
	public void actionPerformed(ActionEvent ae) {
		Button x = (Button)ae.getSource();
		switch(x.getName()) {
		//кнопка запуска сервера -------------------------------------------------------------
			case "bsrv":
				if (x.getLabel() == "Start") { 
					try {
						srv.start();
					} catch(Exception e ) {
						srv.resume();
					}
					x.setLabel("Stop");
					lsrv.setBackground(Color.GREEN);
					//
					if (bcl2.getLabel() != "Connect") {
						lcl2.setBackground(Color.GREEN);
						scl2.setEnabled(true);
					}
					if (bcl1.getLabel() != "Connect") {
						lcl1.setBackground(Color.GREEN);
						scl1.setEnabled(true);
					}
				}
				else 
				if (x.getLabel() == "Stop") {
					srv.suspend();
					srv.closeSocket();
					cl1.closeSocket();
					cl2.closeSocket();
					x.setLabel("Start");
					lsrv.setBackground(Color.RED);
					if (bcl1.getLabel() == "Connect") {
						lcl1.setBackground(Color.RED);
					}
					else 
						lcl1.setBackground(Color.ORANGE);
					if (bcl2.getLabel() == "Connect") 
						lcl2.setBackground(Color.RED);
					else 
						lcl2.setBackground(Color.ORANGE);
					scl1.setEnabled(false);
					scl2.setEnabled(false);
				}
			break;	
		//кнопка подключения первого клиента -------------------------------------------------
			case "bcl1":
				if (x.getLabel() == "Connect") { 
					try {
						cl1.start();
					} catch(Exception e ) {
						cl1.resume();
					}
					x.setLabel("Disconnect");
					if (bsrv.getLabel() == "Start") 
						lcl1.setBackground(Color.ORANGE);
					else {
						lcl1.setBackground(Color.GREEN);
						scl1.setEnabled(true);
					}
					//сервер отправляет клиенту 1 сообщение 
					if (bsrv.getLabel() != "Start") {
						srv.send(1);
						String msg = cl1.recieve();
						if (msg != "")
							tcl1.setText(msg + tcl1.getText());
					}
				}
				else 
				if (x.getLabel() == "Disconnect") {
					cl1.suspend();
					cl1.closeSocket();
					x.setLabel("Connect");
					lcl1.setBackground(Color.RED);
					scl1.setEnabled(false);
				}
			break;
		//кнопка подключения второго клиента -------------------------------------------------
			case "bcl2":
				if (x.getLabel() == "Connect") { 
					try {
						cl2.start();
					} catch(Exception e ) {
						cl2.resume();
					}
					x.setLabel("Disconnect");
					if (bsrv.getLabel() == "Start") 
						lcl2.setBackground(Color.ORANGE);
					else {
						lcl2.setBackground(Color.GREEN);
						scl2.setEnabled(true);
					}
					//сервер отправляет клиенту 2 сообщение
					if (bsrv.getLabel() != "Start") {
						srv.send(2);
						String msg = cl2.recieve();
						if (msg != "")
							tcl2.setText(msg + tcl2.getText());
					}
				}
				else 
				if (x.getLabel() == "Disconnect") {
					cl2.suspend();
					cl2.closeSocket();
					x.setLabel("Connect");
					lcl2.setBackground(Color.RED);
					scl2.setEnabled(false);
				}
			break;	
		//\-----------------------------------------------------------------------------------
			case "scl1": 
				if (x.getLabel() == "Send") {
					if (!tcl1.getText().trim().isEmpty()) {
						cl1.send("");
						if (bcl2.getLabel() != "Connect") 
							cl2.closeSocket();
						srv.recieve();
						x.setLabel("?");
						cl1.closeSocket();
					}
				} 
				else
					if (!tcl1.getText().trim().isEmpty()) {
						cl1.send("1: " + tcl1.getText().trim());
						if (bcl2.getLabel() != "Connect") 
							cl2.closeSocket();
						srv.recieve();
						tcl1.setText(null);
						x.setLabel("Send");	
						//
						if (bcl2.getLabel() != "Connect") {
							srv.send(2);
							tcl2.setText(cl2.recieve() + tcl2.getText());
						}
						else 
							cl1.closeSocket();
					}	
					
			break;
		//\-----------------------------------------------------------------------------------	
			case "scl2": 
				if (x.getLabel() == "Send") {
					if (!tcl2.getText().trim().isEmpty()) {
						cl2.send("");
						if (bcl1.getLabel() != "Connect") 
							cl1.closeSocket();
						srv.recieve();
						x.setLabel("?");
						cl2.closeSocket();
					}
				}
				else
					if (!tcl2.getText().trim().isEmpty()) {
						cl2.send("2: " + tcl2.getText().trim());
						if (bcl1.getLabel() != "Connect") 
							cl1.closeSocket();
						srv.recieve();
						tcl2.setText(null);
						x.setLabel("Send");
						//
						if (bcl1.getLabel() != "Connect") {
							srv.send(1);
							tcl1.setText(cl1.recieve() + tcl1.getText());
						}
						else
							cl2.closeSocket();
					}
			break;
			default: return;
		}
	}
}
