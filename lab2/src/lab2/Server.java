package lab2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread implements Runnable{	
	ServerSocket server = null;
	Socket s = null;
	Vector<String> buf1 = new Vector<String>();
	Vector<String> buf2 = new Vector<String>();
	InputStream sin;
	OutputStream sout;
	DataInputStream in;
	DataOutputStream out;
	
	public void run() {
		while (true) {
			if (server == null) 
				try {
					server = new ServerSocket(4444); // ����� ������
					System.out.println("������:\n����� ���������������\n");
				} catch (Exception e) {
					System.out.println("������:\n������ ��� �������������\n" + e + "\n");
				}
			else 
				try {
					s = server.accept(); // �������� ���������� � ��������
					System.out.println("������:\n������-������ ���������� �����������\n");
				} catch (Exception e) {
					System.out.println("������:\n������ ��� ����������\n" + e + "\n");
				}
		}+
	}
	
	public void closeSocket() {
		try {
			server.close();
			server = null;
			System.out.println("������:\n���� ������\n");
		} catch (Exception e) {
			System.out.println("������:\n������ ��� ��������\n" + e + "\n");
		}
	}
	
	public void send(int key) {
		try {
			sout = s.getOutputStream();
			out = new DataOutputStream(sout);
			String msg = "";
			if (key == 1)
				while (buf2.size() > 0) {
					msg += (buf2.remove(0) + "\n");
				}
			else
			if (key == 2)
				while (buf1.size()> 0 ) {
					msg += (buf1.remove(0) + "\n");
				}
			out.writeUTF(msg);
			out.flush();
		} catch(Exception e) {}
	}
	
	public void recieve() {
		try {
			sin = s.getInputStream();
			in = new DataInputStream(sin);
			String msg = in.readUTF();
			char key = msg.charAt(0);
			if (key == '1') {
				buf1.add(msg);
				for (int i=0; i<buf1.size(); i++) 
					System.out.println(buf1.elementAt(i));
			}
			else
			if (key == '2') {
				buf2.add(msg);
				for (int i=0; i<buf2.size(); i++) 
					System.out.println(buf2.elementAt(i));
			}
		} catch(Exception e) {
			System.out.println("������:\n����������\n" + e + "\n");
		}
	}
}
