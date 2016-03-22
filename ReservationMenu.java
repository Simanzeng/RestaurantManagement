package RestaurantManagement;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.*;

public class ReservationMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;// 定义表格模型对象
	private JTable table;// 定义表格对象
	
	private int[] selectedRow;
	private String strValue="";
	private UserName userName;
    private DatabaseConnection dbc=null;
    
    
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	private JLabel lblNewLabel;
	
	
	
	
	void addTable()   //建表
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="create table t" + userName.gettableNum() + " (id int(4) primary key, name char(20), cost int, sell int)";
			stat.execute(sql);
			
			
		}catch(Exception ex)
		{
			
		}
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
				String[] rowValues = {rs.getString("id"),rs.getString("name"),rs.getString("num"),rs.getString("sell") };// 创建表格行数组
				tableModel.addRow(rowValues);// 向表格模型中添加一行
				int rowCount = table.getRowCount() + 1;
			}
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	void updatediningTable() //修改餐桌
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="UPDATE dining_table SET status=1 WHERE id=";
		    sql=sql + userName.gettableNum();
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	
	
	
	void addmenuTable(MouseEvent e)   //向菜单表表中添加的某一数据
	{
		int sumSell=0; //总价
		int sumCost=0; //总成本
		
		try
		{
			
			for(int i=0;i<selectedRow.length;i++)   //点菜个数
			{
				//在菜谱表中查询点的菜
				stat=conn.createStatement(); //创建命令语句
				String sql="SELECT * FROM menu where id="+table.getValueAt(selectedRow[i], 0);
				ResultSet rs=stat.executeQuery(sql);
				rs.next();
				
				//将点的菜插入表
				stat=conn.createStatement(); //创建命令语句
				String sql1="insert into t" + userName.gettableNum() + " values(";
			    sql1=sql1 + rs.getInt("id") + ",'" + rs.getString("name") + "',";
			    sql1=sql1 + rs.getInt("cost") + "," + rs.getInt("sell") + ")";
			    stat.executeUpdate(sql1);
			    
			    
			    //在菜谱表中的将被点的菜的库存减一
			    stat=conn.createStatement(); //创建命令语句
				String sql2="UPDATE menu SET num=" + (rs.getInt("num")-1) + " WHERE id=";
			    sql2=sql2 + rs.getInt("id");
			    stat.executeUpdate(sql2);
			    
			    //将订单加入总订单
			    stat=conn.createStatement(); //创建命令语句
				String sql4="insert into allmenu(name) values('";
			    sql4=sql4 + rs.getString("name") + "')";
			    stat.executeUpdate(sql4);
			    
			    
			    sumSell=sumSell+rs.getInt("sell");
			    sumCost=sumCost+rs.getInt("cost");
				
			}
			updatediningTable();  //让餐桌状态显示被占用
			
			
			//将订单详情插入结账管理表单
			stat=conn.createStatement(); //创建命令语句
			String sql3="insert into bill values(now(),";
		    sql3=sql3 + userName.gettableNum() + "," + sumSell + ",";
		    sql3=sql3 + sumCost + "," + (sumSell-sumCost) + ", 0)";
		    stat.executeUpdate(sql3);
			
			
			
		}catch(Exception ex)
		{
			//ex.printStackTrace();
		}
		
	}
	
	
	
	public ReservationMenu() {
		super();
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("菜谱管理");
		//setBounds(100, 100, 432, 335);
		setBounds(100, 100, 450, 300);
		final JPanel pane2 = new JPanel();
		getContentPane().add(pane2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u83DC");
		pane2.add(lblNewLabel);
		
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		String[] columnNames = { "编号", "菜名","库存","价格"};// 定义表格列名数组
		String[][] tableValues = {};// 定义表格数据数组
		// 创建指定表格列名和表格数据的表格模型
		tableModel = new DefaultTableModel(tableValues, columnNames);
		
		table = new JTable(tableModel);// 创建指定表格模型的表格
		table.setRowSorter(new TableRowSorter<>(tableModel));// 设置表格的排序器
		// 设置表格的选择模式为单选
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// 为表格添加鼠标事件监听器
		table.addMouseListener(new MouseAdapter() {
			// 发生了点击事件
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setViewportView(table);
		final JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		final JButton okButton = new JButton("\u786E\u5B9A");  //确定
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				selectedRow= table.getSelectedRows();// 获得被选中行的索引
				//strValue=table.getValueAt(selectedRow, 0).toString();
				addTable();
				addmenuTable(e);
				
				//进入确认界面
				ReservationSure sureFrame=new ReservationSure();
				sureFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton cancelButton = new JButton("\u53D6\u6D88");  //取消
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ReservationTable resFrame=new ReservationTable();
				resFrame.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(cancelButton);
		
		JLabel lblNewLabel_1 = new JLabel("         ");
		panel.add(lblNewLabel_1);
		
		
		
		panel.add(okButton);
		showmenuTable();
		
	}
}