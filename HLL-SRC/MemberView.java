package database;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class MemberView extends JPanel implements ActionListener {
	JPanel subpanel;
	JButton viewall, insert, search;
	JLabel subtitle;
	//sub panels
	ShowAllMember all;
	InsertMemPanel im;
	Search s;
	
	public MemberView() {
		
		setLayout(new BorderLayout());
		
		JPanel title=new JPanel();
		//set title Panel Layout
		GridBagLayout titlelayout=new GridBagLayout();
		title.setLayout(titlelayout);
		
		//add components into title Panel
		JLabel titlelabel=new JLabel("회원 관리");
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
		viewall=new JButton("회원 목록 전체보기");
		viewall.addActionListener(this);
		insert=new JButton("회원 추가하기");
		insert.addActionListener(this);
		search=new JButton("회원 검색하기");
		search.addActionListener(this);
		
		menu.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(viewall);
		menu.add(insert);
		menu.add(search);
		
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
		
		im=new InsertMemPanel();
		all=new ShowAllMember();
		s=new Search();
		
		subpanel.add(all,"Center");
		

		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//viewall을 선택했을 때
		if(viewall.equals(e.getSource())) {
			subpanel.removeAll();
			remove(all);
			subpanel.revalidate();
			
			subtitle=new JLabel("회원 목록 전체보기");
			subpanel.add(subtitle);
			//update된 table을 볼 수 있도록 객체를 재생성
			all=new ShowAllMember();
			subpanel.add(all);
			
			subpanel.repaint();

			
		}
		//insert를 선택했을 때
		else if(insert.equals(e.getSource())) {
			subpanel.removeAll();
			subpanel.revalidate();
			
			subtitle=new JLabel("회원 추가하기");
			subpanel.add(subtitle);
			subpanel.add(im);
			
			subpanel.repaint();
			
		}
		//search를 선택했을 때
		else if(search.equals(e.getSource())) {
			subpanel.removeAll();
			subpanel.revalidate();
			
			subtitle=new JLabel("회원 찾기");
			subpanel.add(subtitle);
			subpanel.add(s);
			
			subpanel.repaint();
		}
	}
}
