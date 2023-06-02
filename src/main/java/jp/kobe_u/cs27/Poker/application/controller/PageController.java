package jp.kobe_u.cs27.Poker.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import jp.kobe_u.cs27.Poker.application.bean.Card;
import jp.kobe_u.cs27.Poker.application.Service.CardService;

@Controller
public class PageController {

    private final CardService cardService;

    @Autowired
    public PageController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/")
    public String pageController1(Model model) {
		List<Card> allCards = cardService.generateAllCards();

		String[] hands = {"Hand0111", "Hand0112"};
		String[] boards = {"Board011", "Board012", "Board013", "Board014", "Board015"};

		model.addAttribute("playerNum", 1);
		model.addAttribute("boardNum", 1);

		for (int i = 0; i < hands.length; i++) {
			model.addAttribute("imageType" + hands[i], allCards.get(i).getImageType());
			model.addAttribute("imageNumber" + hands[i], allCards.get(i).getImageNumber());
		}

		for (int i = 0; i < boards.length; i++) {
			model.addAttribute("imageType" + boards[i], allCards.get(i + hands.length).getImageType());
			model.addAttribute("imageNumber" + boards[i], allCards.get(i + hands.length).getImageNumber());
		}

		return "practice";
    }

    @GetMapping("/twoPlayers")
    public String pageController2(Model model) {
		List<Card> allCards = cardService.generateAllCards();

		/*
		Card card1 = new Card();
        card1.setImageType("diamond");
        card1.setImageNumber("7");
		allCards.set(0, card1);
		Card card2 = new Card();
        card2.setImageType("heart");
        card2.setImageNumber("1");
		allCards.set(1, card2);
		Card card3 = new Card();
        card3.setImageType("spade");
        card3.setImageNumber("6");
		allCards.set(2, card3);
		Card card4 = new Card();
        card4.setImageType("diamond");
        card4.setImageNumber("1");
		allCards.set(3, card4);
		Card card5 = new Card();
        card5.setImageType("heart");
        card5.setImageNumber("7");
		allCards.set(4, card5);
		Card card6 = new Card();
        card6.setImageType("club");
        card6.setImageNumber("4");
		allCards.set(5, card6);
		Card card7 = new Card();
        card7.setImageType("heart");
        card7.setImageNumber("11");
		allCards.set(6, card7);
		Card card8 = new Card();
        card8.setImageType("spade");
        card8.setImageNumber("4");
		allCards.set(7, card8);
		Card card9 = new Card();
        card9.setImageType("club");
        card9.setImageNumber("1");
		allCards.set(8, card9);
		*/

		String[] hands = {"Hand0111", "Hand0112", "Hand0121", "Hand0122"};
		String[] boards = {"Board011", "Board012", "Board013", "Board014", "Board015"};

		model.addAttribute("playerNum", 2);
		model.addAttribute("boardNum", 1);

		for (int i = 0; i < hands.length; i++) {
			model.addAttribute("imageType" + hands[i], allCards.get(i).getImageType());
			model.addAttribute("imageNumber" + hands[i], allCards.get(i).getImageNumber());
		}

		for (int i = 0; i < boards.length; i++) {
			model.addAttribute("imageType" + boards[i], allCards.get(i + hands.length).getImageType());
			model.addAttribute("imageNumber" + boards[i], allCards.get(i + hands.length).getImageNumber());
		}

		return "twoPlayers";
    }

	@GetMapping("/version")
    public String pageController4(Model model) {

		return "versionHistory";
    }

}
