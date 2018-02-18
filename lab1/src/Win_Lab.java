import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
 
public class Win_Lab extends Frame implements ActionListener {
     Button bex = new Button("Exit");
     Button sea = new Button("Search");
     TextArea txa = new TextArea();
     
     public  Win_Lab() {
		  super("my window");
		  setLayout(null);
		  setBackground(new Color(75, 100, 40));
		  setSize(800, 400);
		  add(bex);
		  add(sea);
		  add(txa);
		  bex.setBounds(300, 350, 0, 0);
		  bex.addActionListener(this);
		  bex.setSize(200, 50);
		  sea.setBounds(300, 300, 0, 0);
		  sea.addActionListener(this);
		  sea.setSize(200, 50);
		  txa.setBounds(10, 80, 0, 0);
		  txa.setSize(790, 200);
		  Font font=new Font("arial", Font.PLAIN, 20);
		  txa.setFont(font);
		  bex.setFont(font);
		  sea.setFont(font);
		  
		  this.show();
		  this.setLocationRelativeTo(null); 
		  this.setResizable(false);
     }
 
  public void actionPerformed(ActionEvent ae) {
	  if(ae.getSource() == bex)
		  System.exit(0);
	  else if (ae.getSource() == sea){
		  String[] keywords=txa.getText().split(",");
		  for (int j=0; j<keywords.length; j++)
			  System.out.println(keywords[j]);          
		  File f = new File("c:\\Users\\local-V97\\eclipse-workspace\\lab1\\src\\source_html\\");
		  ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		  txa.setText("");
		  File maxFile=null;
		  int maxMatch=0;
		  for (File elem : files){ //for each ELEM of File in FILES
			  int match = test_url(elem, keywords);
			  if (match>maxMatch) {
				  maxMatch=match;
				  maxFile=elem;
			  }
			  txa.append("\n" + elem + "  :" + match);
		  }
		  try {
			  Desktop.getDesktop().open(maxFile);
		  }
		  catch(Exception e) {}
	  }
  }
   
  public static int test_url(File elem, String[] keywords) {
      int res = 0;
      URL url = null;
      URLConnection con = null;
      int i;
      try{
          String ffele = "" + elem;
          url = new URL("file:\\" + ffele.trim());   
          con = url.openConnection();
          BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
          String bhtml = ""; //file content in byte array
                                   
          while ((i = bis.read()) != -1){
        	  bhtml += (char)i;
          }
          bis.close();
          String htmlcontent = (new String(bhtml)).toLowerCase(); //file content in string
          System.out.println("New url content is: " + htmlcontent);         
          for (int j=0; j<keywords.length; j++){
        	  if(htmlcontent.indexOf(keywords[j].trim().toLowerCase()) >= 0)    
              res++;
          }
      } 
      catch (MalformedInputException malformedInputException) {
         System.out.println("error1 " + malformedInputException.getMessage());
         return -1;
      } catch (IOException ioException) {
    	  System.out.println("error2 " + ioException.getMessage());
          return -1;
      } catch(Exception e) {
          System.out.println("error3 " + e.getMessage()); 
          return -1;
      }
      return res;
  	}
  
	public static void main(String[] args) {
		new Win_Lab();
	}
}