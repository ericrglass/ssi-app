package com.github.ssi_app.servlet;

import javax.servlet.http.HttpServletRequest;

public interface IRequestDelegator {

	public void processRequest(HttpServletRequest req, boolean debug);

}
