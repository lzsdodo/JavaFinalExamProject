package lzsdodo.oop.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class PanelFile extends MyPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private MyTextField	tfSavToFile, tfCpyFromFile, tfCpyToFile;
	private MyButton	btnSav, btnRead, btnCpy;
	private MyLabel		lblRemind;
	
	public PanelFile() {
		super("文件操作");
		int tmpHeight, tmpX, tmpY;
		
		String strSavTrgt 	= "保存到目标位置：";
		String strCpySrc	= "复制文件来源："; 
		String strCpyTrgt 	= "复制到目标位置：";
		
		tmpHeight 	= 40;
		tmpX 		= Constant.CONTENT_X;		tmpY		= 100;
		MyLabel		lblSavTrgt 	= new MyLabel(strSavTrgt, MyLabel.CHINESE, 125, tmpHeight, tmpX, tmpY);
		MyLabel		lblCpySrc 	= new MyLabel(strCpySrc, MyLabel.CHINESE, 125, tmpHeight, tmpX, tmpY+100);
		MyLabel		lblCpyTrgt 	= new MyLabel(strCpyTrgt, MyLabel.CHINESE, 125, tmpHeight, tmpX, tmpY+150);
		this.tfSavToFile	= new MyTextField(100, 540, tmpHeight, 175, tmpY);
		this.btnSav			= new MyButton("保存文件", tmpX, tmpY+50);
		this.btnRead		= new MyButton("读取文件", tmpX+150, tmpY+50);
		this.tfCpyFromFile	= new MyTextField(100, 540, tmpHeight, 175, tmpY+100);
		this.tfCpyToFile	= new MyTextField(100, 540, tmpHeight, 175, tmpY+150);
		this.btnCpy			= new MyButton("复制文件", tmpX, tmpY+200);	
		this.lblRemind		= new MyLabel("", MyLabel.ENGLISH, Constant.CONTENT_WIDTH, 100, Constant.CONTENT_X, tmpY+250);
		
		// 设置 输入文本框 初始值
		this.tfSavToFile.setText("./src/lzsdodo/oop/files/file.txt");
		this.tfCpyFromFile.setText("./src/lzsdodo/oop/files/file.txt");
		this.tfCpyToFile.setText("./src/lzsdodo/oop/files/file_bak.txt");
		
		// 面板 添加 控件
		this.add(lblSavTrgt);		this.add(lblCpySrc);		this.add(lblCpyTrgt);
		this.add(tfSavToFile);		this.add(tfCpyFromFile);	this.add(tfCpyToFile);
		this.add(btnSav);			this.add(btnRead);			this.add(btnCpy);			
		this.add(lblRemind);		
		
		// 按钮 添加 监听器与标志
		this.btnSav.addActionListener(this);			this.btnSav.setActionCommand("btnSav");
		this.btnRead.addActionListener(this);			this.btnRead.setActionCommand("btnRead");
		this.btnCpy.addActionListener(this);			this.btnCpy.setActionCommand("btnCpy");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "btnSav":
				// System.out.println(new File(".").getAbsolutePath());	
				// /Users/zsl/Documents/java/workspace/JavaProjectByZachary/.
				try {
					this.writeToFile(this.tfSavToFile.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
					this.lblRemind.setText("Error.");
				}
				break;
			case "btnRead":
				try {
					this.readFromFile(this.tfSavToFile.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
					this.lblRemind.setText("Error.");
				}
				break;
			case "btnCpy":
				try {
					this.fileCopy(new File(this.tfCpyFromFile.getText()), new File(this.tfCpyToFile.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
					this.lblRemind.setText("Error.");
				}
				break;
			default:
				break;
		}
		
	}
	
	void writeToFile(String filePath) throws IOException 
	{ 		
		File file = new File(filePath);
		if (!file.exists() || !file.isFile() || !file.canRead())  {
			file.createNewFile();							// 若文件不存在 构建文件
		}
		FileWriter writer = new FileWriter(filePath);		// 获取该文件的输出流
		writer.write(new SHV(SHV.sA0, SHV.sF, SHV.sDeg, SHV.sT, SHV.sT0, SHV.sT1, SHV.sTimes).toString());	// 写内容 
		if (writer!=null) {
			writer.flush();									// 清空缓冲区 立即将输出流里的内容写到文件里  
			writer.close();									// 关闭输出流 施放资源
		}	
		
		this.lblRemind.setText("Already Saved.");
	}
	
	void readFromFile(String filePath) throws IOException
	{
		File file 			= new File(filePath);			// 指定要读取的文件
		FileReader reader 	= new FileReader(file);			// 获取该文件的输入流  
		
		char[] buffChar = new char[1024];					// 用来保存每次读取到的字符  
		int n;												// 每次读取到的字符长度  
		String str = "<html>Already Read.<br/>";			// 用来将每次读取到的字符拼接  
        while ((n = reader.read(buffChar)) != -1) {  
            str += new String(buffChar, 0, n);  
        }  
        str += "</html>";
        reader.close();										// 关闭输入流，释放连接  
        
        this.lblRemind.setText(str);
	}	
	
	void fileCopy(File srcFile, File trgFile) throws IOException 
	{
        FileInputStream 	fi	= new FileInputStream(srcFile);
        FileOutputStream 	fo	= new FileOutputStream(trgFile);
        FileChannel 		in	= fi.getChannel();			// 得到对应的文件通道
        FileChannel 		out	= fo.getChannel();			// 得到对应的文件通道
        in.transferTo(0, in.size(), out);					// 连接两个通道，并且从in通道读取，然后写入out通道
        fi.close();			fo.close();						// 关闭输入输出流
        in.close();			out.close();
        this.lblRemind.setText("Already Copied.");
    }
	
}
