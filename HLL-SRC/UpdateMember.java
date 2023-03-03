package database;

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

public class UpdateMember extends JPanel implements ActionListener {
	int editrow;
	int editcol[] = new int[6];

	JFrame frame;
	JPanel titleP, editP, buttonP, p1, p2, p3, p4, p5, p6;
	JPanel[] editpanels = new JPanel[6];
	JButton editBtn, clearBtn;
	TextField nameT, ageT, phoneT, typeT, managerT;
	Checkbox f, m;
	String[] colhead = { "고객명", "성별", "나이", "연락처", "관리타입", "담당자" };
	String selectedphone;

	public UpdateMember(int row) {

		editrow = row; // editrow=selectedRow

		frame = new JFrame("Choose elements");
		frame.setLayout(new GridLayout(0, 1));

		// bring table
		ShowAllMember tablepage = new ShowAllMember();
		JTable table2 = tablepage.table;
		selectedphone = (String) table2.getValueAt(editrow, 3);

		// title panel
		titleP = new JPanel();
		titleP.setLayout(new GridLayout(2, 1));
		JLabel name = new JLabel("'" + table2.getValueAt(editrow, 0) + "' 의 정보 수정");
		name.setFont(new Font("Geneva", Font.BOLD, 16));
		name.setHorizontalAlignment(JLabel.CENTER);
		titleP.add(name);

		JLabel text = new JLabel("수정 사항을 입력하세요.");
		text.setHorizontalAlignment(JLabel.CENTER);
		titleP.add(text);

		// edit panel
		editP = new JPanel();
		editP.setLayout(new GridLayout(6, 1));
		JLabel mname = new JLabel("고객명");
		JLabel sex = new JLabel("성별");
		JLabel age = new JLabel("나이");
		JLabel phone = new JLabel("연락처");
		JLabel type = new JLabel("관리타입");
		JLabel manager = new JLabel("담당자");

		// name panel
		nameT = new TextField(15);
		p1 = new JPanel();
		p1.add(mname);
		p1.add(nameT);
		editpanels[0] = p1;

		// sex panel
		CheckboxGroup cbg = new CheckboxGroup();
		f = new Checkbox("Female", cbg, true);
		m = new Checkbox("male", cbg, false);
		p2 = new JPanel();
		p2.add(sex);
		p2.add(f);
		p2.add(m);
		editpanels[1] = p2;

		// age panel
		ageT = new TextField(5);
		p3 = new JPanel();
		p3.add(age);
		p3.add(ageT);
		editpanels[2] = p3;

		// phone panel
		phoneT = new TextField(15);
		p4 = new JPanel();
		p4.add(phone);
		p4.add(phoneT);
		editpanels[3] = p4;

		// type panel
		typeT = new TextField(15);
		p5 = new JPanel();
		p5.add(type);
		p5.add(typeT);
		editpanels[4] = p5;

		// manager panel
		managerT = new TextField(15);
		p6 = new JPanel();
		p6.add(manager);
		p6.add(managerT);
		editpanels[5] = p6;

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
			String name = nameT.getText();
			String sex = "";
			if (f.getState() == true) {
				sex = "female";
			} else if (m.getState() == true) {
				sex = "male";
			}
			String age = ageT.getText();
			String phone = phoneT.getText();
			String type = typeT.getText();
			String manager = managerT.getText();

			String data[] = { name, sex, age, phone, type, manager };

			// DB 수정

			DBConnection connection = new DBConnection();

			int success = 0;
			for (int i = 0; i < data.length; i++) {

				connection.updateMember(selectedphone, data[i], i);

			}
			new SuccessPopUp();

			frame.setVisible(false);

		}
		// clear button event
		else if (clearBtn.equals(e.getSource())) {
			nameT.setText("");
			ageT.setText("");
			phoneT.setText("");
			typeT.setText("");
			managerT.setText("");
			f.setState(true);
			m.setState(false);

		}

	}

	public boolean isNull(String str) {
		if (str == "")
			return true;
		else
			return false;
	}
}
