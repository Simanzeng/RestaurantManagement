package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

public class ModifyPassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField oldPassword;
	private JPasswordField newPassword1;
	private JPasswordField newPassword2;
	
	private UserName userName;
    private DatabaseConnection dbc=null;
	
	Connection conn=null;
	ResultSet rs=null;   //结果集
	Statement stat=null;
	private JLabel errorLabel;

	/**
	 * Launch the application.
	 */
	
	
	boolean selectAdminTable(MouseEvent e) //查询管理员表中是否有此用户
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="SELECT id FROM admin where name=";
			sql=sql + "'" + userName.getUserName() + "' AND password=";
			sql=sql + "'" + oldPassword.getText() + "'";
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
	
	void updateAdminTable(MouseEvent e) //更新密码
	{
		try
		{
			stat=conn.createStatement(); //创建命令语句
			String sql="UPDATE admin SET password='";
		    sql=sql + newPassword1.getText() + "'WHERE password='";
		    sql=sql + oldPassword.getText() + "'";
		    stat.executeUpdate(sql);
			
			
			
		}catch(Exception ex)
		{
			
		}
	}
	
	

	/**
	 * Create the frame.
	 */
	public ModifyPassword() {
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 261, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		
		this.dbc=new DatabaseConnection();
		this.conn=dbc.getConnection();
		
		userName=new UserName();
		
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel password1 = new JLabel("\u539F\u5BC6\u7801");
		
		JLabel password2 = new JLabel("\u65B0\u5BC6\u7801");
		
		JLabel password3 = new JLabel("\u786E\u8BA4");
		
		oldPassword = new JPasswordField();
		
		newPassword1 = new JPasswordField();
		
		newPassword2 = new JPasswordField();
		
		JButton cancelButton = new JButton("\u5173\u95ED");  //关闭界面
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		
		JButton okButton = new JButton("\u786E\u8BA4");  //修改密码
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(selectAdminTable(e))
				{
					if(newPassword1.getText().toString().equals(newPassword2.getText().toString()))
					{
						updateAdminTable(e);
						errorLabel.setText("修改成功");
						oldPassword.setText("");
						newPassword1.setText("");
						newPassword2.setText("");
					}
					else
					{
						errorLabel.setText("两次密码不一致，请重新输入");
						oldPassword.setText("");
						newPassword1.setText("");
						newPassword2.setText("");
					}
				}
				else
				{
					errorLabel.setText("原密码错误，请重新输入");
					oldPassword.setText("");
					newPassword1.setText("");
					newPassword2.setText("");
				}
				
			}
		});
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(108)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(cancelButton)
							.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
							.addComponent(okButton)
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(password1)
							.addGap(18)
							.addComponent(oldPassword, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(password2)
								.addComponent(password3))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(newPassword2, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addComponent(newPassword1, 150, 150, 150))))
					.addGap(112))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(129)
					.addComponent(errorLabel)
					.addContainerGap(241, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorLabel)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password1)
						.addComponent(oldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password2)
						.addComponent(newPassword1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password3)
						.addComponent(newPassword2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(okButton))
					.addGap(32))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
