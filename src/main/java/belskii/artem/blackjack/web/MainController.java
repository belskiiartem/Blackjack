package belskii.artem.blackjack.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import belskii.artem.blackjack.dao.account.AccountDao;
import belskii.artem.blackjack.dao.account.AccountDaoImplHiber;
import belskii.artem.blackjack.dao.gamer.Gamer;
import belskii.artem.blackjack.dao.gamer.GamerDao;
import belskii.artem.blackjack.dao.gamer.GamerDaoImplHiber;
import belskii.artem.blackjack.dao.journal.JournalDao;
import belskii.artem.blackjack.dao.journal.JournalDaoImplHiber;
import belskii.artem.blackjack.game.Game;

@Controller
public class MainController {
	
	private GamerDao gamer=new GamerDaoImplHiber();
	private AccountDao account = new AccountDaoImplHiber();
	private JournalDao journal = new JournalDaoImplHiber();
	private Game game = new Game();
	private Long BALANCE = 5000L;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "main";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("cardId") String cardId, HttpServletResponse response){
		String viewName="newUser";
		ModelAndView modelAndView = new ModelAndView();
		if(account.findCard(cardId)>0){
			viewName="userZone";
			Gamer gamerInfo = gamer.getUserInfo(cardId);
			modelAndView.addObject("firstName", gamerInfo.getFirstName());
			modelAndView.addObject("lastName", gamerInfo.getLastName());
			modelAndView.addObject("account",cardId);
			modelAndView.addObject("balance", account.getBalance(account.findCard(cardId)));
			modelAndView.addObject("journal", journal.getUserJournal(gamerInfo.getId()));
		}
		response.addCookie(new Cookie("cardId", cardId));
		modelAndView.setViewName(viewName);
		return modelAndView;
	}
	
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public ModelAndView newUser(@ModelAttribute("firstName") String firstName, @ModelAttribute("lastName") String lastName,
								@ModelAttribute("cardId") String cardId){
		AccountDao account = new AccountDaoImplHiber();
		account.addAccount(cardId, BALANCE);
		gamer.addGamer(firstName, lastName, account.findCard(cardId));
		Gamer gamerInfo = gamer.getUserInfo(cardId);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("firstName", gamerInfo.getFirstName());
		modelAndView.addObject("lastName", gamerInfo.getLastName());
		modelAndView.addObject("account",cardId);
		modelAndView.addObject("balance", account.getBalance(account.findCard(cardId)));
		modelAndView.addObject("journal", journal.getUserJournal(gamerInfo.getId()));
		
		modelAndView.setViewName("userZone");
		return modelAndView;
	}

	@RequestMapping(path="/startGame", method=RequestMethod.POST)
	public ModelAndView startGame(@ModelAttribute("bet") long bet, HttpServletRequest request, HttpServletResponse response){
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
		response.addCookie(new Cookie("bet", String.valueOf(bet)));
	
		game.startGame(jSessionId, bet);
		
		if (game.getBankCount(jSessionId)==21){
			modelAndView.setViewName("bankWon");
			game.getResult(jSessionId, gamer.getUserInfo(cardId).getId(), account.findCard(cardId), bet);
		} else if(game.getGamerCount(jSessionId)==21){
			modelAndView.setViewName("gamerZone");
			game.getResult(jSessionId, gamer.getUserInfo(cardId).getId(), account.findCard(cardId), bet);
		} else {
			modelAndView.setViewName("gameZone");
		}
		
		modelAndView.addObject("bankCardsOnHend", game.getBankCardsOnHend(jSessionId));
		modelAndView.addObject("bankCount", game.getBankCount(jSessionId));
		modelAndView.addObject("gamerCardsOnHend", game.getGamerCardsOnHend(jSessionId));
		modelAndView.addObject("gamerCount", game.getGamerCount(jSessionId));
		
		return modelAndView;
		}

	@RequestMapping(value="/game", method=RequestMethod.POST)
	public ModelAndView game(@ModelAttribute("action") String action, HttpServletRequest request){
		String cardId="";
		String jSessionId="";
		long bet=0;
		Cookie[] cookies = request.getCookies();
		String viewName="gameZone";
		ModelAndView modelAndView = new ModelAndView();
		
		if (cookies != null) {
	        for (int i = 0; i < cookies.length; i++) {
	        	if (cookies[i].getName().equals("cardId")){
	        		cardId=cookies[i].getValue().toString();
	        	}
	        	if (cookies[i].getName().toLowerCase().equals("jsessionid")){
	        		jSessionId=cookies[i].getValue().toString();
	        	}
	        	if (cookies[i].getName().toLowerCase().equals("bet")){
	        		bet=Long.valueOf(cookies[i].getValue());
	        	}

	        }
		}
		
		if (action.equals("hit") & game.getGamerCount(jSessionId)<=21 & game.getBankCount(jSessionId)<=21){
			game.gamerHit(jSessionId);
			game.bankHit(jSessionId);
			modelAndView.setViewName(viewName);
			modelAndView.addObject("bankCardsOnHend", game.getBankCardsOnHend(jSessionId));
			modelAndView.addObject("bankCount", game.getBankCount(jSessionId));
			modelAndView.addObject("gamerCardsOnHend", game.getGamerCardsOnHend(jSessionId));
			modelAndView.addObject("gamerCount", game.getGamerCount(jSessionId));
		} else {
			game.getResult(jSessionId, gamer.getUserInfo(cardId).getId(), account.findCard(cardId), bet);
			modelAndView.setViewName(viewName);
			modelAndView.addObject("bankCardsOnHend", game.getBankCardsOnHend(jSessionId));
			modelAndView.addObject("bankCount", game.getBankCount(jSessionId));
			modelAndView.addObject("gamerCardsOnHend", game.getGamerCardsOnHend(jSessionId));
			modelAndView.addObject("gamerCount", game.getGamerCount(jSessionId));
		}


		return modelAndView;
	}

}