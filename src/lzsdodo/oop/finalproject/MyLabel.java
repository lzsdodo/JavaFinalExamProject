package lzsdodo.oop.finalproject;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	final Font chnFont = new Font("楷体", Font.PLAIN, 14);
	final Font engFont = new Font("Courier", Font.PLAIN, 14);

	static final boolean CHINESE = true;
	static final boolean ENGLISH = true;
	
	public MyLabel() {
		super();
		this.setForeground(Color.BLACK);							// 默认 字体颜色 黑色
		this.setHorizontalAlignment(SwingConstants.LEFT);			// 默认 左对齐
		this.setVerticalAlignment(SwingConstants.CENTER);			// 默认 向上对齐
		this.setSize(Constant.TEXT_WIDTH, Constant.TEXT_HEIGHT);	// 默认 大小 100*20
	}
	public MyLabel(String string, boolean isChinese) {
		super(string);
		if (isChinese)	this.setFont(chnFont);	
		else			this.setFont(engFont);
		this.setForeground(Color.BLACK);					
		this.setHorizontalAlignment(SwingConstants.LEFT);	
		this.setVerticalAlignment(SwingConstants.CENTER);		
		this.setSize(Constant.TEXT_WIDTH, Constant.TEXT_HEIGHT);	
	}
	public MyLabel(String string, boolean isChinese , int x, int y) {
		super(string);
		if (isChinese)	this.setFont(chnFont);	
		else			this.setFont(engFont);
		this.setForeground(Color.BLACK);					
		this.setHorizontalAlignment(SwingConstants.LEFT);	
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setSize(Constant.TEXT_WIDTH, Constant.TEXT_HEIGHT);	
		this.setLocation(x, y);										// 设置 位置
	}
	public MyLabel(String string, boolean isChinese , int width, int height, int x, int y) {
		super(string);
		if (isChinese)	this.setFont(chnFont);	
		else			this.setFont(engFont);
		this.setForeground(Color.BLACK);					
		this.setHorizontalAlignment(SwingConstants.LEFT);	
		this.setVerticalAlignment(SwingConstants.CENTER);		
		this.setSize(width, height);								// 设置 大小
		this.setLocation(x, y);										// 设置 位置
	}
}
