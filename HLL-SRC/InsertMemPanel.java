package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class InsertMemPanel extends JPanel implements ActionListener {
	JPanel enterP, buttonP;
	TextField nameT, ageT, phoneT, typeT, managerT;
	Checkbox f,m;
	JButton insert, clear;

	public InsertMemPanel() {

		setLayout(new GridLayout(0, 1));

		JLabel name = new JLabel("고객명");
		JLabel sex = new JLabel("성별");
		JLabel age = new JLabel("나이");
		JLabel phone = new JLabel("연락처");
		JLabel type = new JLabel("관리타입");
		JLabel manager = new JLabel("담당자");

		// name panel
		nameT = new TextField(15);
		JPanel p1 = new JPanel();
		p1.add(name);
		p1.add(nameT);

		// sex panel
		CheckboxGroup cbg = new CheckboxGroup();
		f = new Checkbox("Female", cbg, true);
		m = new Checkbox("male", cbg, false);
		JPanel p2 = new JPanel();
		p2.add(sex);
		p2.add(f);
		p2.add(m);

		// age panel
		ageT = new TextField(5);
		JPanel p3 = new JPanel();
		p3.add(age);
		p3.add(ageT);

		// phone panel
		phoneT = new TextField(15);
		JPanel p4 = new JPanel();
		p4.add(phone);
		p4.add(phoneT);

		// type panel
		typeT = new TextField(15);
		JPanel p5 = new JPanel();
		p5.add(type);
		p5.add(typeT);

		// manager panel
		managerT = new TextField(15);
		JPanel p6 = new JPanel();
		p6.add(manager);
		p6.add(managerT);

		// button panel
		insert = new JButton("추가하기");
		clear = new JButton("초기화");
		insert.addActionListener(this);
		clear.addActionListener(this);

		buttonP = new JPanel();
		buttonP.add(insert);
		buttonP.add(clear);

		enterP = new JPanel();
		enterP.setLayout(new GridLayout(6, 1));
		enterP.add(p1);
		enterP.add(p2);
		enterP.add(p3);
		enterP.add(p4);
		enterP.add(p5);
		enterP.add(p6);

		// add panels
		add(enterP);
		add(buttonP);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//insert button event
		if (insert.equals(e.getSource())) {
			
			String name=nameT.getText();
			String sex="";
			if(f.getState()==true) {
				sex="female";
			}
			else if(m.getState()==true) {
				sex="male";
			}
			int age=Integer.parseInt(ageT.getText());
			String phone=phoneT.getText();
			String type=typeT.getText();
			String manager=managerT.getText();
			
			
			//DB에 추가
			DBConnection connection=new DBConnection();
			connection.insertMember(name, sex, age, phone, type, manager);
			

		} 
		//clear button event
		else if (clear.equals(e.getSource())) {
			nameT.setText("");
			ageT.setText("");
			phoneT.setText("");
			typeT.setText("");
			managerT.setText("");
			f.setState(true);
			m.setState(false);
			
		}

	}
}
