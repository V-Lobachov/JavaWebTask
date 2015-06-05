package com.epam.rank.task.helper;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.epam.rank.task.model.UserModel;

public class UsersTag extends SimpleTagSupport {
	private List<UserModel> data;

	public void setData(List<UserModel> input) {
		this.data = input;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		for (UserModel user : data) {

			out.println("<tr>");
			out.println("<td>");
			out.print(user.getId());
			out.println("</td>");
			out.println("<td>");
			out.print(user.getFirstName());
			out.println("</td>");
			out.println("<td>");
			out.print(user.getLastName());
			out.println("</td>");
			out.println("<td>");
			out.print(user.getEmail());
			out.println("</td>");
			out.println("<td id='role'>");
			out.print(user.getRole());
			out.println("</td>");

			out.println("<td>");
			out.print("<a class='btn btn-primary btn-xs btn-lg btn-block' href='/EpamRankTask4/user/account?action=index&id="
					+ user.getId() + "'>User Accounts</a>");
			out.print("<a class='btn btn-success btn-xs btn-lg btn-block' href='/EpamRankTask4/user/history?action=index&userId="
					+ user.getId() + "'>Account History</a>");
			if (user.getRole().equals("admin")) {
				out.print("<a class='btn btn-danger btn-xs btn-lg btn-block' id='roleTriger' href='/EpamRankTask4/users?action=new&role=user&userId="
						+ user.getId() + "'>Change Role</a>");
			} else {
				out.print("<a class='btn btn-danger btn-xs btn-lg btn-block' id='roleTriger' href='/EpamRankTask4/users?action=new&role=admin&userId="
						+ user.getId() + "'>Change Role</a>");
			}

			out.println("</td>");
			out.println("</tr>");
		}
	}
}
