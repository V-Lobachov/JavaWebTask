package com.epam.rank.task.helper;

import javax.servlet.http.HttpServletRequest;

import com.epam.rank.task.model.BankAccountModel;

public class AccountHelper {
	public static BankAccountModel parseAccount(HttpServletRequest request,
			Integer user) {
		BankAccountModel account = new BankAccountModel();
		
		account.setAccount(request.getParameter("account"));
		try{
		account.setBalance(Float.parseFloat(request.getParameter("balance")));
		}catch(NumberFormatException e){
			account.setBalance(00.1111f);
		}
		account.setUser_id(user);
		return account;
	}
}
