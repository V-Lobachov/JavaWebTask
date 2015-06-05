package com.epam.rank.task.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;



public class SortHelper {

	private HashMap<String, Integer> sortMap;
	private StringBuilder sortQuery;

	public SortHelper() {
		sortMap = new HashMap<String, Integer>();
		initialize();
		sortQuery = new StringBuilder();
	}

	public String getSortQuery(HttpServletRequest request) {
		sortQuery = new StringBuilder();
		ArrayList<String> asc = new ArrayList<String>();
		ArrayList<String> desc = new ArrayList<String>();
		String[] categories = new String[3];
		int value;
		categories[0] = request.getParameter("f");
		categories[1] = request.getParameter("l");
		categories[2] = request.getParameter("email");

		for (int i = 0; i < 3; i++) {
			if (categories[i] != null) {
				value = sortMap.get(categories[i]);
				value = (value + 1) % 3;
				sortMap.put(categories[i], value);
			}
		}

		for (Entry<String, Integer> node : sortMap.entrySet()) {
			if (node.getValue() == 0) {
				continue;
			} else if (node.getValue() == 1) {
				asc.add(node.getKey());
			} else if (node.getValue() == 2) {
				desc.add(node.getKey());
			}
		}
		
		if(asc.isEmpty() && desc.isEmpty()){
			return "";
		}else if(!asc.isEmpty() && !desc.isEmpty()) {
			String delim = "";
			sortQuery.append("ORDER BY ");
			for (String i : asc) {
			    sortQuery.append(delim).append(i);
			    delim = ",";
			}
			sortQuery.append(" ASC, ");
			delim = "";
			for (String i : desc) {
			    sortQuery.append(delim).append(i);
			    delim = ",";
			}
			sortQuery.append(" DESC");
			
		}else if (asc.isEmpty()) {
			sortQuery.append("ORDER BY ");
			String delim = "";
			for (String i : desc) {
			    sortQuery.append(delim).append(i);
			    delim = ",";
			}
			sortQuery.append(" DESC");
		}else if(desc.isEmpty()){
			sortQuery.append("ORDER BY ");
			String delim = "";
			for (String i : asc) {
			    sortQuery.append(delim).append(i);
			    delim = ",";
			}
			sortQuery.append(" ASC");
		}

		return sortQuery.toString();
	}

	private void initialize() {
		sortMap.put("first_name", 0);
		sortMap.put("last_name", 0);
		sortMap.put("email", 0);
	}

}
