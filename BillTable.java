package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class BillTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象
	
	private String strValue="";
	private UserName userName;
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	private JLabel lblNewLabel;
	
	
	
	void showdiningTable() //显示表中的用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM bill";
			ResultSet rs=stat.executeQuery(sql);
			
			while(rs.next())
			{
				if(rs.getString("status").equals("0"))
				{
				String[] rowValues = { rs.getString("id"),"未结账" };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
				}
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	public BillTable() {
		super();
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("菜谱管理");
		//setBounds(100, 100, 432, 335);
		setBounds(100, 100, 450, 300);
		final JPanel pane2 = new JPanel();
		getContentPane().add(pane2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u9910\u684C");
		pane2.add(lblNewLabel);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "餐桌编号", "状态"};// 定义表格列名数组
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
				//Object oa = tableModel.getValueAt(selectedRow, 0);
				// 从表格模型中获得指定单元格的值
				//Object ob = tableModel.getValueAt(selectedRow, 1);
				//idText.setText(oa.toString());// 将值赋值给文本框
				//nameText.setText(ob.toString());// 将值赋值给文本框
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton okButton = new JButton("\u786E\u5B9A");  //确定
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();// 获得被选中行的索引
				userName.settableNum(table.getValueAt(selectedRow, 0).toString());
				
				//System.out.println(userName.gettableNum());
				//跳转至结账界面
				Bill billFrame=new Bill();
				billFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		
		JButton cancelButton = new JButton("\u53D6\u6D88");  //取消
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
			}
		});
		panel.add(cancelButton);
		
		JLabel lblNewLabel_1 = new JLabel("     ");
		panel.add(lblNewLabel_1);
		panel.add(okButton);
		
		showdiningTable();
		
	}
}