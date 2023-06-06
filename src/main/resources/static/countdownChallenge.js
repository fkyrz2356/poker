let countdownTimer, countdownTimerPrev;
let counter, counterPrev;

window.onload = function() {
  const params = new URLSearchParams(window.location.search);

  startCountdownPrev(10);

  document.getElementById('countdownPrev').style.display = "block";
  document.getElementById('countdown').style.display = "none";

  for(let i=1; i<=50; i++) {
    let numStr = String(i).padStart(2, '0'); // 2桁の文字列に変換
    let element = document.getElementById('boardImage' + numStr);
    if (element) { // 要素が存在する場合のみ非表示にする
      element.style.display = 'none';
    }
  }
  document.getElementById('nowProblem').textContent = 0;

  document.getElementById('submitForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    let nowProblemValue = parseInt(document.getElementById('nowProblem').textContent, 10);
    document.getElementById('solved').value = nowProblemValue - 1;

    document.getElementById('restTime').value = document.getElementById('countdown').textContent;
  
    this.submit();
  });
};

function startCountdownPrev(tenthsOfSeconds) {
  counterPrev = tenthsOfSeconds;
  clearInterval(countdownTimerPrev);
  document.getElementById('countdownPrev').innerHTML = `${counterPrev}s to start...`;
  countdownTimerPrev = setInterval(function() {
    counterPrev--;
    document.getElementById('countdownPrev').innerHTML = `${counterPrev}s to start...`;
    if (counterPrev <= 0) {
      document.getElementById('countdownPrev').innerHTML = ``;
      document.getElementById('nowProblem').textContent = 1;
      startCountdown(600);
      clearInterval(countdownTimerPrev);
      document.getElementById('countdownPrev').style.display = "none";
      document.getElementById('countdown').style.display = "block";
      document.getElementById('boardImage00').style.display = "none";
      document.getElementById('boardImage01').style.display = "block";
    }
  }, 1000);
}

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
        document.getElementById('boardImage' + String(num + 1).padStart(2, '0')).style.display = 'block';
        document.getElementById('nowProblem').textContent = String(num + 1);
        addCountdown(2);
    }else if(t != whichIsWinner[num]){
        document.getElementById('judgeHandmain' + String(num).padStart(2, '0')).style.display = 'block';
        document.getElementById('judgeHandsub' + String(num).padStart(2, '0')).style.display = 'block';
        document.getElementById('submitButton').style.display = 'block';;
        showShape('cross' + ('00' + num).slice(-2));
        clearInterval(countdownTimer);
    }else{
        clearInterval(countdownTimer);
    }
}