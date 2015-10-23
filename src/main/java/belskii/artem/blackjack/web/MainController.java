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
	private AccountDao account = new AccountDaoImplHiber();
	private JournalDao journal = new JournalDaoImplHiber();
	private Long BALANCE = 5000L;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "main";
	}
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("cardId") String cardId, HttpServletResponse response){
		String viewName="newUser";
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(account.findCard(cardId));
		if(account.findCard(cardId)>0){
			viewName="userZone";
			Gamer gamerInfo = gamer.getUserInfo(cardId);
			System.out.println(gamerInfo.getFirstName());
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
		System.out.println(cardId);
		System.out.println(account.findCard(cardId));
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
}