package database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class InsertFood extends JPanel implements ActionListener {
	JPanel enterP, buttonP;
	TextField nameT, kcalT, carbT, sugarT, proteinT, fatT;
	JButton insert, clear;

	public InsertFood() {

		setLayout(new GridLayout(0, 1));

		JLabel name = new JLabel("음식");
		JLabel kcal = new JLabel("열량");
		JLabel carb = new JLabel("탄수화물");
		JLabel sugar = new JLabel("당");
		JLabel protein = new JLabel("단백질");
		JLabel fat = new JLabel("지방");

		// name panel
		nameT = new TextField(15);
		JPanel p1 = new JPanel();
		p1.add(name);
		p1.add(nameT);

		// kcal panel
		kcalT = new TextField(5);
		JPanel p2 = new JPanel();
		p2.add(kcal);
		p2.add(kcalT);

		// carb panel
		carbT = new TextField(15);
		JPanel p3 = new JPanel();
		p3.add(carb);
		p3.add(carbT);

		// sugar panel
		sugarT = new TextField(15);
		JPanel p4 = new JPanel();
		p4.add(sugar);
		p4.add(sugarT);

		// protein panel
		proteinT = new TextField(15);
		JPanel p5 = new JPanel();
		p5.add(protein);
		p5.add(proteinT);

		// fat panel
		fatT = new TextField(15);
		JPanel p6 = new JPanel();
		p6.add(fat);
		p6.add(fatT);

		enterP = new JPanel();
		enterP.setLayout(new GridLayout(6, 1));
		enterP.add(p1);
		enterP.add(p2);
		enterP.add(p3);
		enterP.add(p4);
		enterP.add(p5);
		enterP.add(p6);

		insert = new JButton("추가하기");
		clear = new JButton("초기화");
		insert.addActionListener(this);
		clear.addActionListener(this);

		buttonP = new JPanel();
		buttonP.add(insert);
		buttonP.add(clear);

		// add panels
		add(enterP);
		add(buttonP);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// insert button event
		if (insert.equals(e.getSource())) {

			// 입력받은 값 가져오기
			String name=nameT.getText();
			int kcal = Integer.parseInt(kcalT.getText());
			double carb = Double.parseDouble(carbT.getText());
			double sugar = Double.parseDouble(sugarT.getText());
			double protein = Double.parseDouble(proteinT.getText());
			double fat = Double.parseDouble(fatT.getText());


			// DB에 추가
			DBConnection connection = new DBConnection();
			connection.insertFood(name, kcal, carb, sugar, protein, fat);

		}
		// clear button event
		else if (clear.equals(e.getSource())) {
			nameT.setText("");
			kcalT.setText("");
			carbT.setText("");
			sugarT.setText("");
			proteinT.setText("");
			fatT.setText("");
	

		}

	}
}
