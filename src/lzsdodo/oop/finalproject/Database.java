package lzsdodo.oop.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database extends Object
{
	private	String 		IP 			= "";		// 数据库服务器的 IP 地址
	private	String		Port 		= "";		// 数据库服务器的访问端口
	private	String		DB 			= "";		// 要访问的数据库
	private	String		User 		= "";		// 用户名
	private	String		Password 	= "";		// 密码
	private boolean		connectID 	= false;	// 数据库连接成功的标志
	private	String		connectURL 	= "";		// 数据库连接地址
	private Connection 	connect 	= null;		// 数据库的连接
	
	/**
	 * 数据库类的无参构造方法
	 */
	public Database()
	{
		this.IP 	= "127.0.0.1";			// 127.0.0.1 代表本机
		this.Port 	= "3306";				 
		this.DB 	= "OOPExperiment";			
		this.User 	= "zachary";
		this.Password = "zacharymdb";
		this.connectURL	= "";
		this.connectID	= false;
	}
	
	/**
	 * 数据库类的有参构造方法
	 * @param ip	传入的 IP 地址
	 * @param port	端口
	 * @param db	数据库
	 * @param user	用户
	 * @param pwd	密码
	 * @throws SQLException	例外类
	 */
	Database(String	ip, String	port, String db, String user, String pwd) throws SQLException
	{
		this.IP 		= ip;
		this.Port 		= port;
		this.DB 		= db;
		this.User 		= user;
		this.Password 	= pwd;
		
		connectURL	= "jdbc:mysql://" + ip + ":" + port + "/" + db;		// 将输入的参数拼接成连接数据库的 URL
		
		String Driver = "com.mysql.jdbc.Driver";	// MySQL 的驱动程序名

		// 加载驱动程序
	    try { Class.forName(Driver); } catch (ClassNotFoundException e)   { e.printStackTrace(); }
	
	    // 连接数据库
	    connect = DriverManager.getConnection(connectURL, User, Password);
	    if (!connect.isClosed()) 
	   	 	connectID = true;
	}
	
	/**
	 * @return boolean = true 数据库连接成功； false 连接失败；
	 * @throws SQLException 
	 */
	public boolean connectDB(String	ip, String	port, String db, String user, String pwd) throws SQLException
	{
		this.IP 		= ip;
		this.Port 		= port;
		this.DB 		= db;
		this.User 		= user;
		this.Password 	= pwd;
		
		connectURL		= "jdbc:mysql://" + ip + ":" + port + "/" + db;
		
		String Driver 	= "com.mysql.jdbc.Driver";

		// 加载驱动程序
	    try { Class.forName(Driver); } 
	    catch (ClassNotFoundException e)   
	    { e.printStackTrace(); }
	
	    // 连接数据库，如果成功，置连接成功标志  connectID 为 true，否则置 false；
	    connect = DriverManager.getConnection(connectURL, User, Password);
	    if (!connect.isClosed()) 
	   	 	return connectID = true;
	    else
	   	 	return connectID = false;
	}
	
	public String getIP() 	{ return this.IP; }
	public String getPort() { return this.Port; }
	public String getDB() 	{ return this.DB; }
	public String getUser() { return this.User; }
	public String getPWD() 	{ return this.Password; }
	public Connection getConnection() { return this.connect; }
	
	/**
	 * 返回数据库连接状态的方法，判断连接是否成功。
	 * @return =true  成功
	 *         =false 失败
	 */
	public boolean isConnected() { return connectID; }
	
	
	/**
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public ResultSet doQuery(String strSQL) throws SQLException
	{
		ResultSet rs;
		if (isConnected())
		{
			Statement stmt = connect.createStatement();
			rs =  stmt.executeQuery(strSQL); 
			return rs;
		}
		else
			return null;
	}
	
	/**
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String strSQL) throws SQLException
	{ return doQuery(strSQL); }
	
	/**
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public ResultSet doSelect(String strSQL) throws SQLException
	{ return doQuery(strSQL); }
	
	/**
	 * 专门执行 Insert 语句的方法
	 * @param strSQL
	 * @return
	 * @throws SQLException
	 */
	public int doInsert(String strSQL) throws SQLException
	{
		if (isConnected())
		{
			Statement stmt = connect.createStatement();
			return stmt.executeUpdate(strSQL);
		}
		else
			return -1;
	}
	
	/**
	 * 执行 insert, update, delete, create, drop 等 SQL 语句的方法
	 * @param 	strSQL 为要执行的 SQL 语句
	 * @return 	返回整型数，代表 SQL 语句操作后受影响的记录数
	 * @throws SQLException
	 */
	public int doUpdate(String strSQL) throws SQLException
	{
		if (isConnected())
		{
			Statement stmt = connect.createStatement();
			return stmt.executeUpdate(strSQL);
		}
		else
			return -1;
	}
	
	/**
	 * 关闭与数据库相关的所有连接
	 * @throws SQLException 
	 */
	public void close() throws SQLException
	{ if(connect!=null) this.connect.close(); }
	
}
