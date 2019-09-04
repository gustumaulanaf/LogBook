package com.gustu.logbook.main.model.levelPrioritas;

import com.google.gson.annotations.SerializedName;

public class Priotitas {

	@SerializedName("RLP_KODE")
	private String rLPKODE;

	@SerializedName("RLP_ISAKTIF")
	private String rLPISAKTIF;

	@SerializedName("RLP_USERUPDATE")
	private String rLPUSERUPDATE;

	@SerializedName("RLP_LASTUPDATE")
	private String rLPLASTUPDATE;

	@SerializedName("RLP_NAMA")
	private String rLPNAMA;

	public void setRLPKODE(String rLPKODE){
		this.rLPKODE = rLPKODE;
	}

	public String getRLPKODE(){
		return rLPKODE;
	}

	public void setRLPISAKTIF(String rLPISAKTIF){
		this.rLPISAKTIF = rLPISAKTIF;
	}

	public String getRLPISAKTIF(){
		return rLPISAKTIF;
	}

	public void setRLPUSERUPDATE(String rLPUSERUPDATE){
		this.rLPUSERUPDATE = rLPUSERUPDATE;
	}

	public String getRLPUSERUPDATE(){
		return rLPUSERUPDATE;
	}

	public void setRLPLASTUPDATE(String rLPLASTUPDATE){
		this.rLPLASTUPDATE = rLPLASTUPDATE;
	}

	public String getRLPLASTUPDATE(){
		return rLPLASTUPDATE;
	}

	public void setRLPNAMA(String rLPNAMA){
		this.rLPNAMA = rLPNAMA;
	}

	public String getRLPNAMA(){
		return rLPNAMA;
	}

	@Override
 	public String toString(){
		return 
			"Kegiatan{" +
			"rLP_KODE = '" + rLPKODE + '\'' + 
			",rLP_ISAKTIF = '" + rLPISAKTIF + '\'' + 
			",rLP_USERUPDATE = '" + rLPUSERUPDATE + '\'' + 
			",rLP_LASTUPDATE = '" + rLPLASTUPDATE + '\'' + 
			",rLP_NAMA = '" + rLPNAMA + '\'' + 
			"}";
		}
}