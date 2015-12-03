package lzsdodo.oop.finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel  extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public MyPanel() { 
		super(); 
	}
	public MyPanel(LayoutManager layoutManager) { 
		super(layoutManager);
	}
	public MyPanel(int width, int height) { 
		super(); 
		this.setSize(width, height);
	}
	public MyPanel(String strLblTitle) {
		super();
		// 初始化
		MyLabel lblTitle = new MyLabel(strLblTitle, MyLabel.CHINESE, 
				Constant.SUBHEAD_WIDTH, Constant.SUBHEAD_HEIGHT, 
				Constant.SUBHEAD_X, Constant.SUBHEAD_Y);
		lblTitle.setForeground(new Color(0x23, 0x23, 0x8e));	// 设置 字体颜色 海军蓝 #23238E
		lblTitle.setFont(new Font("楷体", Font.BOLD, 20));		// 设置 字体样式 楷体 粗体 18字号
		this.add(lblTitle);	

		this.setBackground(null);				// 设置 无背景 （与下句重复）
		this.setOpaque(false);					// 设置 透明
		this.setLayout(null);					// 设置 手动设置位置
		this.setVisible(true);					// 设置 可见
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon bgpImg = new ImageIcon(getClass().getResource("/lzsdodo/oop/images/javabgp.png"));
		g.drawImage(bgpImg.getImage(), 0, 0, Constant.FRAME_WIDTH, Constant.CONTENT_HEIGHT, Color.WHITE, this);
	}
	
}
