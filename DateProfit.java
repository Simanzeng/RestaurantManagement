package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DateProfit extends JFrame {

	private JPanel contentPane;
	private JLabel sellLabel;
	private JLabel profitLabel;
	
	
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;

	/**
	 * Launch the application.
	 */
	
	private void showBill()
	{
		int sumSell=0;
		int sumProfit=0;
		try
		{
			//结账管理表bill中查询选择的数据
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT * FROM bill";
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next())
			{
				if(rs.getString("status").equals("1"))
				{
				    sumSell=sumSell+rs.getInt("sell");
				    sumProfit=sumProfit+rs.getInt("profit");
				}
			}
			
			sellLabel.setText("今日营业额:"+sumSell);
			profitLabel.setText("利润:"+ sumProfit);
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	

	/**
	 * Create the frame.
	 */
	public DateProfit() {
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//setBounds(100, 100, 281, 198);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		sellLabel = new JLabel("\u4ECA\u65E5\u8425\u4E1A\u989D\u4E3A");
		sellLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton okButton = new JButton("\u786E\u8BA4");   //确认
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setVisible(false);
			}
		});
		
		profitLabel = new JLabel("\u5229\u6DA6");
		profitLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addComponent(sellLabel, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
					.addGap(67))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(134)
					.addComponent(profitLabel)
					.addContainerGap(236, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(184)
					.addComponent(okButton)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(64, Short.MAX_VALUE)
					.addComponent(sellLabel)
					.addGap(18)
					.addComponent(profitLabel)
					.addGap(46)
					.addComponent(okButton)
					.addGap(58))
		);
		contentPane.setLayout(gl_contentPane);
		showBill();
	}
}
