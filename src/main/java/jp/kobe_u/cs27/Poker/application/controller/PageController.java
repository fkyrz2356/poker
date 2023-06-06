package jp.kobe_u.cs27.Poker.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import jakarta.servlet.http.HttpSession;

import jp.kobe_u.cs27.Poker.application.bean.Card;
import jp.kobe_u.cs27.Poker.application.Service.CardService;
import jp.kobe_u.cs27.Poker.application.bean.User;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    private final CardService cardService;

    @Autowired
    public PageController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/practice")
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

	@PostMapping("/challenge")
    public String pageController3(@RequestParam("currentDate") String currentDate, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("currentDate", currentDate);
        model.addAttribute("user", user);
		model.addAttribute("playerNum", 2);
		model.addAttribute("boardNum", 50);
		String[] handPath11 = new String[55];
		String[] handPath12 = new String[55];
		String[] handPath21 = new String[55];
		String[] handPath22 = new String[55];
		String[] boardPath1 = new String[55];
		String[] boardPath2 = new String[55];
		String[] boardPath3 = new String[55];
		String[] boardPath4 = new String[55];
		String[] boardPath5 = new String[55];

		handPath11[0] = "/card/black.jpg";
		handPath12[0] = "/card/black.jpg";
		handPath21[0] = "/card/black.jpg";
		handPath22[0] = "/card/black.jpg";
		boardPath1[0] = "/card/black.jpg";
		boardPath2[0] = "/card/black.jpg";
		boardPath3[0] = "/card/black.jpg";
		boardPath4[0] = "/card/black.jpg";
		boardPath5[0] = "/card/black.jpg";

		for(int num=1; num<=50; num++){
			List<Card> allCards = cardService.generateAllCards();
			String numStr = String.format("%02d", num); // 2桁の文字列に変換
			int[] hands = {11, 12, 21, 22};
			int[] boards = {1, 2, 3, 4, 5};

			for (int i = 0; i < hands.length; i++) {
				model.addAttribute("imageTypeHand" + numStr + Integer.valueOf(hands[i]).toString(), allCards.get(i).getImageType());
				model.addAttribute("imageNumberHand" + numStr + Integer.valueOf(hands[i]).toString(), allCards.get(i).getImageNumber());
			}
			handPath11[num] = "/card/" + allCards.get(0).getImageType() + "/" + allCards.get(0).getImageNumber() + ".png";
			handPath12[num] = "/card/" + allCards.get(1).getImageType() + "/" + allCards.get(1).getImageNumber() + ".png";
			handPath21[num] = "/card/" + allCards.get(2).getImageType() + "/" + allCards.get(2).getImageNumber() + ".png";
			handPath22[num] = "/card/" + allCards.get(3).getImageType() + "/" + allCards.get(3).getImageNumber() + ".png";
			
			for (int i = 0; i < boards.length; i++) {
				model.addAttribute("imageTypeBoard" + numStr +  Integer.valueOf(boards[i]).toString(), allCards.get(i + hands.length).getImageType());
				model.addAttribute("imageNumberBoard" + numStr +  Integer.valueOf(boards[i]).toString(), allCards.get(i + hands.length).getImageNumber());
			}
			boardPath1[num] = "/card/" + allCards.get(4).getImageType() + "/" + allCards.get(4).getImageNumber() + ".png";
			boardPath2[num] = "/card/" + allCards.get(5).getImageType() + "/" + allCards.get(5).getImageNumber() + ".png";
			boardPath3[num] = "/card/" + allCards.get(6).getImageType() + "/" + allCards.get(6).getImageNumber() + ".png";
			boardPath4[num] = "/card/" + allCards.get(7).getImageType() + "/" + allCards.get(7).getImageNumber() + ".png";
			boardPath5[num] = "/card/" + allCards.get(8).getImageType() + "/" + allCards.get(8).getImageNumber() + ".png";
		}

		model.addAttribute("handPath11", handPath11);
		model.addAttribute("handPath12", handPath12);
		model.addAttribute("handPath21", handPath21);
		model.addAttribute("handPath22", handPath22);
		model.addAttribute("boardPath1", boardPath1);
		model.addAttribute("boardPath2", boardPath2);
		model.addAttribute("boardPath3", boardPath3);
		model.addAttribute("boardPath4", boardPath4);
		model.addAttribute("boardPath5", boardPath5);

		return "challenge";
    }

	@GetMapping("/version")
    public String pageController4(Model model) {

		return "versionHistory";
    }

}
