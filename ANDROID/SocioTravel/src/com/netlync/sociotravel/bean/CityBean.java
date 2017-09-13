package com.netlync.sociotravel.bean;

public class CityBean {
	public int cityId;
	public String cityName;
	
	public CityBean(int id, String name){
		cityId = id;
		cityName = name;
	}
	
	public void setCityId(int cityId){
		this.cityId = cityId;
	}
	public void setCityName(String name){
		this.cityName = name;
	}
	
	public int getCityId(){
		return cityId;
	}
	public String getCityName(){
		return cityName;
	}
	public String toString(){
		return cityName;
	}
}
