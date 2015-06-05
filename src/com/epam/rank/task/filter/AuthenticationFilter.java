package com.epam.rank.task.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.rank.task.model.UserModel;

public class AuthenticationFilter implements Filter {

	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponce = (HttpServletResponse) response;
		HttpSession userSession = httpRequest.getSession(false);

		if (userSession != null) {
			UserModel user = (UserModel) userSession.getAttribute("user");
			if (user == null) {
				httpResponce.sendRedirect("/EpamRankTask4/login");
				return;
			}
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
