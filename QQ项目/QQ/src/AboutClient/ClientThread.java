package AboutClient;

import java.awt.Container;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.Message;
import ToolPackage.Shack;

public class ClientThread extends Thread{
	private Socket socket;
	private JFrame loginFrame;
	//����������
	private Message message;


	//�˺�
	private String username;
	
	private JLabel warning;

	
	private JDialog reLoginDialog;
	
	//list����
	private  GuiFriendsList friendsList;
	
	//��¼�ͻ��˵�username��port��map���� 
	private  HashMap<String, Integer> clientMap=new HashMap<String, Integer>();
	

		public ClientThread(Socket socket, JFrame loginFrame,
			JLabel warning,JDialog reLoginDialog) {
			super();
			this.socket = socket;
			this.loginFrame = loginFrame;
			this.warning=warning;
			this.reLoginDialog=reLoginDialog;
		}


		public void run() {
		 ObjectInputStream ois=null;
		 	while(true){
		 		try {
		 				ois=new ObjectInputStream(socket.getInputStream());
		 				 message=(Message)ois.readObject();
		 				//����
		 				String content=message.getContent();
		 				//flag
		 				int flag=message.getFlag();

		 				//��¼��֤
		 				if(flag==0){
		 					String[] s=content.split(":");
		 					String returnusername=s[0];
		 					String loginflag=s[1];
		 					if(loginflag.equals("true")){
		 						//�����������5
		 						ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		 						username=returnusername;
		 						oos.writeObject(new Message(username,5));
		 						oos.flush();
		 						loginFrame.setVisible(false);
		 						friendsList =new GuiFriendsList(username,socket);
		 						friendsList.setUserJLabel(username);
		 					}
		 			
		 					//��½���ɹ�
		 					else if(loginflag.equals("false")){
		 						warning.setVisible(true);
		 					//�ظ���¼
		 					}else if(loginflag.equals("relogin")){
		 						reLoginDialog.setVisible(true);
		 					}
		 				}
		 				
		 				
		 				
		 				//˽��
		 				else if(flag==1){
		 				//123:jack:11111111111
		 				String[] s=content.split(":");
		 				//������
		 				String sendname=s[0];
		 				int portA=clientMap.get(sendname);
		 				//�˺�
		 				String receivename=s[1];
		 				String words=s[2];
		 				HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
		 				//�жϴ�����û����
		 					if(userWindow.containsKey(sendname)){
		 						userWindow.get(sendname).getTalkArea().append(sendname+"˵"+words+"\n");
		 					}else{
		 						GuiClient guiClient=new GuiClient(socket, receivename, sendname, portA);
		 						userWindow.put(sendname, guiClient);
		 						guiClient.getTalkArea().append(sendname+"˵"+words+"\n");
		 					
		 					}
		 				}
		 				
		 				
		 				
		 				else if(flag==2){
		 					//123:ALL:11111111111
		 					String[] s=content.split(":");
			 				//������
			 				String sendname=s[0];
			 				int portA=clientMap.get(sendname);
			 				//�˺�
			 				String receivename=s[1];
			 				String words=s[2];
			 				HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
			 				//�жϴ�����û����
			 					if(userWindow.containsKey("ALL")){
			 						userWindow.get("ALL").getTalkArea().append(sendname+"˵"+words+"\n");
			 					}else{
			 						GuiClient guiClient=new GuiClient(socket, username, "ALL", 0);
			 						userWindow.put("ALL", guiClient);
			 						guiClient.getTalkArea().append(sendname+"˵"+words+"\n");
			 					
			 					}
		 				}
		 				
		 				
		 				
		 				
		 				else if(flag==3){
		 					
		 				}
		 				
		 				
		 				//jack:123:������Ϣ
		 				//ack:ALL:������Ϣ
		 				else if(flag==4){
		 					//������Ϣ  ˽�˶���
		 					String[] s=content.split(":");
			 				//������
			 				String sendname=s[0];
			 				int portA=clientMap.get(sendname);
			 				//�˺�
			 				String receivename=s[1];
			 				String words=s[2];
			 					//�ж���˽�ˣ�����Ⱥ��
			 					if(receivename.equals("ALL")==false){
			 					HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
			 					//�жϴ�����û����
			 						if(userWindow.containsKey(sendname)){
				 						JFrame f=userWindow.get(sendname).getF();
				 						//����
				 						Shack.shack(f);
				 					}else{
				 						GuiClient guiClient=new GuiClient(socket, receivename, sendname, portA);
				 						userWindow.put(sendname, guiClient);
				 						JFrame f=guiClient.getF();
				 						//����
				 						Shack.shack(f);
				 					
				 					}
			 						//Ⱥ�Ķ���
			 					}else{
			 						HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
					 				//�жϴ�����û����
					 					if(userWindow.containsKey("ALL")){
					 						JFrame f=userWindow.get("ALL").getF();
					 						//����
					 						Shack.shack(f);
					 					}else{
					 						GuiClient guiClient=new GuiClient(socket, username, "ALL", 0);
					 						userWindow.put("ALL", guiClient);
					 						JFrame f=guiClient.getF();
					 						//����
					 						Shack.shack(f);
					 					
					 					}
			 					}
		 				}
			 						
			 				
		 				
		 				
		 				//������map����
		 				else if(flag==5&&content==null){
		 					clientMap=message.getClientMap();
		 					System.out.println("�ͻ����յ������б�!!!");
		 					//��ȡ�����б�ļ���
		 					DefaultListModel friendslistModel=friendsList.getFriendslistModel();
		 					//��Ӻ����б�
		 					//�����model
		 					friendslistModel.clear();
		 					friendslistModel.addElement("ALL");
		 					Set<Entry<String, Integer>> entry=clientMap.entrySet();
		 					Iterator<Entry<String, Integer>> it=entry.iterator();
		 						while(it.hasNext()){
		 							Entry<String, Integer> element=it.next();
		 							if(element.getKey().equals(username)==false){
		 								friendslistModel.addElement("�˺�:"+element.getKey()+"    "+"�˿ں�:"+element.getValue());
		 							}
		 						}
		 						
		 				}
			
			
		 			} catch (IOException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			} catch (ClassNotFoundException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 			}
		 		
		
		 	}
		}
	}
