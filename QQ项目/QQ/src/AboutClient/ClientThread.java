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
	//读到的内容
	private Message message;


	//账号
	private String username;
	
	private JLabel warning;

	
	private JDialog reLoginDialog;
	
	//list集合
	private  GuiFriendsList friendsList;
	
	//记录客户端的username和port的map集合 
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
		 				//内容
		 				String content=message.getContent();
		 				//flag
		 				int flag=message.getFlag();

		 				//登录验证
		 				if(flag==0){
		 					String[] s=content.split(":");
		 					String returnusername=s[0];
		 					String loginflag=s[1];
		 					if(loginflag.equals("true")){
		 						//向服务器发送5
		 						ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		 						username=returnusername;
		 						oos.writeObject(new Message(username,5));
		 						oos.flush();
		 						loginFrame.setVisible(false);
		 						friendsList =new GuiFriendsList(username,socket);
		 						friendsList.setUserJLabel(username);
		 					}
		 			
		 					//登陆不成功
		 					else if(loginflag.equals("false")){
		 						warning.setVisible(true);
		 					//重复登录
		 					}else if(loginflag.equals("relogin")){
		 						reLoginDialog.setVisible(true);
		 					}
		 				}
		 				
		 				
		 				
		 				//私聊
		 				else if(flag==1){
		 				//123:jack:11111111111
		 				String[] s=content.split(":");
		 				//发送人
		 				String sendname=s[0];
		 				int portA=clientMap.get(sendname);
		 				//账号
		 				String receivename=s[1];
		 				String words=s[2];
		 				HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
		 				//判断窗口有没被打开
		 					if(userWindow.containsKey(sendname)){
		 						userWindow.get(sendname).getTalkArea().append(sendname+"说"+words+"\n");
		 					}else{
		 						GuiClient guiClient=new GuiClient(socket, receivename, sendname, portA);
		 						userWindow.put(sendname, guiClient);
		 						guiClient.getTalkArea().append(sendname+"说"+words+"\n");
		 					
		 					}
		 				}
		 				
		 				
		 				
		 				else if(flag==2){
		 					//123:ALL:11111111111
		 					String[] s=content.split(":");
			 				//发送人
			 				String sendname=s[0];
			 				int portA=clientMap.get(sendname);
			 				//账号
			 				String receivename=s[1];
			 				String words=s[2];
			 				HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
			 				//判断窗口有没被打开
			 					if(userWindow.containsKey("ALL")){
			 						userWindow.get("ALL").getTalkArea().append(sendname+"说"+words+"\n");
			 					}else{
			 						GuiClient guiClient=new GuiClient(socket, username, "ALL", 0);
			 						userWindow.put("ALL", guiClient);
			 						guiClient.getTalkArea().append(sendname+"说"+words+"\n");
			 					
			 					}
		 				}
		 				
		 				
		 				
		 				
		 				else if(flag==3){
		 					
		 				}
		 				
		 				
		 				//jack:123:抖屏消息
		 				//ack:ALL:抖屏消息
		 				else if(flag==4){
		 					//抖屏消息  私人抖屏
		 					String[] s=content.split(":");
			 				//发送人
			 				String sendname=s[0];
			 				int portA=clientMap.get(sendname);
			 				//账号
			 				String receivename=s[1];
			 				String words=s[2];
			 					//判断是私人，还是群聊
			 					if(receivename.equals("ALL")==false){
			 					HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
			 					//判断窗口有没被打开
			 						if(userWindow.containsKey(sendname)){
				 						JFrame f=userWindow.get(sendname).getF();
				 						//抖屏
				 						Shack.shack(f);
				 					}else{
				 						GuiClient guiClient=new GuiClient(socket, receivename, sendname, portA);
				 						userWindow.put(sendname, guiClient);
				 						JFrame f=guiClient.getF();
				 						//抖屏
				 						Shack.shack(f);
				 					
				 					}
			 						//群聊抖屏
			 					}else{
			 						HashMap<String, GuiClient> userWindow=friendsList.getUserWindow();
					 				//判断窗口有没被打开
					 					if(userWindow.containsKey("ALL")){
					 						JFrame f=userWindow.get("ALL").getF();
					 						//抖屏
					 						Shack.shack(f);
					 					}else{
					 						GuiClient guiClient=new GuiClient(socket, username, "ALL", 0);
					 						userWindow.put("ALL", guiClient);
					 						JFrame f=guiClient.getF();
					 						//抖屏
					 						Shack.shack(f);
					 					
					 					}
			 					}
		 				}
			 						
			 				
		 				
		 				
		 				//读的是map集合
		 				else if(flag==5&&content==null){
		 					clientMap=message.getClientMap();
		 					System.out.println("客户端收到好友列表!!!");
		 					//获取好友列表的集合
		 					DefaultListModel friendslistModel=friendsList.getFriendslistModel();
		 					//添加好友列表
		 					//先清空model
		 					friendslistModel.clear();
		 					friendslistModel.addElement("ALL");
		 					Set<Entry<String, Integer>> entry=clientMap.entrySet();
		 					Iterator<Entry<String, Integer>> it=entry.iterator();
		 						while(it.hasNext()){
		 							Entry<String, Integer> element=it.next();
		 							if(element.getKey().equals(username)==false){
		 								friendslistModel.addElement("账号:"+element.getKey()+"    "+"端口号:"+element.getValue());
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
