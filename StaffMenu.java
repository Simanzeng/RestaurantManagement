package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class StaffMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象
	
	private String strValue="";
	
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	
	
	
	void showmenuTable() //显示菜谱表中的用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM menu";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = {rs.getString("name"),rs.getString("num"),rs.getString("sell") };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	public StaffMenu() {
		super();
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("菜谱管理");
		//setBounds(100, 100, 472, 375);
		setBounds(100, 100, 450, 300);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "菜名","库存","售价"};// 定义表格列名数组
		String[][] tableValues = {};// 定义表格数据数组
		// 创建指定表格列名和表格数据的表格模型
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// 创建指定表格模型的表格
		table.setRowSorter(new TableRowSorter<>(tableModel));// 设置表格的排序器
		// 设置表格的选择模式为单选
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton reservationButton = new JButton("\u70B9\u83DC");  //点菜
		reservationButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至点菜界面
				ReservationTable reservationFrame=new ReservationTable();
				reservationFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		
		panel.add(reservationButton);
		
		JLabel lblNewLabel = new JLabel("           ");
		panel.add(lblNewLabel);
		final JButton tableButton = new JButton("\u9910\u684C");  //查看餐桌
		tableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至餐桌界面
				StaffTable TableFrame=new StaffTable();
				TableFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		
		panel.add(tableButton);
		
		showmenuTable();
		
	}
}