package com.gustu.logbook.main.model.levelKesulitan;

import com.google.gson.annotations.SerializedName;

public class Kesulitan {

	@SerializedName("RLK_KODE")
	private String rLKKODE;

	@SerializedName("RLK_NAMA")
	private String rLKNAMA;

	@SerializedName("RLK_USERUPDATE")
	private String rLKUSERUPDATE;

	@SerializedName("RLK_ISAKTIF")
	private String rLKISAKTIF;

	@SerializedName("RLK_LASTUPDATE")
	private String rLKLASTUPDATE;

	public void setRLKKODE(String rLKKODE){
		this.rLKKODE = rLKKODE;
	}

	public String getRLKKODE(){
		return rLKKODE;
	}

	public void setRLKNAMA(String rLKNAMA){
		this.rLKNAMA = rLKNAMA;
	}

	public String getRLKNAMA(){
		return rLKNAMA;
	}

	public void setRLKUSERUPDATE(String rLKUSERUPDATE){
		this.rLKUSERUPDATE = rLKUSERUPDATE;
	}

	public String getRLKUSERUPDATE(){
		return rLKUSERUPDATE;
	}

	public void setRLKISAKTIF(String rLKISAKTIF){
		this.rLKISAKTIF = rLKISAKTIF;
	}

	public String getRLKISAKTIF(){
		return rLKISAKTIF;
	}

	public void setRLKLASTUPDATE(String rLKLASTUPDATE){
		this.rLKLASTUPDATE = rLKLASTUPDATE;
	}

	public String getRLKLASTUPDATE(){
		return rLKLASTUPDATE;
	}

	@Override
 	public String toString(){
		return 
			"Kegiatan{" +
			"rLK_KODE = '" + rLKKODE + '\'' + 
			",rLK_NAMA = '" + rLKNAMA + '\'' + 
			",rLK_USERUPDATE = '" + rLKUSERUPDATE + '\'' + 
			",rLK_ISAKTIF = '" + rLKISAKTIF + '\'' + 
			",rLK_LASTUPDATE = '" + rLKLASTUPDATE + '\'' + 
			"}";
		}
}