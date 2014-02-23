if (typeof yourProfile !== 'undefined') {
  alert('There is name collision with yourProfile!');
} else {
  yourProfile = {
      show: function() {
        if ($ && ($ !== null) && $.fn && $.fn.foundation && ($.fn.foundation !== null)) {
          $('#yourProfileModal').removeClass('hide').foundation('reveal', 'open');
        }
      },
      hide: function() {
        if ($ && ($ !== null) && $.fn && $.fn.foundation && ($.fn.foundation !== null)) {
          $('#yourProfileModal').foundation('reveal', 'close');
        }
      }
  };
}