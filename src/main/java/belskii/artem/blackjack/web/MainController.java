package belskii.artem.blackjack.web;

import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import belskii.artem.blackjack.dao.account.Account;
import belskii.artem.blackjack.dao.account.AccountDao;
import belskii.artem.blackjack.dao.account.AccountDaoImplHiber;
import belskii.artem.blackjack.dao.gamer.Gamer;
import belskii.artem.blackjack.dao.gamer.GamerDao;
import belskii.artem.blackjack.dao.gamer.GamerDaoImplHiber;
import belskii.artem.blackjack.dao.journal.Journal;
import belskii.artem.blackjack.dao.journal.JournalDao;
import belskii.artem.blackjack.dao.journal.JournalDaoImplHiber;
import belskii.artem.blackjack.game.Game;

@Controller
public class MainController {
	
	private GamerDao gamer=new GamerDaoImplHiber();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "main";
	}
	
	@RequestMapping(value = "/userzone", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("cardId") String cardId, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userZone");
		Gamer gamerInfo = gamer.getUserInfo(cardId);
		AccountDao acc = new AccountDaoImplHiber(); 
		Account userAccount = acc.getInfo(gamerInfo.getId());
		JournalDao journal = new JournalDaoImplHiber();
		ArrayList userJournal = (ArrayList) journal.getUserJournal(gamerInfo.getId());
		
		modelAndView.addObject("gamerInfo",gamerInfo);
		modelAndView.addObject("account",userAccount);
		modelAndView.addObject("journal",userJournal);
		response.addCookie(new Cookie("cardId", cardId));
		return modelAndView;
	}

	@RequestMapping(value="/startGame", method = RequestMethod.POST)
	public ModelAndView startGame(@ModelAttribute("bet") String bet, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String cardId="";
		String jSessionId="";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
	        for (int i = 0; i < cookies.length; i++) {
	        	if (cookies[i].getName().equals("cardId")){
	        		cardId=cookies[i].getValue().toString();
	        	}
	        	if (cookies[i].getName().toLowerCase().equals("jsessionid")){
	        		jSessionId=cookies[i].getValue().toString();
	        	}

	        }
		}
		Game game = new Game();
		ArrayList deck = game.startGame(Long.valueOf(bet), jSessionId);
		ArrayList bankCard=(ArrayList) deck.get(0);
		ArrayList gamerCard=(ArrayList) deck.get(1);
		modelAndView.setViewName("gameZone");
		modelAndView.addObject("bankCard",bankCard);
		modelAndView.addObject("gamerCard",gamerCard);
		System.out.println("jSessionId: " + jSessionId);
		return modelAndView;
	}

//	@RequestMapping(value="/game", method = RequestMethod.POST)
//	public ModelAndView game(@ModelAttribute("bet") String bet, HttpServletRequest request){
//		
//	}
	
}