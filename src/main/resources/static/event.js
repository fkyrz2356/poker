// 関数定義
function ReloadOrAnsDisplay() {
    location.reload();
    return;
}

function showShape(shapeId) {
    var shape = document.getElementById(shapeId);
    shape.style.display = 'block';
    setTimeout(function() {
        shape.style.display = 'none';
    }, 400);
}

// PCでのEnterキー押下イベント
window.addEventListener('keydown', function(e) {
    if (e.key === 'Enter') { // Enterキーが押された時
        ReloadOrAnsDisplay();
    }
});
