package model;

import java.util.ArrayList;

public class QLSVModel {
	private ArrayList<SinhVien> dsSinhVien;

	public QLSVModel() {
		this.dsSinhVien = new ArrayList<SinhVien>();
	}

	public QLSVModel(ArrayList<SinhVien> dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
	}

	public ArrayList<SinhVien> getDsSinhVien() {
		return dsSinhVien;
	}

	public void setDsSinhVien(ArrayList<SinhVien> dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
	}
	
	public void insert(SinhVien sinhvien) {
		this.dsSinhVien.add(sinhvien);
	}
	public void delete(SinhVien sinhvien) {
		this.dsSinhVien.remove(sinhvien);
	}
	
	
	
	
	
	
}
