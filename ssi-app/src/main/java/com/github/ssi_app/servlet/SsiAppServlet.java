package com.github.ssi_app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ssi_app.bean.DeviceSessionBean;
import com.github.ssi_servlet.SsiServlet;

public class SsiAppServlet extends SsiServlet {

	public static final String INIT_PARAM_ENABLE_DEVICE_DETECTION_FEATURE = "com.github.ssi_app.servlet.ENABLE_DEVICE_DETECTION_FEATURE";
	public static final String INIT_PARAM_REQUEST_DELEGATOR_CLASS_NAME = "com.github.ssi_app.servlet.RequestDelegatorClassName";

	public static final String REQ_ATTR_DEVICE_SESSION_AVAILABLE = "DEVICE_SESSION_AVAILABLE";
	public static final String REQ_ATTR_DEVICE_SCREEN_WIDTH = "DEVICE_SCREEN_WIDTH";
	public static final String REQ_ATTR_DEVICE_SCREEN_HEIGHT = "DEVICE_SCREEN_HEIGHT";
	public static final String REQ_ATTR_DEVICE_CLIENT_WIDTH = "DEVICE_CLIENT_WIDTH";
	public static final String REQ_ATTR_DEVICE_CAN_RESIZE_BROWSER_WINDOW = "DEVICE_CAN_RESIZE_BROWSER_WINDOW";
	public static final String REQ_ATTR_DEVICE_PIXEL_RATIO = "DEVICE_PIXEL_RATIO";
	public static final String REQ_ATTR_DEVICE_IE9 = "DEVICE_IE9";
	public static final String REQ_ATTR_DEVICE_IE8 = "DEVICE_IE8";
	public static final String REQ_ATTR_DEVICE_IE8_OR_LESS = "DEVICE_IE8_OR_LESS";
	public static final String REQ_ATTR_DEVICE_IE7 = "DEVICE_IE7";
	public static final String REQ_ATTR_DEVICE_LESS_THAN_IE7 = "DEVICE_LESS_THAN_IE7";
	public static final String REQ_ATTR_DEVICE_SMARTPHONE = "DEVICE_SMARTPHONE";
	public static final String REQ_ATTR_DEVICE_TABLET = "DEVICE_TABLET";
	public static final String REQ_ATTR_DEVICE_HIGH_RESOLUTION_DISPLAY = "DEVICE_HIGH_RESOLUTION_DISPLAY";
	public static final String REQ_ATTR_DEVICE_BROWSER = "DEVICE_BROWSER";
	public static final String REQ_ATTR_DEVICE_OLD_BROWSER = "DEVICE_OLD_BROWSER";
	public static final String REQ_ATTR_DEVICE_LANDSCAPE = "DEVICE_LANDSCAPE";
	public static final String REQ_ATTR_DEVICE_PORTRAIT = "DEVICE_PORTRAIT";
	public static final String REQ_ATTR_DEVICE_HTML5_SUPPORTED = "DEVICE_HTML5_SUPPORTED";

	private static final long serialVersionUID = -5364812322312373560L;

	private boolean deviceDetectionFeature = false;
	private String reqDelegatorClassName;
	private Class<IRequestDelegator> reqDelegatorClass;

	@Override
	@SuppressWarnings("unchecked")
	public void init() throws ServletException {
		super.init();
		deviceDetectionFeature = Boolean
				.parseBoolean(getInitParameter(INIT_PARAM_ENABLE_DEVICE_DETECTION_FEATURE));

		if (!deviceDetectionFeature) {
			deviceDetectionFeature = Boolean.parseBoolean(getServletContext()
					.getInitParameter(
							INIT_PARAM_ENABLE_DEVICE_DETECTION_FEATURE));
		}

		if (deviceDetectionFeature) {
			// For the device detection feature, force buffered to be true
			buffered = true;
			// For the device detection feature, force expires to be 0 seconds
			expires = 0l;
		}

		reqDelegatorClassName = getInitParameter(INIT_PARAM_REQUEST_DELEGATOR_CLASS_NAME);

		if ((reqDelegatorClassName == null)
				|| (reqDelegatorClassName.length() == 0)) {
			reqDelegatorClassName = getServletContext().getInitParameter(
					INIT_PARAM_REQUEST_DELEGATOR_CLASS_NAME);
		}

		if ((reqDelegatorClassName != null)
				&& (reqDelegatorClassName.length() > 0)) {
			Class<?> testClass = null;

			try {
				testClass = Thread.currentThread().getContextClassLoader()
						.loadClass(reqDelegatorClassName);
			} catch (ClassNotFoundException e) {
				testClass = null;
			}

			if ((testClass != null)
					&& IRequestDelegator.class.isAssignableFrom(testClass)) {
				reqDelegatorClass = (Class<IRequestDelegator>) testClass;
			}
		}

		if (debug > 0) {
			log("SsiAppServlet.init() - ["
					+ getServletContext().getContextPath()
					+ "] - the device detection feature: "
					+ ((deviceDetectionFeature) ? "Has been enabled"
							: "Was not enabled"));

			if (reqDelegatorClass != null) {
				log("SsiAppServlet.init() - ["
						+ getServletContext().getContextPath()
						+ "] - started with request delegator class name '"
						+ reqDelegatorClassName + "'");
			}
		}
	}

