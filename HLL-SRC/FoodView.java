package database;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class FoodView extends JPanel implements ActionListener {
	JPanel subpanel;
	JButton allfood, memberdiet;
	JLabel subtitle;
	//sub panels
	ShowAllFood all;
	MemberDietManage s;
	
	public FoodView() {
		
		setLayout(new BorderLayout());
		
		JPanel title=new JPanel();
		//set title Panel Layout
		GridBagLayout titlelayout=new GridBagLayout();
		title.setLayout(titlelayout);
		
		//add components into title Panel
		JLabel titlelabel=new JLabel("식단 관리");
		Font titlefont=new Font("Geneva",Font.BOLD, 16);
		titlelabel.setFont(titlefont);
		titlelabel.setHorizontalAlignment(JLabel.CENTER);
		
		GridBagConstraints container1=new GridBagConstraints();
		container1.gridx=0;
		container1.gridy=0;
		container1.fill=GridBagConstraints.HORIZONTAL;
		title.add(titlelabel,container1);
		
		//create menu panel and add buttons with event
		JPanel menu=new JPanel();
		allfood=new JButton("음식 영양 정보 전체보기");
		allfood.addActionListener(this);
		memberdiet=new JButton("회원 식단 관리하기");
		memberdiet.addActionListener(this);

		
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(allfood);
		menu.add(memberdiet);
		
		GridBagConstraints container2=new GridBagConstraints();
		container2.gridx=0;
		container2.gridy=1;
		container2.fill=GridBagConstraints.HORIZONTAL;
		title.add(menu,container2);
		
		//create list view
		subpanel=new JPanel();
		subpanel.setLayout(new BorderLayout());
		
		//show title of user choice
		subtitle=new JLabel();
		subtitle.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(subtitle,BorderLayout.NORTH);
		
		//add components into parent panel
		add(title,BorderLayout.NORTH);
		add(subpanel,BorderLayout.CENTER);
		
		all=new ShowAllFood();
		s=new MemberDietManage();
		
		subpanel.add(all,"Center");

	}


	
	public void actionPerformed(ActionEvent e) {
		
		//viewall을 선택했을 때
		if(allfood.equals(e.getSource())) {
			subpanel.removeAll();
			remove(all);
			subpanel.revalidate();
			
			subtitle=new JLabel("음식 영양 정보 전체보기");
			subpanel.add(subtitle);
			//update된 table을 볼 수 있도록 객체를 재생성
			all=new ShowAllFood();
			subpanel.add(all);
			
			subpanel.repaint();
			
		}
		else if(memberdiet.equals(e.getSource())) {
			subpanel.removeAll();
			subpanel.revalidate();
			
			subtitle=new JLabel("회원별 식단 관리");
			subpanel.add(subtitle);
			subpanel.add(s);
			
			subpanel.repaint();
		}
		
	}
}

