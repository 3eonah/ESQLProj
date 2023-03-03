package database;
import java.awt.*;
import javax.swing.*;

public class TabDisplay {
	JFrame frame;
	JTabbedPane tabpane;
	
	public TabDisplay() {
		//create and set frame
		frame=new JFrame();
		frame.setTitle("Diet Management");
		frame.setSize(700,700);
		
		//set home title
		JLabel title=new JLabel("Eat Healthy, Live Lively");
		Font titlefont=new Font("Geneva",Font.BOLD, 30);	//create font	
		title.setFont(titlefont);	//set font of title label
		title.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add("North",title);	//add label component into frame
		
		//create tabs
		tabpane=new JTabbedPane();
		tabpane.addTab("회원관리", new MemberView());
		tabpane.addTab("식단관리", new FoodView());
//		tabpane.addTab("직원관리", emp_view);
//		
		frame.getContentPane().add("Center",tabpane);
		frame.setVisible(true);
	}
}
