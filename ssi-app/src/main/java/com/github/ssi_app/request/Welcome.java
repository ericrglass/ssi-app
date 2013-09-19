package com.github.ssi_app.request;

import javax.servlet.http.HttpServletRequest;

import com.github.ssi_app.servlet.IRequestDelegator;
import com.github.ssi_app.util.I18nUtils;

public class Welcome implements IRequestDelegator {

	@Override
	public void processRequest(HttpServletRequest req, boolean debug) {
		if (req == null) {
			return;
		}

		I18nUtils.loadCultureResourceBundleIntoRequest(req, "welcome",
				req.getLocale(), "com.github.ssi_app.i18n.welcome", false,
				debug);

	}

}
