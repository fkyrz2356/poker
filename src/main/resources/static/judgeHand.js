function judge(targetCards){
    let ans = "";
    if(IsRoyalFlush(targetCards)) ans =  "ロイヤルストレートフラッシュ";
    else if(IsStraightFlush(targetCards)) ans =  "ストレートフラッシュ";
    else if(IsSameNumber(targetCards, 4)) ans =  "クアッズ";
    else if(IsFullHouse(targetCards)) ans =  "フルハウス";
    else if(IsFlush(targetCards)) ans =  "フラッシュ";
    else if(IsStraight(targetCards)) ans =  "ストレート";
    else if(IsSameNumber(targetCards, 3)) ans =  "スリーカード";
    else if(IsTwoPairs(targetCards)) ans =  "ツーペア";
    else if(IsSameNumber(targetCards, 2)) ans =  "ワンペア";
    else ans = "ハイカード";

    document.getElementById("judgeHand").innerHTML = ans;
    return;
}


function IsFlush(targetCards){
	var suits = { "spade":0, "heart":0, "diamond":0, "club":0};

	for(var i = 0; i < targetCards.length; i++){
		suits[targetCards[i]["suit"]]++;
	}

	for(var Key in suits ){
		if(suits[Key] >= 5) return true;
	}

	return false;
}

function IsSameNumber(targetCards, sameCount){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0};

	for(var i = 0; i < targetCards.length; i++){
		numbers[targetCards[i]["number"]]++;
	}

	for(var i = 1; i <= 13; i++){
		if(numbers[i] >= sameCount)  return true;
	}

    return false;
}

function IsTwoPairs(targetCards ){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0};

	for(var i = 0; i < targetCards.length; i++){
		numbers[targetCards[i]["number"]]++;
	}

	var sameCount = {0:0, 1:0, 2:0, 3:0, 4:0};

	for(var i = 1; i <= 13; i++){
		sameCount[numbers[i]]++;
	}

    return (sameCount[2] >= 2);
}

function IsFullHouse(targetCards){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0};

	for(var i = 0; i < targetCards.length; i++){
		numbers[targetCards[i]["number"]]++;
	}

	//「同じ数字が○枚」がいくつあったのかを数える入れ物を用意
	var sameCount = {0:0, 1:0, 2:0, 3:0, 4:0};

	//カウントした数字を集計
	for(var i = 1; i <= 13; i++){
		sameCount[numbers[i]]++;
	}

	return (sameCount[3] >= 2 || (sameCount[3] >= 1 && sameCount[2] >= 1));
}

function IsStraight(targetCards){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0, 14:0};

	for( var i=0; i<targetCards.length; i++ ){
		numbers[targetCards[i]["number"]]++;
		if(targetCards[i]["number"] == 1) numbers[14]++;
	}

	for(var i = 1;i <= 10; i++){
		if(numbers[i] >= 1 && numbers[i+1] >= 1 && numbers[i+2] >= 1 && numbers[i+3] >= 1 && numbers[i+4] >= 1){
			return true;
		}
	}

	return false;
}

function IsStraightFlush(targetCards){
	var suits = { "spade":[], "heart":[], "diamond":[], "club":[] };

	for(var i = 0; i < targetCards.length; i++){
		suits[targetCards[i]["suit"]].push(targetCards[i]);
	}

	var flag = false;

	Object.keys(suits).forEach(function (key) {
		if(suits[key].length >= 5 && IsStraight(suits[key])){
			flag = true;
		}
	});

	return flag;
}

function IsRoyalFlush(targetCards){
	var cards = [];

	for(var i = 0; i < targetCards.length; i++){
		if(targetCards[i]["number"] <= 9 && targetCards[i]["number"] != 1) continue;
		cards.push(targetCards[i]);
	}

    return IsStraightFlush(cards);
}