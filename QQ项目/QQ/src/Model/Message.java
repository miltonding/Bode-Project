package Model;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//��װ��Ϣ����
public class Message implements Serializable {
		private String content;
		 //Socket���ܱ����л�
		 //��¼�ͻ��˵�username��port��map���� 
		 private  HashMap<String, Integer> clientMap=new HashMap<String, Integer>();
		 
		 private int flag;
		
		//port username��mapӳ��
		 	public Message(HashMap clientMap){
		 		this.clientMap=clientMap;
		 	}
		
		 	public Message(HashMap<String, Integer> clientMap, int flag) {
				super();
				this.clientMap = clientMap;
				this.flag = flag;
			}

			public Message(String content,int flag){
		 		this.content=content;
		 		this.flag=flag;
		 	}
		
		
	
		 	//ClientMap��get set����
		 	public HashMap getClientMap() {
		 		return clientMap;
		 	}



		 	public void setClientMap(HashMap clientMap) {
		 		this.clientMap = clientMap;
		 	}


		 	//content��get set����
		 	public String getContent() {
		 		return content;
		 	}
		 	public void setContent(String content) {
		 		this.content = content;
		 	}
		
		 	public int getFlag() {
		 		return flag;
		 	}


		 	public void setFlag(int flag) {
		 		this.flag = flag;
		 	}


		 	public String toString() {
			
		 		return content+flag;
		 	}

		
}
