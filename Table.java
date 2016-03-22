package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class Table extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象
	private JTextField tableText;
	private JTextField statusText;
	
private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	
	
	
	boolean selectdiningTable() //查询菜谱表中是否有此用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT status FROM dining_table where id=" + Integer.parseInt(tableText.getText().toString());
			ResultSet rs=stat.executeQuery(sql);
			
			if(rs.next()==true)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}catch(Exception ex)
		{
			
		}
		return false;
	}
	
	void showdiningTable() //显示表中的用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM dining_table";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = { rs.getString("id"),
						(rs.getString("status").equals("1")?"占用":"空闲") };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void adddiningTable(ActionEvent e)   //向菜单表表中添加的某一数据
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="insert into dining_table values(";
		    sql=sql + Integer.parseInt(tableText.getText()) + "," + Integer.parseInt(statusText.getText()) + ")";
		    
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	void deletediningTable() //删除管理员表中的某一数据
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="DELETE FROM dining_table WHERE id=";
		    sql=sql + Integer.parseInt(tableText.getText());
		    stat.executeUpdate(sql);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	public Table() {
		super();
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("维护表格模型");
		
		
		//setBounds(100, 100, 510, 375);
		setBounds(100, 100, 450, 300);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "餐桌编号", "状态" };// 定义表格列名数组
		String[][] tableValues = {};// 定义表格数据数组
		// 创建指定表格列名和表格数据的表格模型
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// 创建指定表格模型的表格
		table.setRowSorter(new TableRowSorter<>(tableModel));// 设置表格的排序器
		// 设置表格的选择模式为单选
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 为表格添加鼠标事件监听器
		table.addMouseListener(new MouseAdapter() {
			// 发生了点击事件
			public void mouseClicked(MouseEvent e) {
				// 获得被选中行的索引
				int selectedRow = table.getSelectedRow();
				// 从表格模型中获得指定单元格的值
				Object oa = tableModel.getValueAt(selectedRow, 0);
				// 从表格模型中获得指定单元格的值
				Object ob = tableModel.getValueAt(selectedRow, 1);
				tableText.setText(oa.toString());// 将值赋值给文本框
				statusText.setText(ob.toString());// 将值赋值给文本框
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("餐桌编号"));
		tableText = new JTextField("", 6);
		panel.add(tableText);
		panel.add(new JLabel("状态"));
		statusText = new JTextField("", 2);
		panel.add(statusText);
		final JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowValues = { tableText.getText(),
						(statusText.getText().equals("1")?"占用":"空闲") };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
				adddiningTable(e);
				tableText.setText("");
				statusText.setText("");
			}
		});
		
		JLabel lblNewLabel = new JLabel("                 ");
		panel.add(lblNewLabel);
		panel.add(addButton);
		final JButton delButton = new JButton("删除");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				if (selectedRow != -1)// 判断是否存在被选中行
				{
					// 从表格模型当中删除指定行
					tableModel.removeRow(selectedRow);
					deletediningTable();
				}
			}
		});
		panel.add(delButton);
		
		showdiningTable();
	}
}