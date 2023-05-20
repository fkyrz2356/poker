// 関数定義
function ReloadOrAnsDisplay() {
    if(document.getElementById('judgeHand').style.display == 'none'){
        document.getElementById('judgeHand').style.display = 'block';
    }else{
        location.reload();
    }
    return;
}

// スマホでのタッチイベント
window.addEventListener('touchstart', function(e) {
    ReloadOrAnsDisplay();
});

// PCでの左クリックイベント
window.addEventListener('click', function(e) {
    ReloadOrAnsDisplay();
});

// PCでのEnterキー押下イベント
window.addEventListener('keydown', function(e) {
    if (e.key === 'Enter') { // Enterキーが押された時
        ReloadOrAnsDisplay();
    }
});

