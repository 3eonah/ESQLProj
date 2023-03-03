package database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search extends JPanel implements ActionListener {

	JPanel enterP, searchP;
	TextField nameT, phoneT;
	JButton searchbtn;

	Object ob[][] = new Object[0][6];
	String colname[] = { "고객명", "성별", "나이", "연락처", "관리타입", "담당자" };
	DefaultTableModel model;
	JTable table;
	JScrollPane scroll;

	// DB
	Connection con;
	Statement st; // sql문장 실행
	ResultSet rs; // 실행 결과를 받아오는 객체

	public Search() {
		setLayout(new BorderLayout());
		// create table
		model = new DefaultTableModel(ob, colname);
		table = new JTable(model);
		scroll = new JScrollPane(table);

		JLabel name = new JLabel("고객명");
		JLabel phone = new JLabel("연락처");

		// name panel
		nameT = new TextField(15);
		JPanel p1 = new JPanel();
		p1.add(name);
		p1.add(nameT);

		// phone panel
		phoneT = new TextField(15);
		JPanel p2 = new JPanel();
		p2.add(phone);
		p2.add(phoneT);

		enterP = new JPanel();
		enterP.setLayout(new BorderLayout());
		enterP.add(p1, "North");
		enterP.add(p2, "South");

		// button panel
		searchbtn = new JButton("검색하기");
		searchbtn.addActionListener(this);
		searchP = new JPanel();
		searchP.setLayout(new BorderLayout());
		searchP.add("North", enterP);
		searchP.add("South", searchbtn);

		// add panels
		add("North", searchP);
		add("Center", scroll);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String n = nameT.getText();
		String p = phoneT.getText();

		// connect DB
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diet-manage", "root", "1915247");
			st = con.createStatement();
		} catch (Exception connecte) {
			System.out.println("데이터베이스 연결 오류: " + connecte.getMessage());
		}

		// bring data from Member Table
		try {

			String SQL = "SELECT * FROM MEMBER WHERE Mname='"+n+"'AND phone='"+p+"'";
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				String name = rs.getString("Mname");
				String sex = rs.getString("sex");
				String age = rs.getString("age");
				String phone = rs.getString("phone");
				String type = rs.getString("type");
				String manager = rs.getString("manager");

				Object data[] = { name, sex, age, phone, type, manager };
				model.addRow(data);

			}

		} catch (Exception searche) {
			System.out.println("데이터베이스 검색 오류: " + searche.getMessage());
		}
	}
}
