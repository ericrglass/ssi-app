package com.github.ssi_app.bean;

import java.io.Serializable;

public class DeviceSessionBean implements Serializable {

	public static final String NAME = "ssi_appDeviceSession";

	private static final long serialVersionUID = 3404546927024801372L;

	private String htmlClassNames;
	private int screenWidth;
	private int screenHeight;
	private int clientWidth;
	private boolean canResizeBrowserWindow;
	private int devicePixelRatio;

	public String getHtmlClassNames() {
		return htmlClassNames;
	}

	public void setHtmlClassNames(String htmlClassNames) {
		if (htmlClassNames == null) {
			this.htmlClassNames = null;
		} else {
			this.htmlClassNames = " " + htmlClassNames + " ";
		}
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getClientWidth() {
		return clientWidth;
	}

	public void setClientWidth(int clientWidth) {
		this.clientWidth = clientWidth;
	}

	public boolean isCanResizeBrowserWindow() {
		return canResizeBrowserWindow;
	}

	public void setCanResizeBrowserWindow(boolean canResizeBrowserWindow) {
		this.canResizeBrowserWindow = canResizeBrowserWindow;
	}

	public int getDevicePixelRatio() {
		return devicePixelRatio;
	}

	public void setDevicePixelRatio(int devicePixelRatio) {
		this.devicePixelRatio = devicePixelRatio;
	}

	public boolean isIE9() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" ie9 ") > -1));
	}

	public boolean isIE8() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" ie8 ") > -1));
	}

	public boolean isIE8orLess() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" lte-ie8 ") > -1));
	}

	public boolean isIE7() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" ie7 ") > -1));
	}

	public boolean isLessThanIE7() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" lt-ie7 ") > -1));
	}

	public boolean isSmartphone() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" smartphone ") > -1));
	}

	public boolean isTablet() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" tablet ") > -1));
	}

	public boolean isHighResolutionDisplay() {
		return ((htmlClassNames != null)
				&& (htmlClassNames.indexOf(" highResolutionDisplay ") > -1) || (htmlClassNames
				.indexOf(" highresolutiondisplay ") > -1));
	}

	public boolean isDesktopBrowser() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" browser ") > -1));
	}

	public boolean isOldDesktopBrowser() {
		return ((htmlClassNames != null)
				&& (htmlClassNames.indexOf(" oldBrowser ") > -1) || (htmlClassNames
				.indexOf(" oldbrowser ") > -1));
	}

	public boolean isLandscape() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" landscape ") > -1));
	}

	public boolean isPortrait() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" portrait ") > -1));
	}

	public boolean isHTML5Supported() {
		return (isAudioSupported() && isCanvasSupported() && isVideoSupported());
	}

	public boolean isApplicationCacheSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" applicationcache ") > -1));
	}

	public boolean isAudioSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" audio ") > -1));
	}

	public boolean isBackgroundSizeSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" backgroundsize ") > -1));
	}

	public boolean isBorderImageSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" borderimage ") > -1));
	}

	public boolean isBorderRadiusSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" borderradius ") > -1));
	}

	public boolean isBoxShadowSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" boxshadow ") > -1));
	}

	public boolean isCanvasSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" canvas ") > -1));
	}

	public boolean isCanvasTextSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" canvastext ") > -1));
	}

	public boolean isCssAnimationsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" cssanimations ") > -1));
	}

	public boolean isCssColumnsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" csscolumns ") > -1));
	}

	public boolean isCssGradientsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" cssgradients ") > -1));
	}

	public boolean isCssReflectionsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" cssreflections ") > -1));
	}

	public boolean isCssTransformsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" csstransforms ") > -1));
	}

	public boolean isCssTransforms3dSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" csstransforms3d ") > -1));
	}

	public boolean isCssTransitionsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" csstransitions ") > -1));
	}

	public boolean isDragAndDropSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" draganddrop ") > -1));
	}

	public boolean isFlexboxSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" flexbox ") > -1));
	}

	public boolean isFlexboxLegacySupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" flexboxlegacy ") > -1));
	}

	public boolean isFontFaceSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" fontface ") > -1));
	}

	public boolean isGeneratedContentSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" generatedcontent ") > -1));
	}

	public boolean isGeoLocationSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" geolocation ") > -1));
	}

	public boolean isHashChangeSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" hashchange ") > -1));
	}

	public boolean isHistorySupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" history ") > -1));
	}

	public boolean isHslaSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" hsla ") > -1));
	}

	public boolean isIndexedDBSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" indexeddb ") > -1));
	}

	public boolean isInlineSvgSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" inlinesvg ") > -1));
	}

	public boolean isLocalStorageSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" localstorage ") > -1));
	}

	public boolean isMultipleBackgroundsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" multiplebgs ") > -1));
	}

	public boolean isOpacitySupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" opacity ") > -1));
	}

	public boolean isPostMessageSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" postmessage ") > -1));
	}

	public boolean isRgbaSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" rgba ") > -1));
	}

	public boolean isSessionStorageSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" sessionstorage ") > -1));
	}

	public boolean isSmilSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" smil ") > -1));
	}

	public boolean isSvgSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" svg ") > -1));
	}

	public boolean isSvgClipPathsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" svgclippaths ") > -1));
	}

	public boolean isTextShadowSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" textshadow ") > -1));
	}

	public boolean isTouchSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" touch ") > -1));
	}

	public boolean isVideoSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" video ") > -1));
	}

	public boolean isWebGlSupported() {
		return ((htmlClassNames != null) && (htmlClassNames.indexOf(" webgl ") > -1));
	}

	public boolean isWebSocketsSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" websockets ") > -1));
	}

	public boolean isWebSqlDatabaseSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" websqldatabase ") > -1));
	}

	public boolean isWebWorkersSupported() {
		return ((htmlClassNames != null) && (htmlClassNames
				.indexOf(" webworkers ") > -1));
	}

}
