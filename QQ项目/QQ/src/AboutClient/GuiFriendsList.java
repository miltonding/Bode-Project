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
//�ڲ�����һ�����Ͻ�����Ϣ���̣߳�
//�ڿͻ���д����Ϣ
public class GuiFriendsList {
	private JFrame friendslistframe=new JFrame("QQ");
	
	//panel
	private JPanel friendspanel=new  JPanel(new BorderLayout());
	
	//panel��ϸ
	private JPanel northpanel=new  JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel centerpanel=new  JPanel();
	private JPanel southpanel=new  JPanel();

	//�û�ͷ��
	private JLabel pictureLabel2=new JLabel(new ImageIcon("user.jpg"));
	//�û�����
	private  JLabel userJLabel=new JLabel("");

	//�û��б�
	private JList friendsshowList=new JList();
	//list����
	private  DefaultListModel friendslistModel=new DefaultListModel();
	//��������������ӵĿͻ���
	private  ArrayList<Socket> socketlist=new ArrayList<Socket>();
	
	private Socket socket;
	//�˺�
	private String username;
	
	 //��¼�ͻ��˵�username��socket��map���� 
	private  HashMap<String, Socket> clientMap=new HashMap<String, Socket>();
	
	//һ�����ѣ���Ӧһ���Ի�����
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

		//��������
		public void setUserJLabel(String username) {
			userJLabel.setText("        "+username+"�ĺ����б�");
		}
		
		public GuiFriendsList(String username,Socket socket) {
			this.username=username;
			this.socket=socket;
			//��ʼ������
			init();
			//��Ӽ�����
			addListener();
		
			
		}


		//���ֳ�ʼ������
		public void init(){
		//��ʼ��
			InitFrame.initframeright(friendslistframe, 280, 650);
			friendslistframe.add(friendspanel);
			//��Ӻ�����ʾ�б�
			centerpanel.add(friendsshowList);
	      //����
			friendspanel.add(northpanel,BorderLayout.NORTH);
			friendspanel.add(centerpanel,BorderLayout.CENTER);
			friendspanel.add(southpanel,BorderLayout.SOUTH);

			//����
			northpanel.setLayout(new BorderLayout());

	
			//�û�ͷ�� ���� ��
			JPanel  northoneJPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
			northoneJPanel.setBackground(new Color(36,180,239));
			northoneJPanel.setSize(230,60);
			northoneJPanel.add(pictureLabel2);
			userJLabel.setBackground(new Color(36,180,239));
			northoneJPanel.add(userJLabel);
			northpanel.add(northoneJPanel,BorderLayout.NORTH);
	
			//�ϲ���
			JPanel  southoneJPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
			southoneJPanel.setBackground(new Color(36,180,239));
			JTextField search=new JTextField(13);
			JButton addButton=new JButton("add");
			JButton deleteButton=new JButton("del");
			southoneJPanel.add(search);
			southoneJPanel.add(addButton);
			southoneJPanel.add(deleteButton);
			northpanel.add(southoneJPanel,BorderLayout.SOUTH);

			//�ϲ�
			southpanel.setBackground(new Color(36,180,239));
			//�в�
			//2����Jlist��model
			friendsshowList.setModel(friendslistModel);
			//���ѳߴ�250 56
			friendsshowList.setFixedCellWidth(250);
			friendsshowList.setFixedCellHeight(30);


		}
		
		
		
		//��Ӽ�����
		public void addListener(){
				
				
			friendslistframe.addWindowListener(new WindowAdapter() {

				
				public void windowClosing(WindowEvent e) {
					//����
					//���͸��������û�����Ϣ
					try {
							ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
							Message message=new Message(username,3);
							oos.writeObject(message);
							System.out.println("��������֪ͨ��������������������������");
							oos.flush();
							System.exit(-1);
			
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					
					
				});
				//Jlist��Ӽ�����
				friendsshowList.addMouseListener(new MouseAdapter() {

					//˫�������촰��
					public void mouseClicked(MouseEvent e) {
						
							
							if(e.getClickCount()==2){
							//��ȡ�������
								String content=(String)friendsshowList.getSelectedValue();
								content=content.trim();
								System.out.println(content);
									//˽��
									if(content.equals("ALL")==false){
										String[] s=content.split(" +");
										String[] s2=s[1].split(":");
										String[] s3=s[0].split(":");
										//��ö˿ں�
										int port=Integer.parseInt(s2[1]);
										//����û���
										String receivename=s3[1];
										//�򿪿ͻ���
										//���ݷ����� ������ �����˶˿ں�
											if(userWindow.containsKey(receivename)==false){
												GuiClient c=new GuiClient(socket,username,receivename,port);
											userWindow.put(receivename, c);
											}
									}
									//Ⱥ��
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
