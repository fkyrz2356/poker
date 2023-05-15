window.onload = function() {
    const suits = ["club", "heart", "diamond", "spade"];
    const numbers = Array.from({length: 13}, (_, i) => i + 1);

    // 全てのカードの組み合わせを生成
    const allCards = [];
    for (const suit of suits) {
        for (const number of numbers) {
            allCards.push({
                suit: suit,
                number: number
            });
        }
    }

    // 重複を避けるために、全てのカードをシャッフル
    for (let i = allCards.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [allCards[i], allCards[j]] = [allCards[j], allCards[i]]; // Swap
    }

    let hands = []

    // シャッフルされたカードの先頭から5枚を選ぶ
    for (let i = 1; i <= 2; i++) {
        const card = allCards[i - 1];
        hands.push(card);
        document.getElementById('hand' + i).src = "/image/" + card.suit + "/" + card.number;
    }
    
    // シャッフルされたカードの先頭から5枚を選ぶ
    for (let i = 3; i <= 7; i++) {
        const card = allCards[i - 1];
        hands.push(card);
        document.getElementById('board' + (i - 2)).src = "/image/" + card.suit + "/" + card.number;
    }

    judge(hands);
    return;
}