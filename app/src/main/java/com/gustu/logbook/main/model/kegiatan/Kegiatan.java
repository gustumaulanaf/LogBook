package com.gustu.logbook.main.model.kegiatan;

import com.google.gson.annotations.SerializedName;

public class Kegiatan {

	@SerializedName("MKL_KETERANGAN")
	private String mKLKETERANGAN;

	@SerializedName("MKL_NAMA")
	private String mKLNAMA;

	@SerializedName("MKL_KODE")
	private String mKLKODE;

	public void setMKLKETERANGAN(String mKLKETERANGAN){
		this.mKLKETERANGAN = mKLKETERANGAN;
	}

	public String getMKLKETERANGAN(){
		return mKLKETERANGAN;
	}

	public void setMKLNAMA(String mKLNAMA){
		this.mKLNAMA = mKLNAMA;
	}

	public String getMKLNAMA(){
		return mKLNAMA;
	}

	public void setMKLKODE(String mKLKODE){
		this.mKLKODE = mKLKODE;
	}

	public String getMKLKODE(){
		return mKLKODE;
	}

	@Override
 	public String toString(){
		return 
			"Kegiatan{" +
			"mKL_KETERANGAN = '" + mKLKETERANGAN + '\'' + 
			",mKL_NAMA = '" + mKLNAMA + '\'' + 
			",mKL_KODE = '" + mKLKODE + '\'' + 
			"}";
		}
}