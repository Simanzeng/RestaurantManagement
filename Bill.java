package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill extends JFrame {

	private JPanel contentPane;
	private JLabel sellLabel;
	private JLabel tableLabel;
	private JLabel timeLabel;
	
	
	private UserName userName;
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	
	private void showBill()
	{
		try
		{
			//结账管理表bill中查询选择的数据
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM bill where id=" + userName.gettableNum() + " AND status=0";
			ResultSet rs=stat.executeQuery(sql);
			rs.next();
			timeLabel.setText(rs.getString("t"));
			tableLabel.setText(rs.getString("id"));
			sellLabel.setText(rs.getString("sell"));
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	private void billMenu()
	{
		
		try
		{
			//更新bill表
			stat=conn.createStatement(); //创建命令语句
			String sql="UPDATE bill SET status=1 WHERE id=" + userName.gettableNum() + " AND status=0";
		    stat.executeUpdate(sql);
			
		    //更新餐桌表dining_table
		    stat=conn.createStatement(); //创建命令语句
			String sql2="UPDATE dining_table SET status=0 WHERE id=";
		    sql2=sql2 + userName.gettableNum();
		    stat.executeUpdate(sql2);
		    
		    
		    //删除订单表
		    stat=conn.createStatement(); //创建命令语句
			String sql3="drop table t" + userName.gettableNum();
		    stat.executeUpdate(sql3);
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Bill() {
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		setTitle("\u7ED3\u8D26\u7BA1\u7406");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u7528\u9910\u65F6\u95F4");
		
		timeLabel = new JLabel("             ");
		
		JLabel lblNewLabel_1 = new JLabel("\u9910\u684C\u53F7");
		
		tableLabel = new JLabel("             ");
		
		JLabel lblNewLabel_2 = new JLabel("\u603B\u4EF7\u683C");
		
		sellLabel = new JLabel("         ");
		
		JButton cancelButton = new JButton("\u53D6\u6D88");  //取消
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				BillTable billtableFrame=new BillTable();
				billtableFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton billButton = new JButton("\u7ED3\u8D26");  //结账
		billButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				billMenu();
				Billsure billsure=new Billsure();
				billsure.setVisible(true);
				setVisible(false);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(cancelButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(billButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(sellLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tableLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(timeLabel, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(timeLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(tableLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(sellLabel))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(billButton))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		showBill();
	}
}
