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
	//��¼�ͻ��˵Ķ˿ںź�socket��map����
	private  HashMap<Integer, Socket> socketMap=new HashMap<Integer, Socket>();
	 
	//Socket���ܱ����л�
	//��¼�ͻ��˵�username��port��map���� 
	private  HashMap<String, Integer> clientMap=new HashMap<String, Integer>();
	 
	//���ܵ�����Ϣ
	private Message message;
	 
	private int flag;
	
	//���ʿͻ��ˣ��޸Ŀͻ��˵�����
		public ServerReadThread(Socket socket, JTextArea j1,HashMap socketMap,HashMap clientMap) {
			this.socket = socket;
			this.j1 = j1;
			this.socketMap=socketMap;
			this.clientMap=clientMap;
		}

   		public void run() {
   			//������
   			String content=null;
   			//����
   			ObjectInputStream ois=null;
   			boolean newflag=true;
   				try {
   					while(newflag){
   						System.out.println("1111111111111111111");
   						//����������
   						ois = new ObjectInputStream(socket.getInputStream());
   						System.out.println("222222222222222222222");
   						//��ȡ�ͻ��˵�ip�Ͷ˿ں�
   						InetSocketAddress add=(InetSocketAddress)socket.getRemoteSocketAddress();
   						String ip=add.getAddress().getHostAddress();
   						int port=add.getPort();
   						//��ȡ����
   						message = (Message)ois.readObject();
   						//��ȡ����
   						content=message.getContent();
   						//��ȡflag
   						flag=message.getFlag();
   						System.out.println("����������"+content);
   						j1.append("ip:"+ip+"port:"+port+"    ˵:"+content+"\n");	
   					
   						//����socketMap ��ClientMap����
   					
   							//��֤��¼��Ϣ
   							if(socketMap.get(port)==null&&flag==0){
   								String[] s=content.split(":");
   								String username=s[0];
   								String password=s[1];
   								//�ų��ظ���¼
   								if(clientMap.containsKey(username)==false){
   								//�������ļ�����֤��Ϣ
   									System.out.println("��������ʼ�������ļ�!!!!!!!");
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
   									System.out.println("��־Ϊ����������������������������"+lofginFlag);
   									//��֤�ɹ�
   									if(lofginFlag==true){
   										//���͸���������֤��Ϣ
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
   									//�ظ���¼
   									ObjectOutputStream flagoos=new ObjectOutputStream(socket.getOutputStream());
										String flagcontent=username+":relogin";
										flagoos.writeObject(new Message(flagcontent,0));
										flagoos.flush();
   									}
   							}
   							else if(flag==5){
   								//���ͺ����б�
   								String username=content;
			   						socketMap.put(port, socket);
			   						System.out.println("�ɹ���ӵ�socketMap");
			   						clientMap.put(username,port);
			   						System.out.println("�ɹ���ӵ�ClientMap");
			   							
			   						//����������Ⱥ�����ͻ���
			   						//����socketMap
			   						Set<Entry<Integer, Socket>> set=socketMap.entrySet();
			   						Iterator<Entry<Integer, Socket>> it=set.iterator();
			   							while(it.hasNext()){
			   									Entry<Integer, Socket> entry=it.next();
			   									//���ߵ�socket
			   									Socket liveSocket= entry.getValue();
			   									//ÿ����һ�� ����д��һ��
			   									ObjectOutputStream oos=new ObjectOutputStream(liveSocket.getOutputStream());
			   									//д���ͻ��� �˿ںź��ǳƵļ���   
			   									//�������ߺ����б�
			   									Message message2=new Message(clientMap,5);
			   									oos.writeObject(message2);
			   									oos.flush();
			   								}
			   								System.out.println("���͵�����������.........");
   							}
   					
   					
   							//���ͺ����б�
   							
   					
   					
   							//2222�����ߺ��ѣ�˽��
   							else if(socketMap.get(port)!=null&&flag==1){
   								String[] s=content.split(":");
   								//������
   								String username=s[0];
   								//�ҵ��˿ں�
   								int portA=clientMap.get(username);
   								//������
   								String receivename=s[1];
   								//�ҵ��˿ں�
   								int portB=clientMap.get(receivename);
   								//����
   								String content2=s[2];
   								//��ý����˵�socket
   								Socket socket=socketMap.get(portB);
   								ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
   								oos.writeObject(message);
   								System.out.println("��������Ϊ:"+content2);
   								System.out.println("������ת����Ϣ�ɹ�!!!!!!!!!!!!!!!!!!!!");
   								oos.flush();
   							}
   							//Ⱥ��
   							else if(socketMap.get(port)!=null&&flag==2){
   								Socket socket2=null;
   								String[] s=content.split(":");
   								//������
   								String username=s[0];
   								//�ҵ��˿ں�
   								int portA=clientMap.get(username);
   								//����
   								String content2=s[2];
   								Set<Entry<Integer, Socket>>  set=socketMap.entrySet();
   								Iterator< Entry<Integer, Socket>> it=set.iterator();
   									while(it.hasNext()){
   										Entry<Integer, Socket> e=it.next();
   										//�ų��Լ���socket
   											if(e.getKey()!=portA){
   												socket2=e.getValue();
   												ObjectOutputStream oos=new ObjectOutputStream(socket2.getOutputStream());
   												oos.writeObject(message);
   												System.out.println("Ⱥ��allallallallallallת������?????????????");
   												oos.flush();
   											}
   									}
   							}
   							//����
   							else if(flag==3){
   								int portA=clientMap.get(content);
   								clientMap.remove(content);
   								socketMap.remove(portA);
   								//1111����������Ⱥ�����ͻ���
   								//����socketMap
   								Set<Entry<Integer, Socket>> set=socketMap.entrySet();
   								Iterator<Entry<Integer, Socket>> it=set.iterator();
   								while(it.hasNext()){
   									Entry<Integer, Socket> entry=it.next();
   									//���ߵ�socket
   									Socket liveSocket= entry.getValue();
   									//ÿ����һ�� ����д��һ��
   									ObjectOutputStream oos=new ObjectOutputStream(liveSocket.getOutputStream());
   									//д���ͻ��� �˿ںź��ǳƵļ���   
   									//�������ߺ����б�
   									Message message2=new Message(clientMap,5);
   									oos.writeObject(message2);
   									oos.flush();
   									//�ر��߳�
   									newflag=false;
   								}
   							}
   							//������Ϣ
   							else if(flag==4){
   								System.out.println("�������յ�����1111111111111111111111");
   								String[] s=content.split(":");
   								//������
   								String username=s[0];
   								//������
   								String receivename=s[1];
								//˽�Ķ���
   									if(receivename.equals("ALL")==false){
   										//�ҵ��˿ں�
   										int portB=clientMap.get(receivename);
   										//����
   										String content2=s[2];
   										//��ý����˵�socket
   										Socket socket=socketMap.get(portB);
   										ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
   										oos.writeObject(message);
   										System.out.println("��������Ϊ:"+content2);
   										System.out.println("������ת����Ϣ�ɹ�!!!!!!!!!!!!!!!!!!!!");
   										oos.flush();
   									}
   									//Ⱥ�Ķ���
   									else if(receivename.equals("ALL")==true){
   										Socket socket2=null;
   										//�ҵ��˿ں�
   										int portA=clientMap.get(username);
   										//����
   										String content2=s[2];
   										Set<Entry<Integer, Socket>>  set=socketMap.entrySet();
   										Iterator< Entry<Integer, Socket>> it=set.iterator();
   											while(it.hasNext()){
   												Entry<Integer, Socket> e=it.next();
   												//�ų��Լ���socket
   												if(e.getKey()!=portA){
   													socket2=e.getValue();
   													ObjectOutputStream oos=new ObjectOutputStream(socket2.getOutputStream());
   													oos.writeObject(message);
   													System.out.println("Ⱥ������111111111111111111111111111111111");
   													oos.flush();
   												}
   											}
   									}
   							}
   					
   						}//while����
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



