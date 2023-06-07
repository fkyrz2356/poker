let countdownTimer;

window.onload = function() {
  const params = new URLSearchParams(window.location.search);
  let initialSeconds = countdownInit || 10;

  startCountdown(initialSeconds * 10);
};

function startCountdown(tenthsOfSeconds) {
  let counter = tenthsOfSeconds;
  clearInterval(countdownTimer);
  document.getElementById('countdown').innerHTML = `${(counter / 10).toFixed(1)}s`;
  countdownTimer = setInterval(function() {
    counter--;
    document.getElementById('countdown').innerHTML = `${(counter / 10).toFixed(1)}s`;
    if (counter <= 0) {
      clearInterval(countdownTimer);
      hideButtonsAndShowNext(-1);
    }
  }, 100);
}

function hideButtonsAndShowNext(t) {
  clearInterval(countdownTimer);
  if(t == whichIsWinner){
      showShape('circle01');
  }else{
      showShape('cross01');
  }

  var buttons = document.querySelectorAll('#button-container button');
  for (var i = 0; i < buttons.length; i++) {
      buttons[i].style.display = 'none';
  }
  document.getElementById('judgeHandmain01').style.display = 'block';
  document.getElementById('judgeHandsub01').style.display = 'block';
  document.getElementById('next-button').style.display = 'block';
}