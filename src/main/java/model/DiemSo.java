package model;

public class DiemSo {
	private String tenMon, MaMon;
	private float BT,KT,TH,CC,Thi,KTTP;
	private String hechu;
	public DiemSo(String tenMon, String maMon, float bT, float kT, float tH, float cC, float thi, float kTTP,
			String hechu) {
		super();
		this.tenMon = tenMon;
		MaMon = maMon;
		BT = bT;
		KT = kT;
		TH = tH;
		CC = cC;
		Thi = thi;
		KTTP = kTTP;
		this.hechu = hechu;
	}
	public String getTenMon() {
		return tenMon;
	}
	public String getMaMon() {
		return MaMon;
	}
	public float getBT() {
		return BT;
	}
	public float getKT() {
		return KT;
	}
	public float getTH() {
		return TH;
	}
	public float getCC() {
		return CC;
	}
	public float getThi() {
		return Thi;
	}
	public float getKTTP() {
		return KTTP;
	}
	public String getHechu() {
		return hechu;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public void setMaMon(String maMon) {
		MaMon = maMon;
	}
	public void setBT(float bT) {
		BT = bT;
	}
	public void setKT(float kT) {
		KT = kT;
	}
	public void setTH(float tH) {
		TH = tH;
	}
	public void setCC(float cC) {
		CC = cC;
	}
	public void setThi(float thi) {
		Thi = thi;
	}
	public void setKTTP(float kTTP) {
		KTTP = kTTP;
	}
	public void setHechu(String hechu) {
		this.hechu = hechu;
	}
	
	
	
	
}
