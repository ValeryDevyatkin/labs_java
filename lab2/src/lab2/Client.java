package lab2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread implements Runnable{
	String name;
	Socket s = null;
	InputStream sin;
	OutputStream sout;
	DataInputStream in;
	DataOutputStream out;
	
	private Client() {}	
	public Client(String Name) {
		name=Name;
	}
	
	public void run() {
		while (true) {
			if (s == null || s.isClosed())
				try {
					s = new Socket("127.0.0.1", 4444);
					System.out.println("Клиент " + name + ":\nсокет инициализирован\n");
				} catch (Exception e) {
					System.out.println("Клиент " + name + ":\nОшибка при инициализации\n" 
							+ e + "\n");
				}
		}
	}
	
	public void closeSocket() {
		try {
			s.close();
			s = null;
			System.out.println("Клиент " + name + ":\nСокет закрыт\n");
		} catch(Exception e) {
			System.out.println("Клиент " + name + ":\nОшибка при закрытии\n" + e + "\n");
		}
	}
	
	public void send(String msg) {
		try {
			sout = s.getOutputStream();
			out = new DataOutputStream(sout);
			out.writeUTF(msg);
			out.flush();
		} catch(Exception e) {}
	}
	
	public String recieve() {
		try {
			sin = s.getInputStream();
			in = new DataInputStream(sin);
			return in.readUTF();
		} catch(Exception e) {
			return "";
		}
	}
}
