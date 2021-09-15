$(".sign-prohibited").keydown(function(e) {
  console.log(e.which);
  if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
    e.preventDefault();
  }
})
