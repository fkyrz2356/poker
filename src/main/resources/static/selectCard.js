function makeHands(){
    for(let k=1; k <= boardNum; ++k){
        for(let i=1; i <= playerNum; i++){
            let hands = [];
            hands.push({suit: eval('imageTypeHand' + ('00' + k).slice(-2) + i + '1'), number: Number(eval('imageNumberHand' + ('00' + k).slice(-2) + i + '1'))});
            hands.push({suit: eval('imageTypeHand' + ('00' + k).slice(-2) + i + '2'), number: Number(eval('imageNumberHand' + ('00' + k).slice(-2) + i + '2'))});
            for(let j=1; j <= 5; j++){
                hands.push({suit: eval('imageTypeBoard' + ('00' + k).slice(-2) + j), number: Number(eval('imageNumberBoard' + ('00' + k).slice(-2) + j))});
            }
            console.log(hands);
            allHands.push(judge(hands));
        }    
    }

    console.log(allHands);
    if(playerNum == 1){
        document.getElementById("judgeHandmain").innerHTML = `${allHands[0]["description"]}`;
    }else{
        document.getElementById("judgeHandmain").innerHTML = `Winner : ${isWinner(allHands)}`;
        document.getElementById("judgeHandsub").innerHTML = `Player 1 : <font color="red">${allHands[0]["description"]}</font><br>Player 2 : <font color="blue">${allHands[1]["description"]}</font>`;
    }
    
    return;
}