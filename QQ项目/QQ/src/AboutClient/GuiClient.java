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



//�ͻ���ֻ����Ϣ
public class GuiClient {
	JFrame f=new JFrame("�������촰��");
	//����
	private JPanel talkPanel=new JPanel(new BorderLayout());
	private JPanel northPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
	private JPanel southPanel=new JPanel();
	private JPanel eastPanel=new JPanel();
	
	//�����¼
	private JTextArea talkArea=new JTextArea(12,37);
	private JTextArea sendArea=new JTextArea(11,37);
	//��ť
	private JButton close=new JButton("�ر�");
	private JButton send=new JButton("����");
	private JButton shack=new JButton("����");
	private Socket socket;
	//������
	private String username;
	//������
	private String receivename;
	//�����˶˿ں�
	private int port;
	 
	private ObjectOutputStream oos;
	//username��Socket�ļ���
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
		
		
		//��ô���
		public JFrame getF() {
			return f;
		}

		public void setF(JFrame f) {
			this.f = f;
		}

		
		//��ý��տ�
		public JTextArea getTalkArea() {
		return talkArea;
		}
		
		public void setTalkArea(JTextArea talkArea) {
		this.talkArea = talkArea;
		}
		//��ʼ�����ֺ���
		public void init(){
			InitFrame.initframecenter(f, 510, 550);
			talkArea.setEditable(false);
			//����
			f.add(talkPanel);
			//����
			
			//�������촰�ڵı���
			JLabel northLabel=new JLabel("��ĺ��� "+receivename+"�˿ں�"+port+"    ����");
			northPanel.add(northLabel);
			northPanel.setBackground(new Color(67,191,241));
			talkPanel.add(northPanel,BorderLayout.NORTH);
			
			//�в�;
			centerPanel.setLayout(new BorderLayout());
			//������Ϣ��
			JPanel northonePanel=new JPanel();
			northonePanel.add(talkArea);
			//����
			JPanel centeronePanel=new JPanel();
			centeronePanel.setBackground(new Color(196,230,246));
			//���Ϳ�
			JPanel southonePanel=new JPanel();
			southonePanel.add(sendArea);
			centerPanel.add(northonePanel,BorderLayout.NORTH);
			centerPanel.add(centeronePanel,BorderLayout.CENTER);
			centerPanel.add(southonePanel,BorderLayout.SOUTH);
			
			talkPanel.add(centerPanel,BorderLayout.CENTER);
			
			
			//�ϲ�
			JLabel fillLabel=new JLabel();
			fillLabel.setPreferredSize(new Dimension(85,10));
			southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			southPanel.add(shack);
			southPanel.add(close);
			southPanel.add(send);
			southPanel.add(fillLabel);
			talkPanel.add(southPanel,BorderLayout.SOUTH);
			//����
			JLabel east=new JLabel();
			east.setPreferredSize(new Dimension(80,10));
			eastPanel.add(east);
			talkPanel.add(eastPanel,BorderLayout.EAST);
		}
		public void addListener(){
			
			
			//���Ͱ�ť
			send.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ObjectOutputStream oos=null;
					try {
						 
						 oos=new ObjectOutputStream(socket.getOutputStream());
						 //��װ��������
						
						 String content=username+":"+receivename+":"+sendArea.getText();
						 String[] s=content.split(":");
						 //����
						 String content2=s[2];
							
						 Message message=null;
							 //˽����Ϣ
							 if(receivename.equals("ALL")==false){
								 message=new Message(content,1);
								 System.out.println("   �ͻ��˷���˽����˽��˽��˽��Ϣ��Ϣ�ɹ���");
							 }
							 //Ⱥ����Ϣ
							 else if(receivename.equals("ALL")==true){
								  message=new Message(content,2);
								  System.out.println("   �ͻ��˷���allallallallall��Ϣ�ɹ���");
							 }
						 oos.writeObject(message);
						 System.out.println("   �ͻ��˷�����Ϣ�ɹ���");
						 oos.flush();
						 sendArea.setText("");
						 talkArea.append("��˵��   "+content2+"\n");

						} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					
					}
				});
			
			
				//������ť
				shack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//��װ������Ϣ����ȥ
						try {
							ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
							String content=username+":"+receivename+":"+"������Ϣ";
							Message message=new Message(content,4);
							oos.writeObject(message);
							oos.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
							int x=f.getX();
							int y=f.getY();
							//�ı�����
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
							//���߳�˯��һ��
							try {
									Thread.sleep(50);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}//forѭ������
					
					
					}
				});
			}
		
	

	}
