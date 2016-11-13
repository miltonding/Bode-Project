package AboutClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Model.*;
import ToolPackage.*;



//客户端只发消息
public class GuiClient {
	JFrame f=new JFrame("本地聊天窗口");
	//布局
	private JPanel talkPanel=new JPanel(new BorderLayout());
	private JPanel northPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
	private JPanel southPanel=new JPanel();
	private JPanel eastPanel=new JPanel();
	
	//聊天记录
	private JTextArea talkArea=new JTextArea(12,37);
	private JTextArea sendArea=new JTextArea(11,37);
	//按钮
	private JButton close=new JButton("关闭");
	private JButton send=new JButton("发送");
	private JButton shack=new JButton("抖屏");
	private Socket socket;
	//发送人
	private String username;
	//接收人
	private String receivename;
	//接收人端口号
	private int port;
	 
	private ObjectOutputStream oos;
	//username和Socket的集合
	private  HashMap<String, Socket> socketMap;


	
		public GuiClient(Socket socket, String username, String receivename,
			int port) {
			super();
			this.socket = socket;
			this.username = username;
			this.receivename = receivename;
			this.port = port;
			init();
			addListener();
		}
		
		
		//获得窗口
		public JFrame getF() {
			return f;
		}

		public void setF(JFrame f) {
			this.f = f;
		}

		
		//获得接收框
		public JTextArea getTalkArea() {
		return talkArea;
		}
		
		public void setTalkArea(JTextArea talkArea) {
		this.talkArea = talkArea;
		}
		//初始化布局函数
		public void init(){
			InitFrame.initframecenter(f, 510, 550);
			talkArea.setEditable(false);
			//布局
			f.add(talkPanel);
			//北部
			
			//设置聊天窗口的标题
			JLabel northLabel=new JLabel("你的好友 "+receivename+"端口号"+port+"    在线");
			northPanel.add(northLabel);
			northPanel.setBackground(new Color(67,191,241));
			talkPanel.add(northPanel,BorderLayout.NORTH);
			
			//中部;
			centerPanel.setLayout(new BorderLayout());
			//聊天消息框
			JPanel northonePanel=new JPanel();
			northonePanel.add(talkArea);
			//填充框
			JPanel centeronePanel=new JPanel();
			centeronePanel.setBackground(new Color(196,230,246));
			//发送框
			JPanel southonePanel=new JPanel();
			southonePanel.add(sendArea);
			centerPanel.add(northonePanel,BorderLayout.NORTH);
			centerPanel.add(centeronePanel,BorderLayout.CENTER);
			centerPanel.add(southonePanel,BorderLayout.SOUTH);
			
			talkPanel.add(centerPanel,BorderLayout.CENTER);
			
			
			//南部
			JLabel fillLabel=new JLabel();
			fillLabel.setPreferredSize(new Dimension(85,10));
			southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			southPanel.add(shack);
			southPanel.add(close);
			southPanel.add(send);
			southPanel.add(fillLabel);
			talkPanel.add(southPanel,BorderLayout.SOUTH);
			//东部
			JLabel east=new JLabel();
			east.setPreferredSize(new Dimension(80,10));
			eastPanel.add(east);
			talkPanel.add(eastPanel,BorderLayout.EAST);
		}
		public void addListener(){
			
			
			//发送按钮
			send.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ObjectOutputStream oos=null;
					try {
						 
						 oos=new ObjectOutputStream(socket.getOutputStream());
						 //封装发送内容
						
						 String content=username+":"+receivename+":"+sendArea.getText();
						 String[] s=content.split(":");
						 //内容
						 String content2=s[2];
							
						 Message message=null;
							 //私聊消息
							 if(receivename.equals("ALL")==false){
								 message=new Message(content,1);
								 System.out.println("   客户端发送私聊消私聊私聊私聊息消息成功！");
							 }
							 //群聊消息
							 else if(receivename.equals("ALL")==true){
								  message=new Message(content,2);
								  System.out.println("   客户端发送allallallallall消息成功！");
							 }
						 oos.writeObject(message);
						 System.out.println("   客户端发送消息成功！");
						 oos.flush();
						 sendArea.setText("");
						 talkArea.append("我说：   "+content2+"\n");

						} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					
					}
				});
			
			
				//抖屏按钮
				shack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//封装抖屏消息发出去
						try {
							ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
							String content=username+":"+receivename+":"+"抖屏消息";
							Message message=new Message(content,4);
							oos.writeObject(message);
							oos.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
							int x=f.getX();
							int y=f.getY();
							//改变坐标
							for(int i=0;i<20;i++){
								if(i%2==0){
									x=x-3;
									y=y-3;
								}
								else{
									x=x+3;
									y=y+3;
								}
							f.setLocation(x, y);
							//让线程睡眠一会
							try {
									Thread.sleep(50);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}//for循环结束
					
					
					}
				});
			}
		
	

	}
