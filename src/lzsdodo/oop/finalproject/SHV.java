package lzsdodo.oop.finalproject;

//	封装一个刻画和处理无阻尼简谐振动特性的类
//	(数学表达式为:A = A0 * cos(ω*t + φ)),包含:
//	属性:
//	A0,ω,φ分别为振幅,角频率和初相角 
//	t0,t1 给定的自变量(时间)区间 
//	sTimes 区间内离散采样的点数
//	方法:
//	一个无参构造方法,负责为属性初始化 
//	一个有参构造方法,按照出入的参数初始化属性 
//	给定变量 t 返回函数值的方法
//	给定 t0,t1 和 sTimes,返回存放函数值的动态数组方法

public class SHV {
	// Simple Harmonic Vibration
	// ********** ↓ 成员变量 ↓ **********
	static public double 	sA0 = 1.0, sF = 1.0, sDeg = 0.0, sT = 0.0, sT0 = 0.0, sT1 = 2.0;
	static public int 		sTimes = 4;
	
	public double A0, f, deg;	// 振幅 A0 Amplitude; 角频率 ω Angular Frequency; 相位 φ Degree / Phase;
	public double t, t0, t1;	// 时刻 t0 Time0; t1 Time1;
	public int times;			// 采样点数	Times
	// ---------- ---------- ----------
	
	// ********** ↓ 构造方法 ↓ **********
	public SHV() {
		this.A0 = 1.0;	this.f = 1.0;	this.deg = 0.0;		this.t = 0.0;
		this.t0 = 0.0;	this.t1 = 2.0;	this.times = 1;
	}
	public SHV(double A0, double f, double deg, double t, double t0, double t1, int times ) {
		this.A0 = A0;	this.f = f;		this.deg = deg;		this.t = t;
		this.t0 = t0;	this.t1 = t1;	this.times = sTimes;
	}
	// ---------- ---------- ----------
	
	// ********** ↓ 成员方法 ↓ **********
	public double	solveEquation() {
		double answer = A0 * Math.cos(f*t + deg);
		return answer;
	}
	
	public double	solveEquation(double t) {
		double answer = A0 * Math.cos(f*t + deg);
		return answer;
	}
	
	public double[][] calculateEquation() {
		double array[][] = new double[2][times+2];
		double 	delta = (t1 - t0) / (times + 1.0);
		for(int i=0;i<times+2;i++)
		{
			array[0][i] = t0 + i * delta;
			array[1][i] = this.solveEquation(t0 + i * delta);
		}
		return array;
	}
	public static double[][] sCalculateEquation() {
		double	array[][] = new double[2][sTimes+2];
		double 	delta = (sT1 - sT0) / (sTimes + 1.0);
		for(int i=0;i<sTimes+2;i++)
		{
			array[0][i] = sT0 + i * delta;
			array[1][i] = sA0 * Math.cos( sF * (sT0 + i * delta) + sDeg);
		}
		return array;
	}
	public void saveParameter() {
		sA0 = this.A0; 	sF = this.f;	sDeg = this.deg;		sT = this.t;
		sT0 = this.t0;	sT1 = this.t1;	sTimes = this.times;
	}
	public String 	toString() {
		String str;
		str = "简谐振动模型：A = A0 * cos(ω*t + φ);\n" 
				+ "参数为：A0="+ this.A0 + ", ω="+ this.f + ", φ=" + this.deg 
				+ ", t0=" + this.t0 + ", t1=" + this.t1 
				+ ", 区间采样点数：" + this.times + "("+ (times+2) +");";
		return str;
	}
	// ---------- ---------- ----------
}
