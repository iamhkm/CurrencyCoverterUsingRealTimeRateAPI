package currency_converter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class xyz {
	public static void main(String[] args) {
		try{
		JFrame frame = new JFrame("Currency Converter");
		frame.getContentPane().setLayout(null);
		 
	     URL obj = new URL("http://data.fixer.io/api/latest?access_key=XXXXXXXXXXXXXXXXXXXXXXXXXXX");
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     //in.close();
	     

	      System.out.println(response.toString());
	     
	     JSONObject myres = new JSONObject(response.toString());
	     System.out.println(myres); 
	     
	     JSONObject rate_obj = new JSONObject(myres.getJSONObject("rates").toString());
	     System.out.println(rate_obj);
	     
	     Font font = new Font("hkm",Font.BOLD+Font.ITALIC,12);
			
			JLabel lb = new JLabel("From");
			lb.setBounds(40,10,50,20);
			lb.setFont(font);
			frame.add(lb);
	     
	     ArrayList<String> al = new ArrayList<String>();
		
	     @SuppressWarnings("rawtypes")
		 Iterator it = rate_obj.keys();
	     while(it.hasNext()){
	    	 al.add(it.next().toString());
	     }
	     
	     System.out.println(al);
	     
	    Collections.sort(al);
		 String selection[] =  al.toArray(new String[al.size()]);
		JComboBox<String> box1 = new JComboBox<String>(selection);
		box1.setBounds(90,10,70,20);
		frame.add(box1);
		
		JLabel lb2 = new JLabel("To");
		lb2.setBounds(250,10,25,20);
		lb2.setFont(font);
		frame.add(lb2);
		
		JComboBox<String> box2 = new JComboBox<String>(selection);
		box2.setBounds(275,10,70,20);
		frame.add(box2);
		
		JLabel lb3 = new JLabel("Amount");
		lb3.setBounds(100,50,70,40);
		lb3.setFont(font);
		frame.add(lb3);
		
		JTextField input = new JTextField();
		input.setBounds(170,55,100,30);
		frame.add(input);
		
		JButton btn = new JButton("Calculate");
		btn.setBounds(120,110,150,30);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String from = box1.getSelectedItem().toString();
			     System.out.println(from);
			     
			     String to = box2.getSelectedItem().toString();
			     System.out.println(to);
			     
				if(input.getText().toString().trim().length()==0){
					double from1=0;
						try {
							from1 = rate_obj.getDouble(from);
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					     double to1=0;
						try {
							to1 = rate_obj.getDouble(to);
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						double x = to1/from1;
						JOptionPane.showMessageDialog(null,"1 "+from+"="+x+" "+to);
				}
				
				
				else{
					
					try{
				     Double.parseDouble(input.getText().toString());
				     double from1=0;
					try {
						from1 = rate_obj.getDouble(from);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				     double to1=0;
					try {
						to1 = rate_obj.getDouble(to);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(from1);
					System.out.println(to1);
					
					double x = (Double.parseDouble(input.getText().toString()))*(to1/from1);
					JOptionPane.showMessageDialog(null,Double.parseDouble(input.getText().toString())+" "+from+"="+x+" "+to);
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"Wrong Input, Should be a valid number");
					}
					
					 
				}    
			    
			        
			}
		});
		frame.add(btn);
		
		
		frame.setLocationRelativeTo(null);
		frame.setSize(400,200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		}catch (Exception e1) {
	         JOptionPane.showMessageDialog(null,"Not Connected To Internet");
	       }
}
}
