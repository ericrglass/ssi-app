if (typeof tempate !== 'undefined') {
  alert('There is name collision with tempate!');
} else {
  tempate = {
      show: function() {alert('tempate.show function needs to be implemented!');},
      hide: function() {alert('tempate.hide function needs to be implemented!');}
  };
}