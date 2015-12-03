package lzsdodo.oop.finalproject;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame implements ActionListener{
	
	private static final long 	serialVersionUID = 1L;

	final String 	TITLE 			= "Zachary's final project for OOP.";
	
	MyPanel 		mainPanel;		// 主容器；
	CardLayout 		cardLayout;		// 卡片布局；
	PanelInfo 		pnlInfo; 	
	PanelEquation 	pnlEquation;				
	PanelChart 		pnlChart;
	PanelFile		pnlFile; 
	PanelDBS		pnlDBS; 
	
	public MainFrame() {
		// ********** 菜单栏 设置 **********	
		JMenuBar 	mb 			= new JMenuBar();							// 初始化 菜单
		JMenu		mInfo		= new JMenu("Info");						// 初始化 菜单项
		JMenu		mFunc 		= new JMenu("Func");						
		JMenuItem	miEquation 	= new JMenuItem("Calculating Equation");	// 初始化 菜单项子项	
		JMenuItem	miChart		= new JMenuItem("Paint Chart");	
		JMenuItem	miFile		= new JMenuItem("Save/Copy Files");
		JMenuItem	miDBS		= new JMenuItem("Read/Write DBS");
		JMenuItem	miInfo		= new JMenuItem("Information");				
		
		mb.add(mInfo);														// 添加 菜单项
		mb.add(mFunc);
		mFunc.add(miEquation);												// 添加 菜单项子项
		mFunc.add(miChart);
		mFunc.add(miFile);
		mFunc.add(miDBS);
		mInfo.add(miInfo);
		
		miInfo.addActionListener(this);										// 注册 菜单项子项 点击事件
		miEquation.addActionListener(this);					
		miChart.addActionListener(this);
		miFile.addActionListener(this);
		miDBS.addActionListener(this);

		miInfo.setActionCommand("ePageInfo");								// 标识 菜单项子项 事件
		miEquation.setActionCommand("ePageEquation");			
		miChart.setActionCommand("ePageChart");
		miFile.setActionCommand("ePageFile");
		miDBS.setActionCommand("ePageDBS");
		
		// 添加 快捷键 alt + 1
		miInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));				
		miEquation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));	
		miChart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		miFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		miDBS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
	
		this.setJMenuBar(mb);												// 添加 菜单栏
		
		// ********** mainPanel 设置 **********
		cardLayout 			= new CardLayout(0, 0);					// 初始化 卡片布局 水平和垂直间隙 0, 0	 
		mainPanel 			= new MyPanel(cardLayout); 				// 初始化 主容器 并 设置 主容器 卡片布局
		mainPanel.setSize(WIDTH, HEIGHT);							// 设置 主容器 大小	
		this.pnlInfo 		= new PanelInfo();						// 初始化 各次级容器
		this.pnlEquation 	= new PanelEquation();				
		this.pnlChart 		= new PanelChart(cardLayout, mainPanel);
		this.pnlFile 		= new PanelFile();
		this.pnlDBS 		= new PanelDBS();
		
		MyButton btnNxtEquation = new MyButton("进入程序", 	Constant.BUTTON_X2, Constant.BUTTON_Y);
		MyButton btnNxtChart 	= new MyButton("进入绘图页", 	Constant.BUTTON_X2, Constant.BUTTON_Y);
		MyButton btnRtnEquation = new MyButton("返回方程页", 	Constant.BUTTON_X1, Constant.BUTTON_Y);
		MyButton btnNxtFile 	= new MyButton("进入文件页", 	Constant.BUTTON_X2, Constant.BUTTON_Y);
		MyButton btnRtnChart 	= new MyButton("返回绘图页", 	Constant.BUTTON_X1, Constant.BUTTON_Y);
		MyButton btnNxtDBS 		= new MyButton("进入数据页", 	Constant.BUTTON_X2, Constant.BUTTON_Y);
		MyButton btnRtnFile 	= new MyButton("返回文件页", 	Constant.BUTTON_X1, Constant.BUTTON_Y);
		MyButton btnClose 		= new MyButton("关闭页面", 	Constant.BUTTON_X2, Constant.BUTTON_Y);
		
		// 按钮添加监听器与标志
		btnNxtEquation.addActionListener(this);			btnNxtEquation.setActionCommand("ePageEquation");
		btnNxtChart.addActionListener(this);			btnNxtChart.setActionCommand("ePageChart");
		btnRtnEquation.addActionListener(this);			btnRtnEquation.setActionCommand("ePageEquation");
		btnNxtFile.addActionListener(this);				btnNxtFile.setActionCommand("ePageFile");
		btnRtnChart.addActionListener(this);			btnRtnChart.setActionCommand("ePageChart");
		btnNxtDBS.addActionListener(this);				btnNxtDBS.setActionCommand("ePageDBS");
		btnRtnFile.addActionListener(this);				btnRtnFile.setActionCommand("ePageFile");
		btnClose.addActionListener(this);				btnClose.setActionCommand("eClose");
		
		// 添加 按钮 到 各页面
		this.pnlInfo.add(btnNxtEquation);						
		this.pnlEquation.add(btnNxtChart);						
		this.pnlChart.add(btnRtnEquation);
		this.pnlChart.add(btnNxtFile);
		this.pnlFile.add(btnRtnChart);
		this.pnlFile.add(btnNxtDBS);
		this.pnlDBS.add(btnRtnFile);
		this.pnlDBS.add(btnClose);
		
		// 添加 次级容器 指定名字 到主容器中
		this.mainPanel.add(pnlInfo, "pageInfo");				
		this.mainPanel.add(pnlEquation, "pageEquation");		
		this.mainPanel.add(pnlChart, "pageChart");
		this.mainPanel.add(pnlFile, "pageFile");
		this.mainPanel.add(pnlDBS, "pageDBS");
		
		// ********** mainFrame 设置 **********
		this.getContentPane().add(mainPanel);						// 添加 主容器
		this.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);	// 设置 大小
		this.setTitle(TITLE);										// 设置 标题
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 添加 关闭事件
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "ePageInfo":
				cardLayout.show(mainPanel, "pageInfo");
				break;
			case "ePageEquation":			
				cardLayout.show(mainPanel, "pageEquation");
				break;
			case "ePageChart":
				cardLayout.show(mainPanel, "pageChart");
				PanelChart.isClicked = false;
				break;
			case "ePageFile":
				cardLayout.show(mainPanel, "pageFile");
				break;
			case "ePageDBS":
				cardLayout.show(mainPanel, "pageDBS");
				break;
			case "eClose":
				System.exit(0);
				break;
			default:
				break;
		}
	}
	
}
