var scriptDeviceEventHandlersWinResizeTimeout;
window.onresize = function() {
  clearTimeout(scriptDeviceEventHandlersWinResizeTimeout);
  scriptDeviceEventHandlersWinResizeTimeout = setTimeout(function() {
    var screenWidth = screen.width;
	  var clientWidth = document.documentElement.clientWidth;
		var canResizeBrowserWindow = (clientWidth < screenWidth);
	  var orientation = '';
	  if (window.innerWidth > window.innerHeight) {
	    orientation = 'landscape';
	  } else {
	    orientation = 'portrait';
	  }
	  var htmlClass = document.getElementsByTagName('html')[0].className;
	  if (orientation === 'landscape') {
      var portraitPos = htmlClass.indexOf(' portrait ');
		  if (portraitPos > -1) {
			  // orientation has changed from portrait to landscape
			  var htmlClassStart = '';
        if (portraitPos > 0) {
          htmlClassStart = htmlClass.substring(0, portraitPos);
        }
			  var htmlClassEnd = '';
        if (portraitPos + 10 < htmlClass.length) {
          htmlClassEnd = htmlClass.substr(portraitPos + 10);
        }
        htmlClass = htmlClassStart +  ' landscape ' + htmlClassEnd;
		  }
	  } else {
		  var landscapePos = htmlClass.indexOf(' landscape ');
	    if (landscapePos > -1) {
	      // orientation has changed from landscape to portrait
        var htmlClassStart = '';
        if (landscapePos > 0) {
          htmlClassStart = htmlClass.substring(0, landscapePos);
        }
        var htmlClassEnd = '';
        if (landscapePos + 11 < htmlClass.length) {
          htmlClassEnd = htmlClass.substr(landscapePos + 11);
        }
        htmlClass = htmlClassStart +  ' portrait ' + htmlClassEnd;
	    }
	  }
    location.replace('deviceServlet?redirect='+encodeURIComponent(location.href)
        +'&htmlClassNames='+encodeURIComponent(htmlClass)
			    +'&screenWidth='+screenWidth
			    +'&clientWidth='+clientWidth
			    +'&canResizeBrowserWindow='+canResizeBrowserWindow);
  }, 250);
};
