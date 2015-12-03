package lzsdodo.oop.finalproject;

import javax.swing.JTextField;

public class MyTextField extends JTextField {
	
	private static final long serialVersionUID = 1L;
	
	MyTextField() {
		super();
	}
	MyTextField(int columns) {
		super(columns);
	}
	MyTextField(String string) {
		super(string);
	}
	MyTextField(int columns, int x, int y) {
		super(columns);
		this.setSize(Constant.TEXT_WIDTH, Constant.TEXT_HEIGHT);
		this.setLocation(x, y);
	}	
	MyTextField(int columns, int width, int height, int x, int y) {
		super(columns);
		this.setSize(width, height);
		this.setLocation(x, y);
	}
	
}
