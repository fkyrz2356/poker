package jp.kobe_u.cs27.Poker.application.controller.view;

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

    @GetMapping("/test")
    public String pageController2(Model model) {
		List<Card> allCards = cardService.generateAllCards();

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

}
