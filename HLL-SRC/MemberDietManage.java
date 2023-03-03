package database;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MemberDietManage extends JPanel implements ActionListener {
	JPanel enterP, searchP;
	TextField depttext;
	TextField emptext;
	JButton searchbtn;

	//Jtable
	Object ob[][] = new Object[0][11];
	String colname[] = { "부서번호","담당자","고객명", "시간","음식","수량", "열량(kcal)", "탄수화물(g)", "당(g)","단백질(g)", "지방(g)" };
	DefaultTableModel model;
	JTable table;
	JScrollPane scroll;

	// DB
	Connection con;
	Statement st; // sql문장 실행
	ResultSet rs; // 실행 결과를 받아오는 객체

	public MemberDietManage() {
		
		setLayout(new BorderLayout());
		// create table
		model = new DefaultTableModel(ob, colname);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		// enter panel
		JLabel dept = new JLabel("부서번호");
		JLabel manager = new JLabel("관리자명");

		// name panel
		depttext = new TextField(15);
		JPanel p1 = new JPanel();
		p1.add(dept);
		p1.add(depttext);

		// phone panel
		emptext = new TextField(15);
		JPanel p2 = new JPanel();
		p2.add(manager);
		p2.add(emptext);

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
		String dept = depttext.getText();
		String emp = emptext.getText();

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

			String SQL 
			= "SELECT d.Dnum, m.manager, md.*"+
					" FROM member_diet AS md"+ 
					" JOIN member AS m"+
					" ON md.Mname=m.Mname"+
					" JOIN dept as d"+
					" ON d.Dname=m.type"+
					" WHERE m.manager='"+emp+"'";
			rs = st.executeQuery(SQL);

			while (rs.next()) {
				int dnum = Integer.parseInt(rs.getString("d.Dnum"));
				String adname = rs.getString("m.manager");
				String mname = rs.getString("md.Mname");
				String time=rs.getString("md.time");
				String fname = rs.getString("md.foodname");
				int qty = Integer.parseInt(rs.getString("md.qty"));
				double kcal = Double.parseDouble(rs.getString("md.kcal"));
				double carb = Double.parseDouble(rs.getString("md.carb"));
				double sugar = Double.parseDouble(rs.getString("md.sugar"));
				double protein = Double.parseDouble(rs.getString("md.protein"));
				double fat = Double.parseDouble(rs.getString("md.fat"));

				Object data[] = { dnum,adname,mname,time, fname, qty, kcal, carb, sugar,protein,fat };
				model.addRow(data);

			}

		} catch (Exception searche) {
			System.out.println("데이터베이스 검색 오류: " + searche.getMessage());
		}
	}

}
