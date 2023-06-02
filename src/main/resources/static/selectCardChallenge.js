let whichIsWinner = [null];
function makeHands(){
    for(let k=1; k <= boardNum; ++k){
        let allHands = [];
        for(let i=1; i <= playerNum; i++){
            let hands = [];
            hands.push({suit: eval('imageTypeHand' + ('00' + k).slice(-2) + i + '1'), number: Number(eval('imageNumberHand' + ('00' + k).slice(-2) + i + '1'))});
            hands.push({suit: eval('imageTypeHand' + ('00' + k).slice(-2) + i + '2'), number: Number(eval('imageNumberHand' + ('00' + k).slice(-2) + i + '2'))});
            for(let j=1; j <= 5; j++){
                hands.push({suit: eval('imageTypeBoard' + ('00' + k).slice(-2) + j), number: Number(eval('imageNumberBoard' + ('00' + k).slice(-2) + j))});
            }
            //console.log(hands);
            allHands.push(judge(hands));
        }    
        //console.log(allHands);
        document.getElementById("judgeHandmain" + ('00' + k).slice(-2)).innerHTML = `Winner : ${isWinnerTxt(allHands)}`;
        document.getElementById("judgeHandsub" + ('00' + k).slice(-2)).innerHTML = `Player 1 : <font color="red">${allHands[0]["description"]}</font><br>Player 2 : <font color="blue">${allHands[1]["description"]}</font>`;
    }
    console.log(whichIsWinner);
    
    return;
}

function isWinnerTxt(hands){
	whichIsWinner.push(isWinner(hands));
	if(isWinner(hands) == 1){
		return '<font color="red">Player 1</font>';
	}else if(isWinner(hands) == 2){
		return '<font color="blue">Player 2</font>';
	}else{
		return "Chop";
	}
}