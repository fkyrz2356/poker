let countdownTimer;

window.onload = function() {
  const params = new URLSearchParams(window.location.search);
  let initialSeconds = 60;

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

function hideButtonsAndShowNext(num, t) {
    if(t == whichIsWinner[num]){
        showShape('circle' + ('00' + num).slice(-2));
    }else{
        showShape('cross' + ('00' + num).slice(-2));
        clearInterval(countdownTimer);
    }

    var buttons = document.querySelectorAll('#button-container01 button');
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].style.display = 'none';
    }
}