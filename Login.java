package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JRadioButton;








import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {
	
	
	private JPanel contentPane;    //面板
	private JTextField userText;   //用户名文本框
	private JLabel usersLabel;
	private JLabel passwordLabel;
	private JLabel errorLabel;     //提示登录错误信息
	private JButton cancelButton;  //关闭按钮
	private JButton loginButton;   //登录按钮
	private JRadioButton staffRadioButton; //员工单选按钮
	private JRadioButton adminRadioButton; //管理员单选按钮
	
	private UserName userName=null;
	private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	private JPasswordField passwordText;
	
	
	boolean selectAdminTable(MouseEvent e) //查询管理员表中是否有此用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT id FROM admin where name=";
			sql=sql + "'" + userText.getText() + "' AND password=";
			sql=sql + "'" + passwordText.getText() + "'";
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
	
	boolean selectStaffTable(MouseEvent e)   //查询员工表是否存在此用户
	{
		try
		{
			String sql="SELECT id FROM staff where name=";
			sql=sql + "'" + userText.getText() + "' AND password=";
			sql=sql + "'" + passwordText.getText() + "'";
			stat=conn.createStatement();  //创建命令语句
			rs=stat.executeQuery(sql);
			
			
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
			//ex.printStackTrace();
		}
		
		return false;
	}
	
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Login() {
		
		userName=new UserName();
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		setTitle("\u9910\u996E\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		usersLabel = new JLabel("\u7528\u6237\u540D");
		
		passwordLabel = new JLabel("\u5BC6\u7801");
		
		userText = new JTextField();
		userText.setColumns(10);
		
		errorLabel = new JLabel("                  ");
		errorLabel.setForeground(Color.RED);
		
		
		
		
		cancelButton = new JButton("\u5173\u95ED"); //关闭 
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		
		loginButton = new JButton("\u767B\u5F55");       //登录按钮
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(adminRadioButton.isSelected()) //单选框选择管理员
				{
					if(selectAdminTable(arg0))   //登录成功
					{
				        passwordText.setText("");
				        errorLabel.setText("");
				        userName.setUserName(userText.getText().toString());
				    
				        ///跳转至管理员界面
				        Administrator AdminFrame=new Administrator();
				        AdminFrame.setVisible(true);
				    
					}
					else   //登录失败
					{
						errorLabel.setText("用户名不存在或密码错误");
						passwordText.setText("");
					}
				}
				else if(staffRadioButton.isSelected())
				{
					if(selectStaffTable(arg0))   //登录成功
					{
						passwordText.setText("");
						errorLabel.setText("");
						userName.setUserName(userText.getText().toString());
						
						///跳转至员工界面
						Staff StaffFrame=new Staff();
						StaffFrame.setVisible(true);
						
						
					}
					else    //登录失败
					{
						errorLabel.setText("用户名不存在或密码错误");
						passwordText.setText("");
					}
				}
				
			}
		});
		
		staffRadioButton = new JRadioButton("\u5458\u5DE5");
		staffRadioButton.setSelected(true);
		
		adminRadioButton = new JRadioButton("\u7BA1\u7406\u5458");
		ButtonGroup radioGroup=new ButtonGroup();
		radioGroup.add(staffRadioButton);
		radioGroup.add(adminRadioButton);
		
		passwordText = new JPasswordField("");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(86)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(usersLabel)
						.addComponent(passwordLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(errorLabel)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addComponent(userText, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(staffRadioButton)
									.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
									.addComponent(adminRadioButton))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(cancelButton)
									.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
									.addComponent(loginButton)))
							.addContainerGap(112, GroupLayout.PREFERRED_SIZE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(errorLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(usersLabel)
						.addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(adminRadioButton)
						.addComponent(staffRadioButton))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton)
						.addComponent(cancelButton))
					.addGap(34))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
