package database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

public class ShowAllFood extends JPanel implements ActionListener{
	// JTable
	Object ob[][]=new Object[0][7];
	String colname[] = { "음식", "열량(kcal)", "탄수화물(g)", "당(g)", "단백질(g)", "지방(g)","선택"};
	DefaultTableModel model;
	JTable table;
	JScrollPane scroll;
	JButton update, delete, insert;
	
	//DB
	private Connection con;	
	private Statement st; 	// sql문장 실행
	private ResultSet rs; 	// 실행 결과를 받아오는 객체
	
	public String selectedName;
	public int selectedRow;

	public ShowAllFood() {
		
		setLayout(new BorderLayout());
		
		model=new DefaultTableModel(ob,colname);
		table=new JTable(model);
		scroll=new JScrollPane(table);

		//add buttons into cell
		table.getColumnModel().getColumn(6).setCellRenderer(new TableCell());
		table.getColumnModel().getColumn(6).setCellEditor(new TableCell());

		
		add(scroll,"Center");
		
		//add buttons
		update=new JButton("수정");
		delete=new JButton("삭제");
		insert=new JButton("추가");
		
		update.addActionListener(this);
		delete.addActionListener(this);
		insert.addActionListener(this);
		
		JPanel buttons=new JPanel();
		buttons.add(update);
		buttons.add(delete);
		buttons.add(insert);
		add(buttons,"South");

		
		
		//connect DB
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diet-manage", "root", "1915247");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 오류: " + e.getMessage());
		}
		
		//bring data from Member Table
		try {

			String SQL = "SELECT * FROM nutrition_info";
			rs = st.executeQuery(SQL);

			while(rs.next()) {
				String name=rs.getString("foodname");
				String kcal=rs.getString("kcal");
				String carb=rs.getString("carb");
				String sugar=rs.getString("sugar");
				String protein=rs.getString("protein");
				String fat=rs.getString("fat");
				
				Object data[]= {name,kcal,carb,sugar,protein,fat};
				model.addRow(data);

			}

		} catch (Exception e) {
			System.out.println("데이터베이스 검색 오류: " + e.getMessage());
		}
	}
	
	//checkbox cell 처리
	class TableCell extends AbstractCellEditor implements TableCellEditor,TableCellRenderer{
		
		JCheckBox select;
		
		public TableCell() {
			select=new JCheckBox();
			select.addActionListener(e->{
				selectedName=(String)table.getValueAt(table.getSelectedRow(), 0);
				selectedRow=table.getSelectedRow();
				
			});
		}

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			return select;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// TODO Auto-generated method stub
			return select;
		}
		
		
	}
	
	//edit, delete button event handle
	@Override
	public void actionPerformed(ActionEvent e) {
		// update data
		if(update.equals(e.getSource())) {
			new UpdateFood(selectedRow);
		}
		// delete data
		else if(delete.equals(e.getSource())){
			deleteData(selectedRow, selectedName);
		}
		else if(insert.equals(e.getSource())) {
			add("South",new InsertFood());
		}
		
	}

	public void deleteData(int row, String name) {
		try {
			
			if(row==-1)return;
			
			//JTable에서 삭제
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.removeRow(row);
			
			//DB에서 삭제
			String SQL="DELETE FROM nutrition_info WHERE foodname='"+name+"'";
			int updateCount=st.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null, "삭제 완료");
		}
		catch(Exception deleteerror) {
			String msg="삭제 오류: "+deleteerror.getMessage();
			JOptionPane.showMessageDialog(null, msg);
		}
	}
}
