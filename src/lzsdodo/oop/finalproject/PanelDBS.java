package lzsdodo.oop.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanelDBS extends MyPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
//	protected MyLabel		lblIP,	lblPrt,	lblDB,	lblUsr,	lblPsd;
	
	private MyTextField	tfIP, 	tfPrt, 	tfDB,	tfUsr,	tfPsd;
	private MyButton	btnConnect, btnClose, btnSave, btnRead;
	private MyLabel		lblRemind;	
	private Database 	datebase = new Database();
	static  ResultSet 	resultSet;
	static  int 		ResultID;
	
	public PanelDBS() {
		super("数据库操作");
		
		int tmpWidth, tmpHeight, tmpX, tmpY;
		
		tmpWidth 	= 100;					tmpHeight	= Constant.TEXT_HEIGHT;
		tmpX		= Constant.CONTENT_X;	tmpY 		= 100; 
		MyLabel lblIP 		= new MyLabel("IP:", MyLabel.ENGLISH, tmpWidth, tmpHeight, tmpX, tmpY);
		MyLabel lblPrt 		= new MyLabel("Port:", MyLabel.ENGLISH, tmpWidth, tmpHeight, tmpX, tmpY+40);	
		MyLabel lblDB 		= new MyLabel("DB:", MyLabel.ENGLISH, tmpWidth, tmpHeight, tmpX, tmpY+80);		
		MyLabel lblUsr 		= new MyLabel("User:", MyLabel.ENGLISH, tmpWidth, tmpHeight, tmpX, tmpY+120);				
		MyLabel lblPsd 		= new MyLabel("Password:", MyLabel.ENGLISH, 75, tmpHeight, tmpX, tmpY+160);
		
		tmpWidth	= 150;					tmpHeight	= Constant.TEXT_HEIGHT;
		tmpX 		= 150;					tmpY = 100;
		this.tfIP 	= new MyTextField(20, tmpWidth, tmpHeight, tmpX, tmpY);	
		this.tfPrt 	= new MyTextField(20, tmpWidth, tmpHeight, tmpX, tmpY+40);	
		this.tfDB 	= new MyTextField(20, tmpWidth, tmpHeight, tmpX, tmpY+80);	
		this.tfUsr 	= new MyTextField(20, tmpWidth, tmpHeight, tmpX, tmpY+120);
		this.tfPsd	= new MyTextField(20, tmpWidth, tmpHeight, tmpX, tmpY+160);
		
		tmpX 		= 50;					tmpY = 300;
		this.btnConnect = new MyButton("链接数据库", tmpX, tmpY); 
		this.btnSave 	= new MyButton("更新数据库", tmpX+150, tmpY);
		this.btnRead 	= new MyButton("读取数据库", tmpX+300, tmpY);
		this.btnClose	= new MyButton("断开数据库", tmpX+450, tmpY);
		this.lblRemind 	= new MyLabel("", MyLabel.ENGLISH, Constant.CONTENT_WIDTH, 100, Constant.CONTENT_X, tmpY+50);
		
		// 设置 输入文本框 初始值
		this.tfIP.setText("127.0.0.1");
		this.tfPrt.setText("3306");
		this.tfDB.setText("OOPExperiment");			
		this.tfUsr.setText("zachary");
		this.tfPsd.setText("zacharymdb");

		// 面板 添加 控件
		this.add(lblIP);	this.add(lblPrt);	this.add(lblDB);	this.add(lblUsr);	this.add(lblPsd);
		this.add(tfIP);		this.add(tfPrt);	this.add(tfDB);		this.add(tfUsr);	this.add(tfPsd);
		this.add(btnConnect);					this.add(btnClose);					
		this.add(btnSave);						this.add(btnRead);
		this.add(lblRemind);
		
		// 按钮 添加 监听器与标志
		this.btnConnect.addActionListener(this);		this.btnConnect.setActionCommand("btnConnect");
		this.btnClose.addActionListener(this);			this.btnClose.setActionCommand("btnClose");
		this.btnSave.addActionListener(this);			this.btnSave.setActionCommand("btnSave");
		this.btnRead.addActionListener(this);			this.btnRead.setActionCommand("btnRead");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "btnConnect":
				try {
					this.datebase.connectDB(this.tfIP.getText(), this.tfPrt.getText(), this.tfDB.getText(),
											this.tfUsr.getText(), this.tfPsd.getText());
					if (this.datebase.isConnected())
						this.lblRemind.setText("Already Connected.");
				} catch (SQLException e1) {
					e1.printStackTrace();
					this.lblRemind.setText("Error.");
				}
				break;
			case "btnSave":
				try {
					String strSQL = "SELECT MAX(ID) FROM SHV";
					int maxID = 0;
					resultSet = this.datebase.doQuery(strSQL);
					if (resultSet.next()) {
						maxID = resultSet.getInt(1);
						maxID++;
					}
					
					strSQL = "Insert Into SHV(ID, A0, F, Deg, T, T0, T1, Times) values(?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = this.datebase.getConnection().prepareStatement(strSQL);
					stmt.setInt(1, maxID);
					stmt.setDouble(2, SHV.sA0);
					stmt.setDouble(3, SHV.sF);
					stmt.setDouble(4, SHV.sDeg);
					stmt.setDouble(5, SHV.sT);
					stmt.setDouble(6, SHV.sT0);
					stmt.setDouble(7, SHV.sT1);
					stmt.setInt(8, SHV.sTimes);
					stmt.executeUpdate();
					this.lblRemind.setText("Already Updated.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case "btnRead":
					try {				 
						String strSQL = "SELECT MAX(ID) FROM SHV";
						int maxID = 0;
						resultSet = this.datebase.doQuery(strSQL);
						if (resultSet.next()) {
							maxID = resultSet.getInt(1);
						}

						strSQL = "SELECT * FROM SHV WHERE ID = " + maxID;						
						resultSet = this.datebase.doQuery(strSQL);
						while (resultSet.next())
							this.lblRemind.setText("<html>Already Read From Database.<br/>"
										 + "ID: " + resultSet.getInt("ID") + ": " 
										 + "A0: " + resultSet.getDouble("A0") + ", " 
									     + "ω: " + resultSet.getDouble("F") + ", " 
									     + "Deg: " + resultSet.getDouble("Deg") + ", " 
									     + "T: " + resultSet.getDouble("T") + ", " 
									     + "T0: " + resultSet.getDouble("T0") + ", " 
									     + "T1: " + resultSet.getDouble("T1") + ", " 
									     + "T2: " + resultSet.getInt("Times") + "</html>");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
				break;
			case "btnClose":
			try {
				if (this.datebase.isConnected()) {
					this.datebase.close();
					this.lblRemind.setText("Already Closed.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
				break;
			default:
				break;
		}
	}
	
	void insertToDB() {
//		String sql = "insert into SHV(A0, F, Deg, T, T0, T1, Times)values(?, ?, ?, ?, ?, ?, ?)";
//		Connection conn = this.datebase.getConnection(); 
//		PreparedStatement pstmt;
//		pstmt = conn.prepareStatement(sql);

	}
	
}
