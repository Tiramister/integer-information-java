/** 数字のみからなるか検証 */
function isNumberOnly(val) {
  let pattern = /^(\d*)$/;
  return pattern.test(val);
}

/** 送信時にチェック */
$("form").on("submit", function () {
  let val = $("input.sign-prohibited");
  if (isNumberOnly(val)) {
    return true;
  } else {
    alert("数字のみを入力して下さい。");
    return false;
  }
});
