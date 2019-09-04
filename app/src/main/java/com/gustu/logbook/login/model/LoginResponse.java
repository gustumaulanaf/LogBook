package com.gustu.logbook.login.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("0")
	private JsonMember0 jsonMember0;

	@SerializedName("isLogin")
	private int isLogin;

	@SerializedName("SIP")
	private List<Object> sIP;

	@SerializedName("menu")
	private List<Object> menu;

	@SerializedName("SPK")
	private List<Object> sPK;

	public void setJsonMember0(JsonMember0 jsonMember0){
		this.jsonMember0 = jsonMember0;
	}

	public JsonMember0 getJsonMember0(){
		return jsonMember0;
	}

	public void setIsLogin(int isLogin){
		this.isLogin = isLogin;
	}

	public int getIsLogin(){
		return isLogin;
	}

	public void setSIP(List<Object> sIP){
		this.sIP = sIP;
	}

	public List<Object> getSIP(){
		return sIP;
	}

	public void setMenu(List<Object> menu){
		this.menu = menu;
	}

	public List<Object> getMenu(){
		return menu;
	}

	public void setSPK(List<Object> sPK){
		this.sPK = sPK;
	}

	public List<Object> getSPK(){
		return sPK;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"0 = '" + jsonMember0 + '\'' + 
			",isLogin = '" + isLogin + '\'' + 
			",sIP = '" + sIP + '\'' + 
			",menu = '" + menu + '\'' + 
			",sPK = '" + sPK + '\'' + 
			"}";
		}
}