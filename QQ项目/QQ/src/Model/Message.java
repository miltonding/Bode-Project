package Model;

import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//封装消息的类
public class Message implements Serializable {
		private String content;
		 //Socket不能被序列化
		 //记录客户端的username和port的map集合 
		 private  HashMap<String, Integer> clientMap=new HashMap<String, Integer>();
		 
		 private int flag;
		
		//port username的map映射
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
		
		
	
		 	//ClientMap的get set方法
		 	public HashMap getClientMap() {
		 		return clientMap;
		 	}



		 	public void setClientMap(HashMap clientMap) {
		 		this.clientMap = clientMap;
		 	}


		 	//content的get set方法
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
