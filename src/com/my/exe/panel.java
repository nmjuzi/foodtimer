package com.my.exe;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class panel extends WindowAdapter implements ActionListener,
		ItemListener
// 定义事件处理类，由它对按钮和框架上的窗口产生的事件进行处理
{

	JFrame f; // 框架对象
	JButton btnOk;
	JButton btnCopy;
	JButton btnCut;
	JTextField hourField;
	JTextField minuteField;
	JTextField msgField;
	JTextField tokenField;
	JLabel cardLabel;
	JLabel tsLabel;
	JLabel tsLabel1;
	JLabel tsLabel2;
	JLabel tsLabel3;
	JLabel tsLabel4;
	JLabel tsLabel5;
	Clipboard clipbd;

	public panel() // 构造窗口界面

	{
		clipbd = Toolkit.getDefaultToolkit().getSystemClipboard();
		hourField = new JTextField("");
		hourField.setFont(new Font("宋体",Font.BOLD,20));
		hourField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
					
				}else{
					e.consume(); //关键，屏蔽掉非法输入
				}
			}
		});
		minuteField = new JTextField("");
		minuteField.setFont(new Font("宋体",Font.BOLD,20));
		minuteField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){
					
				}else{
					e.consume(); //关键，屏蔽掉非法输入
				}
			}
		});
		msgField = new JTextField("");
		tokenField = new JTextField("");
		f = new JFrame("钉钉定时提醒工具"); // 创建带标题的框架

		f.setSize(300, 300); // 设置框架大小
		f.setResizable(false);
		f.setLocationRelativeTo(null); 

		JPanel p = new JPanel();
		p.setLayout(null);  
		f.add(p);

		btnOk = new JButton("启动");
		tsLabel = new  JLabel("小时:");
		tsLabel1 = new  JLabel("分钟:");
		tsLabel2 = new  JLabel("内容:");
		tsLabel3 = new  JLabel("WEBHOOK:");
		tsLabel4 = new  JLabel("一旦按下启动只有完全退出工具才能终止运行!");

		tsLabel.setBounds(5, 4, 30, 40);
		hourField.setBounds(38, 4, 100, 40);
		tsLabel1.setBounds(141, 4, 30, 40);
		minuteField.setBounds(174, 4, 100, 40);
		tsLabel2.setBounds(5, 45, 30, 40);
		msgField.setBounds(38, 45, 236, 40);
		tsLabel3.setBounds(5, 86, 100, 30);
		tokenField.setBounds(38, 114, 236, 40);
		tsLabel4.setBounds(5, 174, 290, 30);
		btnOk.setBounds(100, 204, 93, 35);
		// 创建按钮对象

		
		btnOk.addActionListener(new Start());
	
		
		p.add(tsLabel);
		p.add(hourField);
		p.add(tsLabel1);
		p.add(minuteField);
		p.add(tsLabel2);
		p.add(msgField);
		p.add(tsLabel3);
		p.add(tsLabel4);
		p.add(tokenField);
		p.add(btnOk); // 在框架中加入按钮
		
		

		f.setVisible(true);           // 设置框架为可见
		f.addWindowListener(this);    // 注册监听框架上的窗口事件

	}
	class Start implements ActionListener {           //生成按钮处理事件
	    public void actionPerformed(ActionEvent e) {
				 if(hourField.getText().trim().equals("")){
					 JOptionPane.showMessageDialog(null, "请输入小时，该数据不能为空", "提示",JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				 if(minuteField.getText().trim().equals("")){
					 JOptionPane.showMessageDialog(null, "请输入分钟，该数据不能为空", "提示",JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				 if(msgField.getText().trim().equals("")){
					 JOptionPane.showMessageDialog(null, "请输入要定时发送的消息", "提示",JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				 if(tokenField.getText().trim().equals("")){
					 JOptionPane.showMessageDialog(null, "请输入WEBHOOK值", "提示",JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				 int h = Integer.parseInt(hourField.getText());
				 int m = Integer.parseInt(minuteField.getText());
				 if (h>23 || h<0){
					 JOptionPane.showMessageDialog(null, "请输入正确数值,小时的取值范围在0-23之间", "提示",JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				 if (m>59 || m<0){
					 JOptionPane.showMessageDialog(null, "请输入正确数值，分钟的取值范围在0-59之间", "提示",JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				 final String msg = msgField.getText();
				 final String tokenmsg = tokenField.getText();
				 Calendar calendar = Calendar.getInstance();
			        calendar.set(Calendar.HOUR_OF_DAY, h);
			        calendar.set(Calendar.MINUTE, m);
			        calendar.set(Calendar.SECOND, 0);

			        Date time = calendar.getTime();

			        Timer timer = new Timer();
			        timer.scheduleAtFixedRate(new TimerTask() {
			            public void run() {
			            	try {
			            		ChatbotSend.botStart(tokenmsg,msg);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }
			        }, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
			        
			      //ChatbotSend.botStart();
					 System.out.println("h:"+h+"m:"+m);
					 System.out.println("msg:"+msg+"tokenmsg:"+tokenmsg);
			    }
				
	    }

	
	public static void main(String args[]){
		panel pp = new panel();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
	}

	public void windowClosing(WindowEvent e){// 处理窗口关闭事件
		System.exit(0); // 程序停止运行,关闭框架窗口
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}