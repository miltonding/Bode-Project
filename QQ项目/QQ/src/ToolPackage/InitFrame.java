package ToolPackage;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
//��frame��ʾ����Ļ����
public class InitFrame {
	public static void  initframecenter(JFrame f,int width,int height){
		f.setVisible(true);
		f.setResizable(false);
		 //���ô�������
		Toolkit toolkit=Toolkit.getDefaultToolkit();//��ȡһ����ϵͳ��ع��������
			
		//��ȡ��Ļ�ֱ���
		Dimension dimension=toolkit.getScreenSize();
			
		int x=(int )dimension.getWidth();
		int y=(int)dimension.getHeight();
			
		f.setBounds((x-width)/2,(y-height)/2,width,height);
			
	}

			
	public static void initframeright(JFrame f,int width,int height){
		f.setVisible(true);
		f.setResizable(false);
		//���ô�������
		Toolkit toolkit=Toolkit.getDefaultToolkit();//��ȡһ����ϵͳ��ع��������
			
		//��ȡ��Ļ�ֱ���
		Dimension dimension=toolkit.getScreenSize();
			
		int x=(int )dimension.getWidth();
		int y=(int)dimension.getHeight();
			
		f.setBounds((x-width-30),30,width,height);
	}
	
	public static void  initDialogcenter(JDialog j,int width,int height){
		j.setResizable(false);
		//���ô�������
		Toolkit toolkit=Toolkit.getDefaultToolkit();//��ȡһ����ϵͳ��ع��������
			
		//��ȡ��Ļ�ֱ���
		Dimension dimension=toolkit.getScreenSize();
			
		int x=(int )dimension.getWidth();
		int y=(int)dimension.getHeight();
			
		j.setBounds((x-width)/2,(y-height)/2,width,height);
			
	}
	
		
}
