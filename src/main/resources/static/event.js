// 関数定義
function ReloadOrAnsDisplay() {
    location.reload();
    return;
}

function hideButtonsAndShowNext(t) {
    if(t == whichIsWinner){
        showShape('circle');
    }else{
        showShape('cross');
    }

    var buttons = document.querySelectorAll('#button-container button');
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].style.display = 'none';
    }
    document.getElementById('judgeHandmain').style.display = 'block';
    document.getElementById('judgeHandsub').style.display = 'block';
    document.getElementById('next-button').style.display = 'block';
}

function showShape(shapeId) {
    var shape = document.getElementById(shapeId);
    shape.style.display = 'block';
    setTimeout(function() {
        shape.style.display = 'none';
    }, 400);
}

function judgeCorrect(){

}

// スマホでのタッチイベント
/*
window.addEventListener('touchstart', function(e) {
    ReloadOrAnsDisplay();
});
*/
// PCでの左クリックイベント
/*
window.addEventListener('click', function(e) {
    ReloadOrAnsDisplay();
});
*/

// PCでのEnterキー押下イベント
window.addEventListener('keydown', function(e) {
    if (e.key === 'Enter') { // Enterキーが押された時
        ReloadOrAnsDisplay();
    }
});

function highlight(){
    
}