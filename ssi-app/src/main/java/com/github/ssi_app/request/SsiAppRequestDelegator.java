package com.github.ssi_app.request;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import com.github.ssi_app.SsiAppLogger;
import com.github.ssi_app.servlet.IRequestDelegator;

public class SsiAppRequestDelegator implements IRequestDelegator {

	@Override
	public void processRequest(HttpServletRequest req, boolean debug) {
		if (req == null) {
			return;
		}

		String reqPath = req.getServletPath();

		if (reqPath.startsWith("/")) {
			if (reqPath.length() > 1) {
				reqPath = reqPath.substring(1);
			} else {
				reqPath = "";
			}
		}

		if (reqPath.endsWith(".html")) {
			if (reqPath.length() > 5) {
				reqPath = reqPath.substring(0, reqPath.length() - 5);
			} else {
				reqPath = "";
			}
		}

		if (debug) {
			SsiAppLogger.LOGGER.log(Level.INFO,
					"SsiAppRequestDelegator processing request for path '"
							+ req.getServletPath() + "' as request process: "
							+ reqPath);
		}

		Common common = new Common();
		common.processRequest(req, debug);

		if ("welcome".equalsIgnoreCase(reqPath)) {
			Welcome welcome = new Welcome();
			welcome.processRequest(req, debug);
		}
	}

}
