package com.epam.rank.task.filter;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.epam.rank.task.i18n.LocaleHandler;

public class I18nFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public I18nFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String currentLocale = (String) httpRequest.getSession().getAttribute(
				"locale");
		String locale = (String) request.getParameter("locale");

		if (currentLocale == null  || locale != null
				&& !locale.equals(currentLocale)) {
			ResourceBundle localeResource = LocaleHandler.changeLocale(locale);
			httpRequest.getSession().setAttribute("locale", locale);
			httpRequest.getSession().setAttribute("localeResource",
					localeResource);
		}
		// httpRequest.setAttribute("localeResource", localeResource);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
