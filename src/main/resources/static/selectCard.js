function makeHands(playerNum){
    let res = [];
    for(let i=1; i <= playerNum; i++){
        let hands = [];
        hands.push({suit: eval('imageTypeHand' + i + '1'), number: Number(eval('imageNumberHand' + i + '1'))});
        hands.push({suit: eval('imageTypeHand' + i + '2'), number: Number(eval('imageNumberHand' + i + '2'))});
        for(let j=1; j <= 5; j++){
            hands.push({suit: eval('imageTypeBoard' + j), number: Number(eval('imageNumberBoard' + j))});
        }
        console.log(hands);
        res.push(judge(hands));
    }    

    console.log(res);
    if(playerNum == 1){
        document.getElementById("judgeHandmain").innerHTML = `${res[0]["description"]}`;
    }else{
        document.getElementById("judgeHandmain").innerHTML = `Winner : ${isWinner(res)}`;
        document.getElementById("judgeHandsub").innerHTML = `Player 1 : <font color="red">${res[0]["description"]}</font><br>Player 2 : <font color="blue">${res[1]["description"]}</font>`;
    }
    
    return;
}