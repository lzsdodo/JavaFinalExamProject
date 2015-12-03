package lzsdodo.oop.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

public class PanelEquation extends MyPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private MyTextField 	tfA0, 	tfOmiga, tfT, tfFai;
	private MyTextField		tfT0, 	tfT1, 	 tfTimes;
	private MyLabel 		lblA,	lblAs, 	 lblRemind;
	private MyButton		btnSlvEqtn, btnSlvEqtns, btnSavParameter;
	
	public PanelEquation() {
		super("简谐振动方程");
		int tmpWidth, tmpHeight, tmpX, tmpY;
		
		tmpWidth 	= 30;						tmpHeight	= Constant.TEXT_HEIGHT;
		tmpX		= Constant.CONTENT_X;		tmpY 		= 100; 
		MyLabel lblSHVEquation 	= new MyLabel("A = A0 * cos(ω*t + φ));", MyLabel.ENGLISH, Constant.CONTENT_WIDTH, tmpHeight, tmpX, tmpY);
		MyLabel lblPmtrA0 		= new MyLabel("A0=", MyLabel.ENGLISH, tmpWidth, tmpHeight, tmpX, tmpY+40);	
		MyLabel lblPmtrOmiga 	= new MyLabel("ω =", MyLabel.ENGLISH, tmpWidth, tmpHeight, 200, tmpY+40);		
		MyLabel lblPmtrTime 	= new MyLabel(" t =", MyLabel.ENGLISH, tmpWidth, tmpHeight, 350, tmpY+40);				
		MyLabel lblPmtrFai 		= new MyLabel("φ =", MyLabel.ENGLISH, tmpWidth, tmpHeight, 500, tmpY+40);			
		this.tfA0 		= new MyTextField(10, 80, tmpY+40);	
		this.tfOmiga 	= new MyTextField(10, 230, tmpY+40);	
		this.tfT 		= new MyTextField(10, 380, tmpY+40);	
		this.tfFai 		= new MyTextField(10, 530, tmpY+40);	
		this.lblA		= new MyLabel("A = "+"", MyLabel.ENGLISH, tmpX, tmpY+80);
		this.btnSlvEqtn 	= new MyButton("求解当前解", Constant.CONTENT_X, tmpY+120); 
		
		tmpWidth	= 0;						tmpHeight	= Constant.TEXT_HEIGHT;		
		tmpX		= Constant.CONTENT_X;		tmpY 		= 300;
		MyLabel lbl0 		= new MyLabel("自变量范围 [t0, t1]:     [", MyLabel.ENGLISH, 150, tmpHeight, tmpX, tmpY);
		MyLabel lbl1 		= new MyLabel(",", MyLabel.ENGLISH, 10, tmpHeight, 300, tmpY);
		MyLabel lbl2 		= new MyLabel("]", MyLabel.ENGLISH, 10, tmpHeight, 410, tmpY);
		MyLabel lblTimes 	= new MyLabel("采样次数的Times数量(不计两端):", MyLabel.ENGLISH, 250, tmpHeight, tmpX, tmpY+40);
		this.tfT0			= new MyTextField(20, 200, tmpY);
		this.tfT1			= new MyTextField(20, 310, tmpY);
		this.tfTimes		= new MyTextField(20, 260, tmpY+40);
		this.lblAs			= new MyLabel("As = "+"", MyLabel.ENGLISH, Constant.CONTENT_WIDTH, tmpHeight, tmpX, tmpY+80);
		this.btnSlvEqtns	= new MyButton("求解系列解", tmpX, tmpY+120);

		this.tfA0.setText("1.0");		this.tfOmiga.setText("1.0");		
		this.tfFai.setText("1.0");		this.tfT.setText("0.0");
		this.tfT0.setText("0.0");		this.tfT1.setText("2.0");		
		this.tfTimes.setText("1");
		
		this.lblRemind 			= new MyLabel("", MyLabel.ENGLISH, 200, 20, Constant.CONTENT_X+110, Constant.BUTTON_Y+10);
		this.btnSavParameter 	= new MyButton("保存参数", Constant.BUTTON_X1, Constant.BUTTON_Y);
		
		// 面板 添加 控件
		this.add(lblSHVEquation);			
		this.add(lblPmtrA0);	this.add(lblPmtrOmiga);		this.add(lblPmtrTime);		this.add(lblPmtrFai);
		this.add(tfA0);			this.add(tfOmiga);			this.add(tfT);				this.add(tfFai);
		this.add(lblA);			this.add(btnSlvEqtn);
		this.add(lbl0);			this.add(lbl1);				this.add(lbl2);			this.add(lblTimes);
		this.add(tfT0);			this.add(tfT1);				this.add(tfTimes);
		this.add(lblAs);		this.add(btnSlvEqtns);
		this.add(btnSavParameter);							this.add(lblRemind);
		
		// 按钮 添加 监听器与标志
		this.btnSlvEqtn.addActionListener(this);				this.btnSlvEqtn.setActionCommand("btnSlvEqtn");
		this.btnSlvEqtns.addActionListener(this);				this.btnSlvEqtns.setActionCommand("btnSlvEqtns");
		this.btnSavParameter.addActionListener(this);			this.btnSavParameter.setActionCommand("btnSavParameter");	
	}
	
	public SHV getSHVParameter() throws UnsupportedEncodingException {
		SHV shv 	= new SHV();
		shv.A0 		= Double.parseDouble(tfA0.getText());
		shv.f 		= Double.parseDouble(tfOmiga.getText());		
		shv.deg 	= Double.parseDouble(tfFai.getText());
		shv.t		= Double.parseDouble(tfT.getText());
		shv.t0		= Double.parseDouble(tfT0.getText());
		shv.t1		= Double.parseDouble(tfT1.getText());
		shv.times	= Integer.parseInt(tfTimes.getText());
		return shv;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "btnSlvEqtn":
			try {
				SHV shv = getSHVParameter();
				double answer = shv.solveEquation();
				this.lblA.setText("A = " + String.format("%1$.2f", answer));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			break;
		case "btnSlvEqtns":			
			try {
				SHV shv = getSHVParameter();
				double answer[][] = shv.calculateEquation();
				String string = "A = { ";
				for(int i=0; i<shv.times+2; i++)
				{
					if(i == 0) {
						string += "(" + String.format("%1$.2f", answer[0][i]) + "," 
								+ String.format("%1$.2f", answer[1][i]) + ")";
					}
					else {
						string += ", (" + String.format("%1$.2f", answer[0][i]) + "," 
								+ String.format("%1$.2f", answer[1][i]) + ")";
					}
				}
				string	+= " }";
				this.lblAs.setText(string);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			break;
		case "btnSavParameter":
			try {
				System.out.println("保存参数.");
				SHV shv = getSHVParameter();
				shv.saveParameter();
				lblRemind.setText("Already Saved Parameter.");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			break;
		default:
			break;
	}
	}
	
}
