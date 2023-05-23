function makeHands(playerNum){
    let hands = [];

    for(let i=1; i <= playerNum; i++){
        hands.push({suit: eval('imageTypeHand' + i + '1'), number: eval('imageNumberHand' + i + '1')});
        hands.push({suit: eval('imageTypeHand' + i + '2'), number: eval('imageNumberHand' + i + '2')});
    }    
    for(let i=1; i <= 5; i++){
        hands.push({suit: eval('imageTypeBoard' + i), number: eval('imageNumberBoard' + i)});
    }
    
    // console.log(hands);
    judge(hands);
    return;
}