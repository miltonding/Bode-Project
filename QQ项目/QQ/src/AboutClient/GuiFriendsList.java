package AboutClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.*;
import ToolPackage.*;
//内部含有一个不断接受消息的线程，
//在客户端写出消息
public class GuiFriendsList {
	private JFrame friendslistframe=new JFrame("QQ");
	
	//panel
	private JPanel friendspanel=new  JPanel(new BorderLayout());
	
	//panel详细
	private JPanel northpanel=new  JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel centerpanel=new  JPanel();
	private JPanel southpanel=new  JPanel();

	//用户头像
	private JLabel pictureLabel2=new JLabel(new ImageIcon("user.jpg"));
	//用户名称
	private  JLabel userJLabel=new JLabel("");

	//用户列表
	private JList friendsshowList=new JList();
	//list集合
	private  DefaultListModel friendslistModel=new DefaultListModel();
	//与服务器建立连接的客户端
	private  ArrayList<Socket> socketlist=new ArrayList<Socket>();
	
	private Socket socket;
	//账号
	private String username;
	
	 //记录客户端的username和socket的map集合 
	private  HashMap<String, Socket> clientMap=new HashMap<String, Socket>();
	
	//一个好友，对应一个对话窗口
	private HashMap<String, GuiClient> userWindow=new HashMap<String, GuiClient>();
	



	public DefaultListModel getFriendslistModel() {
		return friendslistModel;
	}

	public HashMap<String, GuiClient> getUserWindow() {
		return userWindow;
	}

	public void setUserWindow(HashMap<String, GuiClient> userWindow) {
		this.userWindow = userWindow;
	}

		//设置名称
		public void setUserJLabel(String username) {
			userJLabel.setText("        "+username+"的好友列表");
		}
		
		public GuiFriendsList(String username,Socket socket) {
			this.username=username;
			this.socket=socket;
			//初始化窗口
			init();
			//添加监听器
			addListener();
		
			
		}


		//布局初始化函数
		public void init(){
		//初始化
			InitFrame.initframeright(friendslistframe, 280, 650);
			friendslistframe.add(friendspanel);
			//添加好友显示列表
			centerpanel.add(friendsshowList);
	      //布局
			friendspanel.add(northpanel,BorderLayout.NORTH);
			friendspanel.add(centerpanel,BorderLayout.CENTER);
			friendspanel.add(southpanel,BorderLayout.SOUTH);

			//北部
			northpanel.setLayout(new BorderLayout());

	
			//用户头像 姓名 栏
			JPanel  northoneJPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
			northoneJPanel.setBackground(new Color(36,180,239));
			northoneJPanel.setSize(230,60);
			northoneJPanel.add(pictureLabel2);
			userJLabel.setBackground(new Color(36,180,239));
			northoneJPanel.add(userJLabel);
			northpanel.add(northoneJPanel,BorderLayout.NORTH);
	
			//南部栏
			JPanel  southoneJPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
			southoneJPanel.setBackground(new Color(36,180,239));
			JTextField search=new JTextField(13);
			JButton addButton=new JButton("add");
			JButton deleteButton=new JButton("del");
			southoneJPanel.add(search);
			southoneJPanel.add(addButton);
			southoneJPanel.add(deleteButton);
			northpanel.add(southoneJPanel,BorderLayout.SOUTH);

			//南部
			southpanel.setBackground(new Color(36,180,239));
			//中部
			//2设置Jlist的model
			friendsshowList.setModel(friendslistModel);
			//好友尺寸250 56
			friendsshowList.setFixedCellWidth(250);
			friendsshowList.setFixedCellHeight(30);


		}
		
		
		
		//添加监听器
		public void addListener(){
				
				
			friendslistframe.addWindowListener(new WindowAdapter() {

				
				public void windowClosing(WindowEvent e) {
					//下线
					//发送给服务器用户名消息
					try {
							ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
							Message message=new Message(username,3);
							oos.writeObject(message);
							System.out.println("发送下线通知！！！！！！！！！！！！！");
							oos.flush();
							System.exit(-1);
			
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					
					
				});
				//Jlist添加监听器
				friendsshowList.addMouseListener(new MouseAdapter() {

					//双击打开聊天窗口
					public void mouseClicked(MouseEvent e) {
						
							
							if(e.getClickCount()==2){
							//获取点击内容
								String content=(String)friendsshowList.getSelectedValue();
								content=content.trim();
								System.out.println(content);
									//私聊
									if(content.equals("ALL")==false){
										String[] s=content.split(" +");
										String[] s2=s[1].split(":");
										String[] s3=s[0].split(":");
										//获得端口号
										int port=Integer.parseInt(s2[1]);
										//获得用户名
										String receivename=s3[1];
										//打开客户端
										//传递发送人 接收人 接收人端口号
											if(userWindow.containsKey(receivename)==false){
												GuiClient c=new GuiClient(socket,username,receivename,port);
											userWindow.put(receivename, c);
											}
									}
									//群聊
									else if(content.equals("ALL")==true){
										System.out.println("2222222222222222222");
										if(userWindow.containsKey("ALL")==false){
										GuiClient c=new GuiClient(socket,username,"ALL",0);
										userWindow.put("ALL", c);
										}
									}
								}
							}
					
					
					});
				}

	
}
