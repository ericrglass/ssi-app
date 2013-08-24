package com.github.ssi_app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ssi_app.bean.DeviceSessionBean;

public class DeviceServlet extends HttpServlet {

	public static final String PARAM_REDIRECT = "redirect";
	public static final String PARAM_HTML_CLASS_NAMES = "htmlClassNames";
	public static final String PARAM_SCREEN_WIDTH = "screenWidth";
	public static final String PARAM_SCREEN_HEIGHT = "screenHeight";
	public static final String PARAM_CLIENT_WIDTH = "clientWidth";
	public static final String PARAM_CAN_RESIZE_WINDOW = "canResizeBrowserWindow";
	public static final String PARAM_DEVICE_PIXEL_RATIO = "devicePixelRatio";

	private static final long serialVersionUID = -7578889557054189909L;

	private boolean deviceDetectionFeature = false;

	@Override
	public void init() throws ServletException {
		super.init();
		deviceDetectionFeature = Boolean
				.parseBoolean(getInitParameter(SsiAppServlet.INIT_PARAM_ENABLE_DEVICE_DETECTION_FEATURE));

		if (!deviceDetectionFeature) {
			deviceDetectionFeature = Boolean
					.parseBoolean(getServletContext()
							.getInitParameter(
									SsiAppServlet.INIT_PARAM_ENABLE_DEVICE_DETECTION_FEATURE));
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!deviceDetectionFeature) {
			resp.sendError(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		String redirect = req.getParameter(PARAM_REDIRECT);
		String htmlClassNames = req.getParameter(PARAM_HTML_CLASS_NAMES);
		String screenWidth = req.getParameter(PARAM_SCREEN_WIDTH);
		String screenHeight = req.getParameter(PARAM_SCREEN_HEIGHT);
		String clientWidth = req.getParameter(PARAM_CLIENT_WIDTH);
		String canResizeBrowserWindow = req
				.getParameter(PARAM_CAN_RESIZE_WINDOW);
		String devicePixelRatio = req.getParameter(PARAM_DEVICE_PIXEL_RATIO);

		DeviceSessionBean deviceSession = null;

		if ((req.getSession(false) != null)
				&& (req.getSession().getAttribute(DeviceSessionBean.NAME) instanceof DeviceSessionBean)) {
			deviceSession = (DeviceSessionBean) req.getSession().getAttribute(
					DeviceSessionBean.NAME);
		}

		if (deviceSession == null) {
			deviceSession = new DeviceSessionBean();
		}

		if ((htmlClassNames != null) && (htmlClassNames.length() > 0)) {
			deviceSession.setHtmlClassNames(htmlClassNames);
		}

		if ((screenWidth != null) && (screenWidth.length() > 0)) {
			try {
				deviceSession.setScreenWidth(Integer.parseInt(screenWidth));
			} catch (NumberFormatException e) {
			}
		}

		if ((screenHeight != null) && (screenHeight.length() > 0)) {
			try {
				deviceSession.setScreenHeight(Integer.parseInt(screenHeight));
			} catch (NumberFormatException e) {
			}
		}

		if ((clientWidth != null) && (clientWidth.length() > 0)) {
			try {
				deviceSession.setClientWidth(Integer.parseInt(clientWidth));
			} catch (NumberFormatException e) {
			}
		}

		if ((canResizeBrowserWindow != null)
				&& (canResizeBrowserWindow.length() > 0)) {
			deviceSession.setCanResizeBrowserWindow(Boolean
					.parseBoolean(canResizeBrowserWindow));
		}

		if ((devicePixelRatio != null) && (devicePixelRatio.length() > 0)) {
			try {
				deviceSession.setDevicePixelRatio(Integer
						.parseInt(devicePixelRatio));
			} catch (NumberFormatException e) {
			}
		}

		req.getSession(true)
				.setAttribute(DeviceSessionBean.NAME, deviceSession);

		if ((redirect == null) || (redirect.length() == 0)) {
			resp.sendError(HttpServletResponse.SC_OK);
			return;
		}

		resp.sendRedirect(redirect);
	}

}
