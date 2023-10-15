package model;

public class SinhVien {
	private String maSV;
	private String tenSinhVien;
	private float Diem;
	public SinhVien(String maSV, String tenSinhVien, float diem) {
		this.maSV = maSV;
		this.tenSinhVien = tenSinhVien;
		Diem = diem;
	}
	public String getMaSV() {
		return maSV;
	}
	public String getTenSinhVien() {
		return tenSinhVien;
	}
	public float getDiem() {
		return Diem;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public void setTenSinhVien(String tenSinhVien) {
		this.tenSinhVien = tenSinhVien;
	}
	public void setDiem(float diem) {
		this.Diem = diem;
	}
	
	
	
	
	
}
