let countdownTimer;

window.onload = function() {
  const params = new URLSearchParams(window.location.search);
  const initialSeconds = params.get('countdown') || 10;

  document.getElementById('set-countdown').addEventListener('click', function() {
    const seconds = document.getElementById('seconds-input').value;
    if (seconds) {
        // 現在のURLがどこから始まるかによって、リダイレクト先のURLを変更
        let url = window.location.href;
        if (url.startsWith('https://es4.eedept.kobe-u.ac.jp/poker/twoPlayers')) {
          window.location.href = `https://es4.eedept.kobe-u.ac.jp/poker/twoPlayers?countdown=${seconds}`;
        } else if (url.startsWith('http://localhost:8080/twoPlayers')) {
          window.location.href = `http://localhost:8080/twoPlayers?countdown=${seconds}`;
        }
      }
      
  });

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