	@Override
	protected String processBufferedBeforeCompress(
			final HttpServletRequest req, final String html) {
		DeviceSessionBean deviceSession = null;

		if (deviceDetectionFeature) {
			deviceSession = getDeviceSessionBean(req);
		}

		if ((deviceSession == null)
				|| (deviceSession.getHtmlClassNames() == null)) {
			return super.processBufferedBeforeCompress(req, html);
		}

		StringBuilder deviceHtml = new StringBuilder(
				super.processBufferedBeforeCompress(req, html));
		int htmlPos = deviceHtml.indexOf("<html");

		if (htmlPos < 0) {
			return deviceHtml.toString();
		}

		int htmlClosePos = deviceHtml.indexOf(">", htmlPos);
		int htmlClassPos = deviceHtml.indexOf(" class=", htmlPos);
		String classNames = "";

		if ((htmlClosePos > htmlPos) && (htmlClassPos > htmlPos)
				&& (htmlClassPos < htmlClosePos)) {
			String quoteChar = deviceHtml.substring(htmlClassPos + 7,
					htmlClassPos + 8);
			int quoteEndPos = deviceHtml.indexOf(quoteChar, htmlClassPos + 8);

			if (quoteEndPos < 0) {
				quoteEndPos = deviceHtml.indexOf(" ", htmlClassPos + 7);

				if ((quoteEndPos < 0) || (quoteEndPos > htmlClosePos)) {
					quoteEndPos = htmlClosePos - 1;
				} else {
					quoteEndPos--;
				}
			}

			String currentNames = deviceHtml.substring(htmlClassPos + 7,
					quoteEndPos + 1);
			deviceHtml.delete(htmlClassPos, quoteEndPos + 1);

			if ("'".equals(quoteChar) || "\"".equals(quoteChar)) {
				if (currentNames.length() > 2) {
					classNames = currentNames.substring(1,
							currentNames.length() - 1);
				}
			} else {
				classNames = currentNames;
			}
		}

		classNames += " " + deviceSession.getHtmlClassNames();
		deviceHtml.insert(htmlPos + 5, " class=\"" + classNames + "\"");
		return deviceHtml.toString();
	}

	@Override
	protected void requestHandlerInProcessSSI(final HttpServletRequest req,
			final HttpServletResponse res) throws IOException {
		super.requestHandlerInProcessSSI(req, res);

		if (reqDelegatorClass != null) {
			IRequestDelegator reqDelegator = null;

			try {
				reqDelegator = reqDelegatorClass.newInstance();
			} catch (InstantiationException e) {
				reqDelegator = null;
			} catch (IllegalAccessException e) {
				reqDelegator = null;
			}

			if (reqDelegator != null) {
				reqDelegator.processRequest(req, (debug > 0));
			}
		}

		DeviceSessionBean deviceSession = null;

		if (deviceDetectionFeature) {
			deviceSession = getDeviceSessionBean(req);
		}

		if (deviceSession != null) {
			req.setAttribute(REQ_ATTR_DEVICE_SESSION_AVAILABLE, "true");
			req.setAttribute(REQ_ATTR_DEVICE_SCREEN_WIDTH,
					String.valueOf(deviceSession.getScreenWidth()));
			req.setAttribute(REQ_ATTR_DEVICE_SCREEN_HEIGHT,
					String.valueOf(deviceSession.getScreenHeight()));
			req.setAttribute(REQ_ATTR_DEVICE_CLIENT_WIDTH,
					String.valueOf(deviceSession.getClientWidth()));
			req.setAttribute(REQ_ATTR_DEVICE_CAN_RESIZE_BROWSER_WINDOW,
					String.valueOf(deviceSession.isCanResizeBrowserWindow()));
			req.setAttribute(REQ_ATTR_DEVICE_PIXEL_RATIO,
					String.valueOf(deviceSession.getDevicePixelRatio()));
			req.setAttribute(REQ_ATTR_DEVICE_IE9,
					String.valueOf(deviceSession.isIE9()));
			req.setAttribute(REQ_ATTR_DEVICE_IE8,
					String.valueOf(deviceSession.isIE8()));
			req.setAttribute(REQ_ATTR_DEVICE_IE8_OR_LESS,
					String.valueOf(deviceSession.isIE8orLess()));
			req.setAttribute(REQ_ATTR_DEVICE_IE7,
					String.valueOf(deviceSession.isIE7()));
			req.setAttribute(REQ_ATTR_DEVICE_LESS_THAN_IE7,
					String.valueOf(deviceSession.isLessThanIE7()));
			req.setAttribute(REQ_ATTR_DEVICE_SMARTPHONE,
					String.valueOf(deviceSession.isSmartphone()));
			req.setAttribute(REQ_ATTR_DEVICE_TABLET,
					String.valueOf(deviceSession.isTablet()));
			req.setAttribute(REQ_ATTR_DEVICE_HIGH_RESOLUTION_DISPLAY,
					String.valueOf(deviceSession.isHighResolutionDisplay()));
			req.setAttribute(REQ_ATTR_DEVICE_BROWSER,
					String.valueOf(deviceSession.isDesktopBrowser()));
			req.setAttribute(REQ_ATTR_DEVICE_OLD_BROWSER,
					String.valueOf(deviceSession.isOldDesktopBrowser()));
			req.setAttribute(REQ_ATTR_DEVICE_LANDSCAPE,
					String.valueOf(deviceSession.isLandscape()));
			req.setAttribute(REQ_ATTR_DEVICE_PORTRAIT,
					String.valueOf(deviceSession.isPortrait()));
			req.setAttribute(REQ_ATTR_DEVICE_HTML5_SUPPORTED,
					String.valueOf(deviceSession.isHTML5Supported()));
		}
	}

	private DeviceSessionBean getDeviceSessionBean(final HttpServletRequest req) {
		if ((req == null)
				|| (req.getSession(false) == null)
				|| !(req.getSession().getAttribute(DeviceSessionBean.NAME) instanceof DeviceSessionBean)) {
			return null;
		}

		return (DeviceSessionBean) req.getSession().getAttribute(
				DeviceSessionBean.NAME);
	}

}
