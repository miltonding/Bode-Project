package AboutServer;

import Model.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JTextArea;

public class ServerReadThread  extends Thread{
	private Socket socket=null;
	private JTextArea j1;
	//记录客户端的端口号和socket的map集合
	private  HashMap<Integer, Socket> socketMap=new HashMap<Integer, Socket>();
	 
	//Socket不能被序列化
	//记录客户端的username和port的map集合 
	private  HashMap<String, Integer> clientMap=new HashMap<String, Integer>();
	 
	//接受到的消息
	private Message message;
	 
	private int flag;
	
	//访问客户端，修改客户端的属性
		public ServerReadThread(Socket socket, JTextArea j1,HashMap socketMap,HashMap clientMap) {
			this.socket = socket;
			this.j1 = j1;
			this.socketMap=socketMap;
			this.clientMap=clientMap;
		}

   		public void run() {
   			//读数据
   			String content=null;
   			//内容
   			ObjectInputStream ois=null;
   			boolean newflag=true;
   				try {
   					while(newflag){
   						System.out.println("1111111111111111111");
   						//阻塞在这里
   						ois = new ObjectInputStream(socket.getInputStream());
   						System.out.println("222222222222222222222");
   						//获取客户端的ip和端口号
   						InetSocketAddress add=(InetSocketAddress)socket.getRemoteSocketAddress();
   						String ip=add.getAddress().getHostAddress();
   						int port=add.getPort();
   						//获取对象
   						message = (Message)ois.readObject();
   						//获取内容
   						content=message.getContent();
   						//获取flag
   						flag=message.getFlag();
   						System.out.println("服务器读到"+content);
   						j1.append("ip:"+ip+"port:"+port+"    说:"+content+"\n");	
   					
   						//更新socketMap 和ClientMap集合
   					
   							//验证登录消息
   							if(socketMap.get(port)==null&&flag==0){
   								String[] s=content.split(":");
   								String username=s[0];
   								String password=s[1];
   								//排除重复登录
   								if(clientMap.containsKey(username)==false){
   								//读配置文件，验证消息
   									System.out.println("服务器开始读配置文件!!!!!!!");
   									Properties p=new Properties();
   									p.load(new FileReader("user.properties"));
   									Set<Entry<Object, Object>> set=p.entrySet();
   									Iterator<Entry<Object, Object>> it=set.iterator();
   									boolean lofginFlag=false;
   										while(it.hasNext()){
   											Entry<Object, Object> e=it.next();
   											String fileusername=(String)e.getKey();
   											String filepassword=(String)e.getValue();
   												if(fileusername.equals(username)&&filepassword.equals(password)){
   													lofginFlag=true;
   													break;
   												}
   										}
   									System.out.println("标志为！！！！！！！！！！！！！！"+lofginFlag);
   									//验证成功
   									if(lofginFlag==true){
   										//发送给服务器验证消息
   										ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
   										String flagcontent=username+":true";
   										oos.writeObject(new Message(flagcontent,0));
   										oos.flush();

   										}else{
   										ObjectOutputStream flagoos=new ObjectOutputStream(socket.getOutputStream());
   										String flagcontent=username+":false";
   										flagoos.writeObject(new Message(flagcontent,0));
   										flagoos.flush();
   										}
   							
   									
   								
   								
   									}else{
   									//重复登录
   									ObjectOutputStream flagoos=new ObjectOutputStream(socket.getOutputStream());
										String flagcontent=username+":relogin";
										flagoos.writeObject(new Message(flagcontent,0));
										flagoos.flush();
   									}
   							}
   							else if(flag==5){
   								//发送好友列表
   								String username=content;
			   						socketMap.put(port, socket);
			   						System.out.println("成功添加到socketMap");
			   						clientMap.put(username,port);
			   						System.out.println("成功添加到ClientMap");
			   							
			   						//把在线名单群发给客户端
			   						//遍历socketMap
			   						Set<Entry<Integer, Socket>> set=socketMap.entrySet();
			   						Iterator<Entry<Integer, Socket>> it=set.iterator();
			   							while(it.hasNext()){
			   									Entry<Integer, Socket> entry=it.next();
			   									//在线的socket
			   									Socket liveSocket= entry.getValue();
			   									//每遍历一次 向外写出一次
			   									ObjectOutputStream oos=new ObjectOutputStream(liveSocket.getOutputStream());
			   									//写出客户端 端口号和昵称的集合   
			   									//发送在线好友列表
			   									Message message2=new Message(clientMap,5);
			   									oos.writeObject(message2);
			   									oos.flush();
			   								}
			   								System.out.println("发送的是在线名单.........");
   							}
   					
   					
   							//发送好友列表
   							
   					
   					
   							//2222是在线好友，私聊
   							else if(socketMap.get(port)!=null&&flag==1){
   								String[] s=content.split(":");
   								//发送人
   								String username=s[0];
   								//找到端口号
   								int portA=clientMap.get(username);
   								//接收人
   								String receivename=s[1];
   								//找到端口号
   								int portB=clientMap.get(receivename);
   								//内容
   								String content2=s[2];
   								//获得接收人的socket
   								Socket socket=socketMap.get(portB);
   								ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
   								oos.writeObject(message);
   								System.out.println("聊天内容为:"+content2);
   								System.out.println("服务器转发消息成功!!!!!!!!!!!!!!!!!!!!");
   								oos.flush();
   							}
   							//群聊
   							else if(socketMap.get(port)!=null&&flag==2){
   								Socket socket2=null;
   								String[] s=content.split(":");
   								//发送人
   								String username=s[0];
   								//找到端口号
   								int portA=clientMap.get(username);
   								//内容
   								String content2=s[2];
   								Set<Entry<Integer, Socket>>  set=socketMap.entrySet();
   								Iterator< Entry<Integer, Socket>> it=set.iterator();
   									while(it.hasNext()){
   										Entry<Integer, Socket> e=it.next();
   										//排除自己的socket
   											if(e.getKey()!=portA){
   												socket2=e.getValue();
   												ObjectOutputStream oos=new ObjectOutputStream(socket2.getOutputStream());
   												oos.writeObject(message);
   												System.out.println("群发allallallallallall转发了吗?????????????");
   												oos.flush();
   											}
   									}
   							}
   							//下线
   							else if(flag==3){
   								int portA=clientMap.get(content);
   								clientMap.remove(content);
   								socketMap.remove(portA);
   								//1111把在线名单群发给客户端
   								//遍历socketMap
   								Set<Entry<Integer, Socket>> set=socketMap.entrySet();
   								Iterator<Entry<Integer, Socket>> it=set.iterator();
   								while(it.hasNext()){
   									Entry<Integer, Socket> entry=it.next();
   									//在线的socket
   									Socket liveSocket= entry.getValue();
   									//每遍历一次 向外写出一次
   									ObjectOutputStream oos=new ObjectOutputStream(liveSocket.getOutputStream());
   									//写出客户端 端口号和昵称的集合   
   									//发送在线好友列表
   									Message message2=new Message(clientMap,5);
   									oos.writeObject(message2);
   									oos.flush();
   									//关闭线程
   									newflag=false;
   								}
   							}
   							//抖屏消息
   							else if(flag==4){
   								System.out.println("服务器收到抖屏1111111111111111111111");
   								String[] s=content.split(":");
   								//发送人
   								String username=s[0];
   								//接收人
   								String receivename=s[1];
								//私聊抖动
   									if(receivename.equals("ALL")==false){
   										//找到端口号
   										int portB=clientMap.get(receivename);
   										//内容
   										String content2=s[2];
   										//获得接收人的socket
   										Socket socket=socketMap.get(portB);
   										ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
   										oos.writeObject(message);
   										System.out.println("聊天内容为:"+content2);
   										System.out.println("服务器转发消息成功!!!!!!!!!!!!!!!!!!!!");
   										oos.flush();
   									}
   									//群聊抖动
   									else if(receivename.equals("ALL")==true){
   										Socket socket2=null;
   										//找到端口号
   										int portA=clientMap.get(username);
   										//内容
   										String content2=s[2];
   										Set<Entry<Integer, Socket>>  set=socketMap.entrySet();
   										Iterator< Entry<Integer, Socket>> it=set.iterator();
   											while(it.hasNext()){
   												Entry<Integer, Socket> e=it.next();
   												//排除自己的socket
   												if(e.getKey()!=portA){
   													socket2=e.getValue();
   													ObjectOutputStream oos=new ObjectOutputStream(socket2.getOutputStream());
   													oos.writeObject(message);
   													System.out.println("群发抖屏111111111111111111111111111111111");
   													oos.flush();
   												}
   											}
   									}
   							}
   					
   						}//while结束
   					} catch (IOException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
   					catch (ClassNotFoundException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   					
   					
   					}
   			}
			
		     
		}



