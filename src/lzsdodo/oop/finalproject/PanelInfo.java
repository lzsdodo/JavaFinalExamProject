package lzsdodo.oop.finalproject;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PanelInfo extends MyPanel {
	
	private static final long serialVersionUID = 1L;
	
	public PanelInfo() {
		super("程序介绍");
		
		String string = new String();
		string = "<html>本程序主要分四个板块功能：<br/>"
				+ "<br/><br/>"
				+ "一、简谐振动方程："
				+ "<br/><br/>"
				+ "　　1、 输入方程参数； 2、输出方程函数值； 3、查看图表；"
				+ "<br/><br/><br/>"
				+ "二、绘图："
				+ "<br/><br/>"
				+ "　　1、绘制方程结果集图像；"
				+ "<br/><br/><br/>"
				+ "三、文件操作："
				+ "<br/><br/>"
				+ "　　1、保存数据文件； 2、拷贝文件；"
				+ "<br/><br/><br/>"
				+ "四、数据库操作："
				+ "<br/><br/>"
				+ "　　1、保存数据到数据库； 2、从数据库读取数据；"
				+ "<br/><br/><br/>"
				+ "本程序基本已覆盖绝大部分要求，希望老师直接以本次报告作为平时作业与实验的成绩参考依据。"
				+ "<br/>"
				+ "大连理工大学，管理与经济学部，双管信1502，201597151，梁子山。"
				+ "</html>";
		
		JLabel lblContent = new MyLabel(string, true, Constant.CONTENT_X, Constant.CONTENT_Y);		// 初始化
		lblContent.setSize(Constant.CONTENT_WIDTH, Constant.CONTENT_HEIGHT);						// 设置 大小
		lblContent.setVerticalAlignment(SwingConstants.TOP);										// 设置 顶端对齐
		this.add(lblContent);												
	}
}


/*
- 作业 03
	- 请同学们将无阻尼弹簧振动的模型利用面向对象方法封装成一个类。        
	- 要求：
    	1. 用户可以通过封装的方法设置模型的参数。      
    	2. 计算出给定时间间隔内弹簧振动的位移量数据，并将偏移量在屏幕输出。       

- 作业 04
	- 使用Java语言封装一个一元二次函数 y(x)=ax^2 + bx + c的类
	- 要求：
    	1. 从控制台输入（或者GUI界面文本输入控件输入）刻画函数的属性 a,b,c。    
    	2. 给定一个自变量x的值，输出一个函数值。  
    	3. 给定自变量x的区间值和离散化的数量，生成（x，y）一组数据对，并将数据用DataInputStream和DataOutpouStream保存到数据文件中。    
    	4. 打开数据文件在标准输出设备上打印输出。  
    	4. 对程序进行测试和验证。  
    	5. 书面作业要包含源程序，程序运行结果的截图。    

- 作业 05
	- 编写一个类似于DOS命令copy的Java命令行程序jcopy。      
    	- 使用的格式是：`java -jar jcopy.jar sfile dfile`      
    	- 其中 `sfile` 是源文件，`dfile` 是目标文件（副本文件）。      

- 本工程说明
		在方程中，包括：
			1. 封装方程的类， Model；
			2. GUI 界面输入相关参数；
			3. 给定变量，输出函数值；
			4. 给定自变量x的区间值和离散化的数量，生成（x，y）一组数据对；
			5. 绘图；
			6. 数据保存到文件中；
			7. 文件复制与保存； 
			8. 数据保存到数据库中；
	
*/