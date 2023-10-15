package model;

public class SinhVien {
	private String maSV;
	private String tenSinhVien, hoLot,maLop;
	private DiemSo Diem;
	public SinhVien(String maSV, String tenSinhVien, String hoLot, String maLop, DiemSo diem) {
		super();
		this.maSV = maSV;
		this.tenSinhVien = tenSinhVien;
		this.hoLot = hoLot;
		this.maLop = maLop;
		Diem = diem;
	}
	public String getMaSV() {
		return maSV;
	}
	public String getTenSinhVien() {
		return tenSinhVien;
	}
	public String getHoLot() {
		return hoLot;
	}
	public String getMaLop() {
		return maLop;
	}
	public DiemSo getDiem() {
		return Diem;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public void setTenSinhVien(String tenSinhVien) {
		this.tenSinhVien = tenSinhVien;
	}
	public void setHoLot(String hoLot) {
		this.hoLot = hoLot;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public void setDiem(DiemSo diem) {
		Diem = diem;
	}
	
	
	
}
