package ToolPackage;

import javax.swing.JFrame;

public class Shack {
	 //抖屏函数
	  public static void shack(JFrame f){
		  int x=f.getX();
		  int y=f.getY();
		  //改变坐标
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
					//让线程睡眠一会
					try {
						Thread.sleep(50);
					} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}//for循环结束
	  }
	  
}
