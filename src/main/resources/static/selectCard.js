function makeHands(){
    let hands = [];

    hands.push({suit:imageTypeHand1, number:imageNumberHand1});
    hands.push({suit:imageTypeHand2, number:imageNumberHand2});
    hands.push({suit:imageTypeBoard1, number:imageNumberBoard1});
    hands.push({suit:imageTypeBoard2, number:imageNumberBoard2});
    hands.push({suit:imageTypeBoard3, number:imageNumberBoard3});
    hands.push({suit:imageTypeBoard4, number:imageNumberBoard4});
    hands.push({suit:imageTypeBoard5, number:imageNumberBoard5});

    // console.log(hands);
    judge(hands);
    return;
}