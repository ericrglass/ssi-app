package com.github.ssi_app.request;

import javax.servlet.http.HttpServletRequest;

import com.github.ssi_app.servlet.IRequestDelegator;

public class Common implements IRequestDelegator {

	@Override
	public void processRequest(HttpServletRequest req, boolean debug) {
		if (req == null) {
			return;
		}

		// TODO Auto-generated method stub

	}

}
