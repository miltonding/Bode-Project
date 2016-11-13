package ToolPackage;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
//让frame显示在屏幕中央
public class InitFrame {
	public static void  initframecenter(JFrame f,int width,int height){
		f.setVisible(true);
		f.setResizable(false);
		 //设置窗口属性
		Toolkit toolkit=Toolkit.getDefaultToolkit();//获取一个与系统相关工具类对象
			
		//获取屏幕分辨率
		Dimension dimension=toolkit.getScreenSize();
			
		int x=(int )dimension.getWidth();
		int y=(int)dimension.getHeight();
			
		f.setBounds((x-width)/2,(y-height)/2,width,height);
			
	}

			
	public static void initframeright(JFrame f,int width,int height){
		f.setVisible(true);
		f.setResizable(false);
		//设置窗口属性
		Toolkit toolkit=Toolkit.getDefaultToolkit();//获取一个与系统相关工具类对象
			
		//获取屏幕分辨率
		Dimension dimension=toolkit.getScreenSize();
			
		int x=(int )dimension.getWidth();
		int y=(int)dimension.getHeight();
			
		f.setBounds((x-width-30),30,width,height);
	}
	
	public static void  initDialogcenter(JDialog j,int width,int height){
		j.setResizable(false);
		//设置窗口属性
		Toolkit toolkit=Toolkit.getDefaultToolkit();//获取一个与系统相关工具类对象
			
		//获取屏幕分辨率
		Dimension dimension=toolkit.getScreenSize();
			
		int x=(int )dimension.getWidth();
		int y=(int)dimension.getHeight();
			
		j.setBounds((x-width)/2,(y-height)/2,width,height);
			
	}
	
		
}
