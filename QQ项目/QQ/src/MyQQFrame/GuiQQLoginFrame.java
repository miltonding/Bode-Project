package MyQQFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import AboutClient.GuiFriendsList;
import AboutClient.ClientThread;
import Model.*;
import ToolPackage.*;

public class GuiQQLoginFrame {
	private JFrame loginFrame=new JFrame("QQ2016");
	
	//总布局采用BorderLayout
	private JPanel LoginPanel=new JPanel(new BorderLayout());
	
		//分布局
	private JPanel northPanel=new JPanel();
	private JPanel southPanel=new JPanel();
	private JPanel westPanel=new JPanel();	
	private JPanel centerPanel=new JPanel();
	

	//图片
	private JLabel pictureLabel=new JLabel(new ImageIcon("1.jpg"));
	private JLabel usernamelabel=new JLabel("    账号");	
	private JLabel passwordLabel=new JLabel("密码");	
	private JLabel messageoneLabel=new JLabel("注册新账号");	
	private JLabel messagetwoLabel=new JLabel("找回密码");
	
	
	//TextField
	private JTextField usernameField=new JTextField(15);
	private JPasswordField passwordField=new JPasswordField(15);
	
	
	//复选框
	private JCheckBox c1=new JCheckBox("记住密码");
	private JCheckBox c2=new JCheckBox("自动登录	");
	
	
	//Button
	private JButton submit=new JButton("登陆");
	
	//提示框
	private JLabel warning=new JLabel(" 您的账号密码错误！    请重新输入         ");
	
	//dialog
	private JDialog warningDialog=new JDialog(loginFrame);
	private JDialog reLoginDialog=new JDialog(loginFrame);
	
	//账号密码
	private String username="";	
	private String password="";
	
	private Socket socket;
	

	
	




		public GuiQQLoginFrame() {
			//初始化窗口
			init();
			//与服务器建立连接
			try {
				socket=new 	Socket("127.0.0.1",9999);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//接受登录验证消息的线程
			Thread t1=new ClientThread(socket,loginFrame,warning,reLoginDialog);
			t1.start();
			//添加监听器
			addListener();
		
			}
	
	
		
			public void init(){
				//设置dialog的参数
				InitFrame.initDialogcenter(warningDialog, 100, 100);
				InitFrame.initDialogcenter(reLoginDialog,100,100);
				JLabel nullJLabel=new JLabel("账号为空！");
				JPanel dialogPanel=new JPanel();
				dialogPanel.add(nullJLabel);
				JPanel d=new JPanel();
				d.add(new JLabel("该账号已登录！"));
				warningDialog.add(dialogPanel);
				reLoginDialog.add(d);
				//设置warning警告框的参数
				warning.setVisible(false);
		
				//初始化
				InitFrame.initframecenter(loginFrame, 405, 305);
		
				//设置颜色
				loginFrame.setBackground(new Color(255,255,255));
				centerPanel.setBackground(new Color(227,244,254));

				loginFrame.add(LoginPanel);

				//总布局
				LoginPanel.add(northPanel,BorderLayout.NORTH);
				LoginPanel.add(southPanel,BorderLayout.SOUTH);
				LoginPanel.add(centerPanel,BorderLayout.CENTER);
		
		
				//北面布局
				northPanel.setLayout(new BorderLayout());
				//图片
				northPanel.add(pictureLabel);
			
				//南面布局
				southPanel.add(warning);
				southPanel.add(submit);
				southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				southPanel.setBackground(new Color(191,225,250));
		

				//中间布局
				BorderLayout borderLayout=new BorderLayout();
				centerPanel.setLayout(borderLayout);
				borderLayout.setVgap(10);
				JPanel northone=new JPanel(new BorderLayout());
				//第一行
				JPanel rowone=new JPanel(new FlowLayout(FlowLayout.CENTER));
				rowone.setBackground(new Color(227,244,254));
				rowone.add(usernamelabel);
				rowone.add(usernameField);
				rowone.add(messageoneLabel);
				//第二行
				JPanel rowtwo=new JPanel(new FlowLayout(FlowLayout.CENTER));
				rowtwo.setBackground(new Color(227,244,254));
				rowtwo.add(passwordLabel);
				rowtwo.add(passwordField);
				rowtwo.add(messagetwoLabel);
				northone.add(rowone,BorderLayout.NORTH);
				northone.add(rowtwo,BorderLayout.SOUTH);
				centerPanel.add(northone,BorderLayout.NORTH);
				JPanel southone=new JPanel();
				southone.setBackground(new Color(227,244,254));
				southone.add(c1);
				southone.add(c2);
				c1.setBackground(new Color(227,244,254));
				c2.setBackground(new Color(227,244,254));
				centerPanel.add(southone,BorderLayout.SOUTH);
        

			}
		
		
		
			//注册监听器
			public void addListener(){
					//窗口监听器
					loginFrame.addWindowListener(new WindowAdapter() {

							public void windowClosing(WindowEvent e) {
								System.exit(-1);			}

					});
		        
					//按钮监听器
					submit.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							//验证密码
							try {
									
								username=usernameField.getText()==null?"":usernameField.getText().trim();
								password=passwordField.getText()==null?"":passwordField.getText().trim();
									
									if(username.length()==0){
									warningDialog.setVisible(true);
									}
									
									
									else if(username.length()!=0)
									{
									//向服务器发送消息
									ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
									String content=username+":"+password;
									Message message=new Message(content,0);
									oos.writeObject(message);
									System.out.println("用户名密码发送给了服务器!!!");
									oos.flush();
									
									}

								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

						});
						

				}
	
				public static void main(String[] args) {
					GuiQQLoginFrame frame1=new GuiQQLoginFrame();
				}
	
}
