package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class Menu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象
	private JTextField idText;
	private JTextField nameText;
	private JTextField numText;
	private JTextField costText;
	private JTextField sellText;
	
	private String strValue="";
	
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	
	
	boolean selectmenuTable() //查询菜谱表中是否有此用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT name FROM menu where id="+Integer.parseInt(idText.toString());
			ResultSet rs=stat.executeQuery(sql);
			System.out.println(sql);
			
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
	
	void showmenuTable() //显示菜谱表中的用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM menu";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				String[] rowValues = { rs.getString("id"),
						rs.getString("name"),rs.getString("num"), rs.getString("cost"), rs.getString("sell") };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void updatemenuTable(ActionEvent e) //修改菜单
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="UPDATE menu SET id=";
		    sql=sql + Integer.parseInt(idText.getText()) + ",name='";
		    sql=sql + nameText.getText() + "',num=";
		    sql=sql + Integer.parseInt(numText.getText()) + ",cost=";
		    sql=sql + Integer.parseInt(costText.getText()) + ",sell=";
		    sql=sql + Integer.parseInt(sellText.getText()) + " WHERE id=";
		    sql=sql + Integer.parseInt(strValue);
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void addmenuTable(ActionEvent e)   //向菜单表表中添加的某一数据
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="insert into menu values(";
		    sql=sql + Integer.parseInt(idText.getText()) + ",'" + nameText.getText() + "',";
		    sql=sql + Integer.parseInt(numText.getText()) + "," + Integer.parseInt(costText.getText()) + ",";
		    sql=sql + Integer.parseInt(sellText.getText()) + ")";
		    
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	void deletemenuTable() //删除管理员表中的某一数据
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="DELETE FROM menu WHERE id=";
		    sql=sql + Integer.parseInt(idText.getText());
		    stat.executeUpdate(sql);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	public Menu() {
		super();
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("菜谱管理");
		setBounds(100, 100, 838, 376);
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "编号", "菜名","库存", "成本","售价"};// 定义表格列名数组
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
				Object oc = tableModel.getValueAt(selectedRow, 2);
				Object od = tableModel.getValueAt(selectedRow, 3);
				Object oe = tableModel.getValueAt(selectedRow, 4);
				
				idText.setText(oa.toString());// 将值赋值给文本框
				nameText.setText(ob.toString());// 将值赋值给文本框
				numText.setText(oc.toString());
				costText.setText(od.toString());
				sellText.setText(oe.toString());
				
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("编号"));
		idText = new JTextField("", 4);
		panel.add(idText);
		panel.add(new JLabel("菜名"));
		nameText = new JTextField("", 15);
		panel.add(nameText);
		panel.add(new JLabel("库存"));
		numText = new JTextField("", 4);
		panel.add(numText);
		panel.add(new JLabel("成本"));
		costText = new JTextField("", 4);
		panel.add(costText);
		panel.add(new JLabel("售价"));
		sellText = new JTextField("", 4);
		panel.add(sellText);
		final JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectmenuTable()==false)
				{
				   String[] rowValues = { idText.getText(),
						   nameText.getText(),numText.getText(), costText.getText(), sellText.getText() };// 创建表格行数组
				   tableModel.addRow(rowValues);// 向表格模型中添加一行
				   int rowCount = table.getRowCount() + 1;
				   addmenuTable(e);
				   idText.setText("");
				   nameText.setText("");
				   numText.setText("");
				   costText.setText("");
				   sellText.setText("");
				}
				else
				{
					//JOptionPane.showmessageDialog(Menu.this,"","",1);
				}
				
				
			}
		});
		panel.add(addButton);
		final JButton updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				strValue=table.getValueAt(selectedRow, 0).toString();
				if (selectedRow != -1) {// 判断是否存在被选中行
					tableModel.setValueAt(idText.getText(),
							selectedRow, 0);// 修改表格模型当中的指定值
					tableModel.setValueAt(nameText.getText(),
							selectedRow, 1);// 修改表格模型当中的指定值
					tableModel.setValueAt(numText.getText(),
							selectedRow, 2);
					tableModel.setValueAt(costText.getText(),
							selectedRow, 3);
					tableModel.setValueAt(sellText.getText(),
							selectedRow, 4);
					updatemenuTable(e);
				}
			}
		});
		panel.add(updateButton);
		final JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				strValue=table.getValueAt(selectedRow, 0).toString();
				if (selectedRow != -1)// 判断是否存在被选中行
				{
					// 从表格模型当中删除指定行
					tableModel.removeRow(selectedRow);
					deletemenuTable();
				}
			}
		});
		panel.add(deleteButton);
		
		showmenuTable();
		
	}
}