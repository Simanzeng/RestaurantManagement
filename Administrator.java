package RestaurantManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Administrator extends JFrame {

	private JPanel contentPane;
	private JButton loggingButton;   //人员管理(注册)
	private JButton modifyButton;    //修改密码
	private JButton menuButton;      //菜谱管理
	private JButton tableButton;     //餐桌管理
	private JButton reservationButton;  //订菜管理
	private JButton billButton;        //结账管理
	private JButton turnoverButton;    //日营业额
	
	private UserName userName;
	private JLabel userLabel;
	//private String userName;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Administrator() {
		setTitle("\u9910\u996E\u7BA1\u7406\u7CFB\u7EDF");
		//setBounds(100, 100, 454, 366);
		
		setBounds(100, 100, 450, 300);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		loggingButton = new JButton("\u4EBA\u5458\u7BA1\u7406"); //人员管理（注册）
		loggingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至人员管理界面
				Loggin LogginFrame=new Loggin();
			    LogginFrame.setVisible(true);
			}
		});
		
		modifyButton = new JButton("\u4FEE\u6539\u5BC6\u7801");  //修改密码
		modifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//跳转至密码修改界面
				ModifyPassword ModifyFrame=new ModifyPassword();
			    ModifyFrame.setVisible(true);
				
			}
		});
		
		menuButton = new JButton("\u83DC\u8C31\u7BA1\u7406");  //菜谱管理
		menuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至菜谱管理界面
				Menu MenuFrame=new Menu();
				MenuFrame.setVisible(true);
				
			}
		});
		
		tableButton = new JButton("\u9910\u684C\u7BA1\u7406"); //餐桌管理
		
		tableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至餐桌管理界面
				Table TableFrame=new Table();
				TableFrame.setVisible(true);
			}
		});
		
		reservationButton = new JButton("\u8BA2\u5355\u7BA1\u7406"); //订菜管理
		reservationButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至订菜管理界面
				AllMenu allmenuFrame=new AllMenu();
				allmenuFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		billButton = new JButton("\u7ED3\u8D26\u7BA1\u7406");  //结账管理
		billButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至结账管理界面
				AdminBill billFrame=new AdminBill();
				billFrame.setVisible(true);
				setVisible(false);
			}
		});
		
		turnoverButton = new JButton("\u65E5\u8425\u4E1A\u989D");  //日营业额
		turnoverButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//跳转至日营业额界面
				DateProfit dateprofitFrame=new DateProfit();
				dateprofitFrame.setVisible(true);
			}
		});
		
		userName=new UserName();
		userLabel = new JLabel("欢迎您：" + userName.getUserName());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(96)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(reservationButton)
								.addComponent(menuButton)
								.addComponent(loggingButton))
							.addGap(56)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tableButton)
								.addComponent(billButton)
								.addComponent(turnoverButton)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(32)
							.addComponent(userLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
							.addComponent(modifyButton)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(modifyButton)
						.addComponent(userLabel))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(menuButton)
						.addComponent(tableButton))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(reservationButton)
						.addComponent(billButton))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loggingButton)
						.addComponent(turnoverButton))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
