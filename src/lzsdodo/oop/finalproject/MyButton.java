package lzsdodo.oop.finalproject;

import javax.swing.JButton;

public class MyButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public MyButton() {
		super();
		setSize(Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
	}
	public MyButton(String str) {
		super(str);
		setSize(Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
	}
	public MyButton(String str, int x, int y) {
		super(str);
		setSize(Constant.BUTTON_WIDTH, Constant.BUTTON_HEIGHT);
		setLocation(x, y);
	}
	
}
