let countdownTimer;
let counter

window.onload = function() {
  const params = new URLSearchParams(window.location.search);
  let initialSeconds = 60;

  startCountdown(initialSeconds * 10);

  for(let i=2; i<=50; i++) {
    let numStr = String(i).padStart(2, '0'); // 2桁の文字列に変換
    let element = document.getElementById('boardImage' + numStr);
    if (element) { // 要素が存在する場合のみ非表示にする
      element.style.display = 'none';
    }
  }
  document.getElementById('nowProblem').textContent = 1;

  document.getElementById('submitForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    document.getElementById('solved').value = document.getElementById('nowProblem').textContent;
    document.getElementById('restTime').value = document.getElementById('countdown').textContent;
  
    this.submit();
  });
};

function startCountdown(tenthsOfSeconds) {
  counter = tenthsOfSeconds;
  clearInterval(countdownTimer);
  document.getElementById('countdown').innerHTML = `${(counter / 10).toFixed(1)}s`;
  countdownTimer = setInterval(function() {
    counter--;
    document.getElementById('countdown').innerHTML = `${(counter / 10).toFixed(1)}s`;
    if (counter <= 0) {
      clearInterval(countdownTimer);
      let now = document.getElementById('nowProblem').textContent;
      hideButtonsAndShowNext(now, -1);
    }
  }, 100);
}

function addCountdown(time){
    counter += time * 10;
    let countdownElement = document.getElementById('countdown');
    countdownElement.style.color = "blue";
    countdownElement.innerHTML = `${(counter / 10).toFixed(1)}s`;
    
    // 500ms後に色を元に戻す
    setTimeout(function() {
        countdownElement.style.color = "initial";
    }, 400);
}

function hideButtonsAndShowNext(num, t) {
    var buttons = document.querySelectorAll('#button-container' + String(num).padStart(2, '0') + ' button');
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].style.display = 'none';
    }
    if(t == whichIsWinner[num] && num < 50){
        showShape('circle' + ('00' + (num + 0)).slice(-2));
        showShape('circle' + ('00' + (num + 1)).slice(-2));
        document.getElementById('boardImage' + String(num).padStart(2, '0')).style.display = 'none';
        document.getElementById('boardImage' + String(num + 1).padStart(2, '0')).style.display = '';
        document.getElementById('nowProblem').textContent = String(num + 1);
        addCountdown(2);
    }else if(t != whichIsWinner[num]){
        document.getElementById('judgeHandmain' + String(num).padStart(2, '0')).style.display = '';
        document.getElementById('judgeHandsub' + String(num).padStart(2, '0')).style.display = '';
        showShape('cross' + ('00' + num).slice(-2));
        clearInterval(countdownTimer);
    }else{
        clearInterval(countdownTimer);
    }
}