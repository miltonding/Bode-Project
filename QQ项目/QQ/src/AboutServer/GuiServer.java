package AboutServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.SocketHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ToolPackage.*;

public class GuiServer {
	private JFrame talkframe=new JFrame("������");
	//����
	private JPanel p=new JPanel();
	private JPanel northPanel=new JPanel();
	private JPanel centerPanel=new JPanel();
	private JPanel southPanel=new JPanel();
	private JPanel eastPanel=new JPanel();
	
	//��ť
	private JButton close=new JButton("�ر�");
	private JButton send=new JButton("����");
	private JTextArea j1=new JTextArea(12,37);
	private JTextArea j2=new JTextArea(11,37);
	private JScrollPane s=new JScrollPane(j1);
	
	private Socket socket;
	 

	//��¼�ͻ��˵Ķ˿ںź�socket��map����
	private  HashMap<Integer, Socket> socketMap=new HashMap<Integer, Socket>(); 
	//socket���ܱ����л�
	//��¼�ͻ��˵�username��port��map���� 
	private  HashMap<String, Integer> clientMap=new HashMap<String, Integer>();
	  

		public GuiServer() {
	
		init();
		ServerSocket server;
			try {
				server = new ServerSocket(9999);
					while(true){
						socket = server.accept();
						InetSocketAddress address=((InetSocketAddress)socket.getRemoteSocketAddress());
						String ip=address.getAddress().getHostAddress();
						int port=address.getPort();
						System.out.println("��������������ӵ���      "+"ip��ַ"+ip+"      �˿ں�"+port);
						//	���߳�
						Thread read=new ServerReadThread(socket,j1,socketMap,clientMap);
						read.start();
					}
	 		
			} catch (IOException e) {
			
			e.printStackTrace();
			}

	
		}

	 	
	 	public  void init(){
	 		InitFrame.initframecenter(talkframe, 510, 550);
			j1.setEditable(false);
		
			//����
			talkframe.add(p);
			//����
			JLabel northLabel=new JLabel("  ������                                                             ");
			northPanel.add(northLabel);
			northPanel.setBackground(new Color(67,191,241));
			p.add(northPanel,BorderLayout.NORTH);
			
			//�в�;
			centerPanel.setLayout(new BorderLayout());
			//������Ϣ��
			JPanel northonePanel=new JPanel();
			northonePanel.add(j1);
			//����
			JPanel centeronePanel=new JPanel();
			centeronePanel.setBackground(new Color(196,230,246));
			//���Ϳ�
			JPanel southonePanel=new JPanel();
			southonePanel.add(j2);
			centerPanel.add(northonePanel,BorderLayout.NORTH);
			centerPanel.add(centeronePanel,BorderLayout.CENTER);
			centerPanel.add(southonePanel,BorderLayout.SOUTH);
			
			p.add(centerPanel,BorderLayout.CENTER);
			
			
			//�ϲ�
			JLabel fillLabel=new JLabel();
			fillLabel.setPreferredSize(new Dimension(85,10));
			southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			southPanel.add(close);
			southPanel.add(send);
			southPanel.add(fillLabel);
			p.add(southPanel,BorderLayout.SOUTH);
			//����
			JLabel east=new JLabel();
			east.setPreferredSize(new Dimension(80,10));
			eastPanel.add(east);
			p.add(eastPanel,BorderLayout.EAST);
		
	 	}

	 	
	 	public static void main(String[] args) {
	 		GuiServer s=new GuiServer();
	 	}

	}
