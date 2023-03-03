package database;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class UpdateFood extends JPanel implements ActionListener {
	int editrow;
	int editcol[] = new int[6];

	JFrame frame;
	JPanel titleP, editP, buttonP, p1, p2, p3, p4, p5, p6;
	JPanel[] editpanels = new JPanel[5];
	JButton editBtn, clearBtn;
	TextField nameT, kcalT, carbT, sugarT, proteinT, fatT;
	String[] colhead = { "음식", "열량", "탄수화물", "당", "단백질", "지방" };
	String selectedfood;

	public UpdateFood(int row) {

		editrow = row; // editrow=selectedRow

		frame = new JFrame("Update elements");
		frame.setLayout(new GridLayout(0, 1));

		// bring table
		ShowAllFood tablepage = new ShowAllFood();
		JTable table2 = tablepage.table;
		selectedfood = (String) table2.getValueAt(editrow, 0);

		// title panel
		titleP = new JPanel();
		titleP.setLayout(new GridLayout(2,1));
		JLabel name = new JLabel("'"+selectedfood + "' 의 영양 정보 수정");
		name.setFont(new Font("Geneva",Font.BOLD,16));
		name.setHorizontalAlignment(JLabel.CENTER);
		titleP.add(name);
		
		JLabel text = new JLabel("수정 사항을 입력하세요.");
		text.setHorizontalAlignment(JLabel.CENTER);
		titleP.add(text);
		
		// edit panel
		editP = new JPanel();
		editP.setLayout(new GridLayout(6, 1));


		JLabel kcal = new JLabel("열량(kcal)");
		JLabel carb = new JLabel("탄수화물(g)");
		JLabel sugar = new JLabel("당(g)");
		JLabel protein = new JLabel("단백질(g)");
		JLabel fat = new JLabel("지방(g)");

		// kcal panel
		kcalT = new TextField(5);
		p2 = new JPanel();
		p2.add(kcal);
		p2.add(kcalT);
		editpanels[0] = p2;

		// carb panel
		carbT = new TextField(5);
		p3 = new JPanel();
		p3.add(carb);
		p3.add(carbT);
		editpanels[1] = p3;

		// sugar panel
		sugarT = new TextField(15);
		p4 = new JPanel();
		p4.add(sugar);
		p4.add(sugarT);
		editpanels[2] = p4;

		// protein panel
		proteinT = new TextField(15);
		p5 = new JPanel();
		p5.add(protein);
		p5.add(proteinT);
		editpanels[3] = p5;

		// manager panel
		fatT = new TextField(15);
		p6 = new JPanel();
		p6.add(fat);
		p6.add(fatT);
		editpanels[4] = p6;

		for (int i = 0; i < editpanels.length; i++) {
			editP.add(editpanels[i]);
		}

		// button panel
		buttonP = new JPanel();
		editBtn = new JButton("수정하기");
		clearBtn = new JButton("초기화");
		editBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		buttonP.add(editBtn);
		buttonP.add(clearBtn);

		// add panels into frame
		frame.add(titleP);
		frame.add(editP);
		frame.add(buttonP);

		frame.setSize(400, 600);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// update button event
		if (editBtn.equals(e.getSource())) {
			// 입력받은 값 가져오기
			String kcal = kcalT.getText();
			String carb = carbT.getText();
			String sugar = sugarT.getText();
			String protein = proteinT.getText();
			String fat = fatT.getText();

			String data[] = { kcal, carb, sugar, protein, fat };

			// DB 수정

			DBConnection connection = new DBConnection();

			for (int i = 0; i < data.length; i++) {
				connection.updateFood(selectedfood, data[i], i);

			}
			new SuccessPopUp();

			frame.setVisible(false);

		}
		// clear button event
		else if (clearBtn.equals(e.getSource())) {
			nameT.setText("");
			kcalT.setText("");
			carbT.setText("");
			sugarT.setText("");
			proteinT.setText("");
			fatT.setText("");

		}

	}
}
