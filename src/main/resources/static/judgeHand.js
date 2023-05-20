function judge(targetCards){
    let ans;
    if(IsRoyalFlush(targetCards)["flag"]) ans = IsRoyalFlush(targetCards);
    else if(IsStraightFlush(targetCards)["flag"]) ans = IsStraightFlush(targetCards);
    else if(IsSameNumber(targetCards, 4)["flag"]) ans = IsSameNumber(targetCards, 4);
    else if(IsFullHouse(targetCards)["flag"]) ans =  IsFullHouse(targetCards);
    else if(IsFlush(targetCards)["flag"]) ans = IsFlush(targetCards);
    else if(IsStraight(targetCards)["flag"]) ans = IsStraight(targetCards);
    else if(IsSameNumber(targetCards, 3)["flag"]) ans = IsSameNumber(targetCards, 3);
    else if(IsTwoPairs(targetCards)["flag"]) ans = IsTwoPairs(targetCards);
    else if(IsSameNumber(targetCards, 2)["flag"]) ans = IsSameNumber(targetCards, 2);
    else ans = IsHighCard(targetCards);

    document.getElementById("judgeHand").textContent = ans["name"];
    return;
}

function modifyAndSort(arr, num, arr1 = []) {
	// 配列の中の1をすべて14に変える
	for(let i = 0; i < arr.length; i++) {
	  if(arr[i] === 1) {
		arr[i] = 14;
	  }
	}
  
	// 配列を大きい順に並べる
	arr.sort(function(a, b) {
	  return b - a;
	});
	
	// 数字の分だけ配列を切り出し、arr1と連結する
	return arr1.concat(arr.slice(0, num));
}

function IsFlush(targetCards){
    var suits = { "spade":[], "heart":[], "diamond":[], "club":[]};

    for(var i = 0; i < targetCards.length; i++){
        suits[targetCards[i]["suit"]].push(targetCards[i]["number"]);
    }

    for(var Key in suits ){
        if(suits[Key].length >= 5) return  {"flag":true, "strength":6, "cards":modifyAndSort(suits[Key], 5), "name":"フラッシュ"};
    }

    return {"flag":false};
}


function IsSameNumber(targetCards, sameCount){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0};
	var arr = [];

	for(var i = 0; i < targetCards.length; i++){
		numbers[targetCards[i]["number"]]++;
		arr.push(targetCards[i]["number"]);
	}

	let ans = [];

	for(var i = 1; i <= 13; i++){
		if(numbers[i] >= sameCount){
			for (let j = 0; j < numbers[i]; j++) {
				if(i > 1) ans.push(i);
				else ans.push(14);
			}
			arr = arr.filter(item => item !== i);
			if(numbers[i] == 2){
				return {"flag":true, "strength":2, "cards":modifyAndSort(arr, 3, ans), "name":"ワンペア"}
			}else if(numbers[i] == 3){
				if(targetCards[0]["number"] == i && targetCards[1]["number"] == i){
					return {"flag":true, "strength":4, "cards":modifyAndSort(arr, 2, ans), "name":"セット"}
				}else if(targetCards[0]["number"] == i || targetCards[1]["number"] == i){
					return {"flag":true, "strength":4, "cards":modifyAndSort(arr, 2, ans), "name":"トリプス"}
				}else{
					return {"flag":true, "strength":4, "cards":modifyAndSort(arr, 2, ans), "name":"スリーカード"}
				}
			}else{
				return {"flag":true, "strength":8, "cards":modifyAndSort(arr, 1, ans), "name":"クアッズ"}
			}
		}
	}

    return {"flag":false};
}

