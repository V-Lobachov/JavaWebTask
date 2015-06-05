package com.epam.rank.task.helper;

public enum Actions {
	
	CREATE("create"), NEW("new"), SHOW("show"), EDIT("edit"), UPDATE("update"),  DELITE("delete"), INDEX("index");
	
	Actions(String action){
		this.action = action;
	}
	private String action;
	
	@Override
	public String toString() {
		return action;
	}
	
	
}
