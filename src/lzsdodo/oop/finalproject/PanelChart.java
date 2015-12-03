package lzsdodo.oop.finalproject;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelChart extends MyPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
    protected MyPanel 	pnlChart;
    protected MyButton 	btnPaint;
    static boolean 		isClicked = false;
    
	public PanelChart(CardLayout cardLayout, MyPanel jPanel) {
		super("绘图");
		
		pnlChart = new MyPanel() {
			
			private static final long serialVersionUID = 1L;
			// 绘图宽高坐标
			private final int PAINT_WIDTH 	= 480;								// 480 * 320
			private final int PAINT_HEIGHT 	= 320;
			// x, y 轴坐标终点
			private final int XAXIS_START 	= 110;								// (110, 360)
			private final int YAXIS_START 	= 360;
			private final int XAXIS_END 	= XAXIS_START + PAINT_WIDTH;   
		    private final int YAXIS_END 	= YAXIS_START - PAINT_HEIGHT;
		    // 原点 绝对位置
		    private final int ORIGIN_OX 	= XAXIS_START;						// (110, 200)
			private final int ORIGIN_OY		= YAXIS_START - PAINT_HEIGHT / 2;
		    // x 轴坐标间隔
		    private int tInterval;								
		    
		    // System.out.println();
			public void paint(Graphics g) {
				Graphics2D g2D = (Graphics2D) g;  
				if (isClicked) {
					// 画边框  
					g.setColor(Color.BLACK);
					g2D.setStroke(new BasicStroke(Float.parseFloat("1f")));
					g.draw3DRect(0, 0, Constant.PAINT_WIDTH-1, Constant.PAINT_HEIGHT-1, true);	// (0, 0) (700-1, 400-1) 
					// 画坐标轴  
					g.setColor(Color.BLACK);  
					g2D.setStroke(new BasicStroke(Float.parseFloat("2f")));  
					// X轴及方向箭头  
					g.drawLine(ORIGIN_OX, 		ORIGIN_OY, 			XAXIS_END + 15, 	ORIGIN_OY);  
					g.drawLine(XAXIS_END + 15, 	ORIGIN_OY, 			XAXIS_END + 5, 		ORIGIN_OY - 5);  
					g.drawLine(XAXIS_END + 15, 	ORIGIN_OY, 			XAXIS_END + 5, 		ORIGIN_OY + 5);  
					// Y轴及方向箭头  
					g.drawLine(ORIGIN_OX, 		YAXIS_START + 10, 	ORIGIN_OX, 			YAXIS_END - 15);  
					g.drawLine(ORIGIN_OX, 		YAXIS_END - 15, 	ORIGIN_OX - 5, 		YAXIS_END - 5);  
					g.drawLine(ORIGIN_OX, 		YAXIS_END - 15, 	ORIGIN_OX + 5, 		YAXIS_END - 5);  

					double 	array[][] = SHV.sCalculateEquation();
					int 	numInterval = array[0].length;
					tInterval = (int)(PAINT_WIDTH / (numInterval * 2 - 2));		
					
					g2D.setStroke(new BasicStroke(Float.parseFloat("0.5f")));  
					g.drawString("0", ORIGIN_OX - 20, ORIGIN_OY + 15);
					// 画 X 轴刻度(坐标原点起，隔一定像素，到 X 轴终点)  
					for (int i = 1; i < numInterval * 2 - 1; i++) {
						g.drawString(""+String.format("%1$.1f", array[0][0] + i * (array[0][1] - array[0][0]) / 2.0), 
								ORIGIN_OX - 20 + i * tInterval, ORIGIN_OY + 15);
						g.drawLine(ORIGIN_OX + i * tInterval, YAXIS_START, ORIGIN_OX + i * tInterval, YAXIS_END);
					}
					// 画 Y 轴刻度(坐标原点起，隔一定像素，到 Y 轴终点)  
					for (int i = 1; i < 6; i++) {
						g.drawString("+"+String.format("%1$.1f",SHV.sA0/5*i), ORIGIN_OX - 35, ORIGIN_OY + 10 - i * 32);
						g.drawString(""+String.format("%1$.1f",-SHV.sA0/5*i), ORIGIN_OX - 35, ORIGIN_OY + 10 + i * 32);
						g.drawLine(ORIGIN_OX, ORIGIN_OY - i * 32, 
								ORIGIN_OX + (numInterval - 1) * 2 * tInterval, ORIGIN_OY - i * 32);
						g.drawLine(ORIGIN_OX, ORIGIN_OY + i * 32, 
								ORIGIN_OX + (numInterval - 1) * 2 * tInterval, ORIGIN_OY + i * 32);
					}
					g.drawString("时间/t", XAXIS_END + 10, 	ORIGIN_OY + 15);
		            g.drawString("振幅/A", ORIGIN_OX - 30, 	YAXIS_END - 20);  
		            
		            // 画线段
		            for (int i = 0; i < numInterval * 2 - 1; i++) {
		            	int tmpI = i / 2;
		            	if (i % 2 == 0) {
		            		g2D.setStroke(new BasicStroke(Float.parseFloat("3f")));
		            		g.drawString("●", ORIGIN_OX + i * tInterval - 3, ORIGIN_OY + (int)(array[1][tmpI] / SHV.sA0 * 160) + 3);
						} else {
							g2D.setStroke(new BasicStroke(Float.parseFloat("1f")));
							int tmpX = ORIGIN_OX + i * tInterval - 3;
							int tmpY = (int) (ORIGIN_OY  + (int)(array[1][tmpI] / SHV.sA0 * 160 + array[1][tmpI+1] / SHV.sA0 * 160) / 2);
							g.drawLine(tmpX, tmpY, ORIGIN_OX + (i-1) * tInterval, ORIGIN_OY + (int)(array[1][tmpI] / SHV.sA0 * 160));
							g.drawLine(tmpX, tmpY, ORIGIN_OX + (i+1) * tInterval, ORIGIN_OY + (int)(array[1][tmpI+1] / SHV.sA0 * 160));
						}
		            	
					}
				}
				 
			};
		};
		
		this.pnlChart.setLayout(null);
		this.pnlChart.setBackground(Color.WHITE);
		this.pnlChart.setLocation(Constant.CONTENT_X, Constant.CONTENT_Y);		// (50, 60)
		this.pnlChart.setSize(Constant.PAINT_WIDTH, Constant.PAINT_HEIGHT);		// 700 * 400
		this.add(pnlChart);
		
		// 添加 按钮,监听器与标志
		this.btnPaint = new MyButton("绘图", 350, 480);
		this.add(btnPaint);
		this.btnPaint.addActionListener(this);			
		this.btnPaint.setActionCommand("btnPaint");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand() == "btnPaint") {
			isClicked = true;
			this.pnlChart.repaint();
		}
	}
		
}
