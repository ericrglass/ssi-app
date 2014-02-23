if (typeof footerWithNav !== 'undefined') {
  alert('There is name collision with footerWithNav!');
} else {
  footerWithNav = {
    showYourProfile: function() {
      if (yourProfile && (yourProfile !== null) && (typeof yourProfile.show === 'function')) {
        yourProfile.show();
      } else {
        alert('The footerWithNav.showYourProfile function is dependent on the yourProfile.show function!');
      }
    },
    hideYourProfile: function() {
      if (yourProfile && (yourProfile !== null) && (typeof yourProfile.hide === 'function')) {
        yourProfile.hide();
      } else {
        alert('The footerWithNav.hideYourProfile function is dependent on the yourProfile.hide function!');
      }
    }
  };
}