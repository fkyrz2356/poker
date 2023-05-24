package jp.kobe_u.cs27.Poker.application.Service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import jp.kobe_u.cs27.Poker.application.bean.Card;

@Service
public class CardService {

    public List<Card> generateAllCards() {
        String[] suits = {"club", "heart", "diamond", "spade"};
        List<Integer> numbers = IntStream.rangeClosed(1, 13).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        // 全てのカードの組み合わせを生成
        List<Card> allCards = new ArrayList<>();
        for (String suit : suits) {
            for (Integer number : numbers) {
                Card card = new Card();
                card.setImageType(suit);
                card.setImageNumber(number.toString());
                allCards.add(card);
            }
        }

        // 重複を避けるために、全てのカードをシャッフル
        Collections.shuffle(allCards);

        return allCards;
    }
}
