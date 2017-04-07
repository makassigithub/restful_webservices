package com.makas.model;

import java.util.ArrayList;

public class CarSearchObject {
private int ageFrom;
private int ageTo;

private ArrayList<String> owners;

// I use this enum to add additionnal info to my query.
private SearchType searchType;

public SearchType getSearchType() {
	return searchType;
}

public void setSearchType(SearchType searchType) {
	this.searchType = searchType;
}

public int getAgeFrom() {
	return ageFrom;
}

public void setAgeFrom(int ageFrom) {
	this.ageFrom = ageFrom;
}

public int getAgeTo() {
	return ageTo;
}

public void setAgeTo(int ageTo) {
	this.ageTo = ageTo;
}

public ArrayList<String> getOwners() {
	return owners;
}

public void setOwners(ArrayList<String> owners) {
	this.owners = owners;
}

@Override
public String toString() {
	return "CarSearchObject [ageFrom=" + ageFrom + ", ageTo=" + ageTo + ", owners=" + owners + "]";
}



}
