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
	
	//�ܲ��ֲ���BorderLayout
	private JPanel LoginPanel=new JPanel(new BorderLayout());
	
		//�ֲ���
	private JPanel northPanel=new JPanel();
	private JPanel southPanel=new JPanel();
	private JPanel westPanel=new JPanel();	
	private JPanel centerPanel=new JPanel();
	

	//ͼƬ
	private JLabel pictureLabel=new JLabel(new ImageIcon("1.jpg"));
	private JLabel usernamelabel=new JLabel("    �˺�");	
	private JLabel passwordLabel=new JLabel("����");	
	private JLabel messageoneLabel=new JLabel("ע�����˺�");	
	private JLabel messagetwoLabel=new JLabel("�һ�����");
	
	
	//TextField
	private JTextField usernameField=new JTextField(15);
	private JPasswordField passwordField=new JPasswordField(15);
	
	
	//��ѡ��
	private JCheckBox c1=new JCheckBox("��ס����");
	private JCheckBox c2=new JCheckBox("�Զ���¼	");
	
	
	//Button
	private JButton submit=new JButton("��½");
	
	//��ʾ��
	private JLabel warning=new JLabel(" �����˺��������    ����������         ");
	
	//dialog
	private JDialog warningDialog=new JDialog(loginFrame);
	private JDialog reLoginDialog=new JDialog(loginFrame);
	
	//�˺�����
	private String username="";	
	private String password="";
	
	private Socket socket;
	

	
	




		public GuiQQLoginFrame() {
			//��ʼ������
			init();
			//���������������
			try {
				socket=new 	Socket("127.0.0.1",9999);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//���ܵ�¼��֤��Ϣ���߳�
			Thread t1=new ClientThread(socket,loginFrame,warning,reLoginDialog);
			t1.start();
			//��Ӽ�����
			addListener();
		
			}
	
	
		
			public void init(){
				//����dialog�Ĳ���
				InitFrame.initDialogcenter(warningDialog, 100, 100);
				InitFrame.initDialogcenter(reLoginDialog,100,100);
				JLabel nullJLabel=new JLabel("�˺�Ϊ�գ�");
				JPanel dialogPanel=new JPanel();
				dialogPanel.add(nullJLabel);
				JPanel d=new JPanel();
				d.add(new JLabel("���˺��ѵ�¼��"));
				warningDialog.add(dialogPanel);
				reLoginDialog.add(d);
				//����warning�����Ĳ���
				warning.setVisible(false);
		
				//��ʼ��
				InitFrame.initframecenter(loginFrame, 405, 305);
		
				//������ɫ
				loginFrame.setBackground(new Color(255,255,255));
				centerPanel.setBackground(new Color(227,244,254));

				loginFrame.add(LoginPanel);

				//�ܲ���
				LoginPanel.add(northPanel,BorderLayout.NORTH);
				LoginPanel.add(southPanel,BorderLayout.SOUTH);
				LoginPanel.add(centerPanel,BorderLayout.CENTER);
		
		
				//���沼��
				northPanel.setLayout(new BorderLayout());
				//ͼƬ
				northPanel.add(pictureLabel);
			
				//���沼��
				southPanel.add(warning);
				southPanel.add(submit);
				southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
				southPanel.setBackground(new Color(191,225,250));
		

				//�м䲼��
				BorderLayout borderLayout=new BorderLayout();
				centerPanel.setLayout(borderLayout);
				borderLayout.setVgap(10);
				JPanel northone=new JPanel(new BorderLayout());
				//��һ��
				JPanel rowone=new JPanel(new FlowLayout(FlowLayout.CENTER));
				rowone.setBackground(new Color(227,244,254));
				rowone.add(usernamelabel);
				rowone.add(usernameField);
				rowone.add(messageoneLabel);
				//�ڶ���
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
		
		
		
			//ע�������
			public void addListener(){
					//���ڼ�����
					loginFrame.addWindowListener(new WindowAdapter() {

							public void windowClosing(WindowEvent e) {
								System.exit(-1);			}

					});
		        
					//��ť������
					submit.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							//��֤����
							try {
									
								username=usernameField.getText()==null?"":usernameField.getText().trim();
								password=passwordField.getText()==null?"":passwordField.getText().trim();
									
									if(username.length()==0){
									warningDialog.setVisible(true);
									}
									
									
									else if(username.length()!=0)
									{
									//�������������Ϣ
									ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
									String content=username+":"+password;
									Message message=new Message(content,0);
									oos.writeObject(message);
									System.out.println("�û������뷢�͸��˷�����!!!");
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