function IsTwoPairs(targetCards ){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0};
	var arr = [];

	for(var i = 0; i < targetCards.length; i++){
		numbers[targetCards[i]["number"]]++;
		arr.push(targetCards[i]["number"]);
	}

	var sameCount = {0:[], 1:[], 2:[], 3:[], 4:[]};

	for(var i = 1; i <= 13; i++){
		sameCount[numbers[i]].push(i);
	}

	if(sameCount[2].length >= 2){
		let ans = sameCount[2];
		ans.sort((a, b) => b - a);
		ans = ans.slice(0, 2);
		for(let i = 0; i < ans.length; i++) {
			arr = arr.filter(item => item !== ans[i]);
			if(ans[i] == 1) {
				ans[i] = 14;
			}
		}
		let ansCopy = ans.slice();
		ans = ans.concat(ansCopy);

		ans.sort((a, b) => b - a);
		return {"flag":true, "strength":3, "cards":modifyAndSort(arr, 1, ans), "name":"ツーペア"};
	}
	return {"flag":false};
}

function IsFullHouse(targetCards){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0};

	for(var i = 0; i < targetCards.length; i++){
		numbers[targetCards[i]["number"]]++;
	}

	//「同じ数字が○枚」がいくつあったのかを数える入れ物を用意
	var sameCount = {0:[], 1:[], 2:[], 3:[], 4:[]};

	//カウントした数字を集計
	for(var i = 1; i <= 13; i++){
		sameCount[numbers[i]].push(i);
	}

	if(sameCount[3].length >= 2){
		var a = sameCount[3][0], b = sameCount[3][1];
		if (a == 1) a = 14;
		if (b == 1) b = 14;
		if(a < b) [a, b] = [b, a];
		return {"flag":true, "strength":7, "cards":[a, a, a, b, b], "name":"フルハウス"};
	}else if(sameCount[3].length >= 1 && sameCount[2].length >= 1){
		sameCount[2].sort((a, b) => b - a);
		var a = sameCount[3][0], b = sameCount[2][0];
		return {"flag":true, "strength":7, "cards":[a, a, a, b, b], "name":"フルハウス"};
	}else{
		return {"flag":false};
	}
}

function IsStraight(targetCards){
	var numbers = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0, 10:0, 11:0, 12:0, 13:0, 14:0};

	for( var i=0; i<targetCards.length; i++ ){
		numbers[targetCards[i]["number"]]++;
		if(targetCards[i]["number"] == 1) numbers[14]++;
	}

	for(var i = 10; i >= 1; i--){
		if(numbers[i] >= 1 && numbers[i+1] >= 1 && numbers[i+2] >= 1 && numbers[i+3] >= 1 && numbers[i+4] >= 1){
			return {"flag":true, "strength":5, "cards":[i+4, i+3, i+2, i+1, i], "name":"ストレート"};
		}
	}

	return {"flag":false};
}

function IsStraightFlush(targetCards){
	var suits = { "spade":[], "heart":[], "diamond":[], "club":[] };

	for(var i = 0; i < targetCards.length; i++){
		suits[targetCards[i]["suit"]].push(targetCards[i]);
	}

	let ans = {"flag":false};
	Object.keys(suits).forEach(function (key) {
		if(suits[key].length >= 5 && IsStraight(suits[key])["flag"]){
			let ansCards = IsStraight(suits[key])["cards"];
			ans = {"flag":true, "strength":9, "cards":ansCards, "name":"ストレートフラッシュ"};
		}
	});

	return ans;
}

function IsRoyalFlush(targetCards){
	var cards = [];

	for(var i = 0; i < targetCards.length; i++){
		if(targetCards[i]["number"] <= 9 && targetCards[i]["number"] != 1) continue;
		cards.push(targetCards[i]);
	}

	if(IsStraightFlush(cards)["flag"]){
		return {"flag":true, "strength":10, "cards":[14, 13, 12, 11, 10], "name":"ロイヤルストレートフラッシュ"};
	}else{
		return {"flag":false};
	}
}

function IsHighCard(targetCards){
	let ary = [];
	for(var i = 0; i < targetCards.length; ++i){	
		if(targetCards[i]["number"] > 1) ary.push(targetCards[i]["number"]);
		else ary.push(14);
	}
	ary.sort((a, b) => b - a);
	return {"flag":true, "strength":1, "cards":modifyAndSort(ary, 5), "name":"ハイカード"};
}
