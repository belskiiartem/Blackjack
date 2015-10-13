package belskii.artem.blackjack.dao.gamer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import belskii.artem.blackjack.dao.account.Account;
import belskii.artem.blackjack.dao.account.AccountDao;
import belskii.artem.blackjack.dao.account.AccountDaoImplHiber;
import belskii.artem.blackjack.dao.journal.Journal;
import belskii.artem.blackjack.dao.journal.JournalDao;
import belskii.artem.blackjack.dao.journal.JournalDaoImplHiber;

public class GamerDaoImplHiber implements GamerDao {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	private Gamer fetchData(long gamerId) {
		Transaction transaction = null;
		Session session = null;
		Gamer gamer = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			gamer = session.get(Gamer.class, gamerId);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return gamer;
	}

	public void addGamer(String firstName, String lastName) {
		Transaction transaction = null;
		Session session = null;
		Gamer user = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			user = new Gamer(firstName, lastName);
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public String getFirstName(long gamerId) {
		return this.fetchData(gamerId).getFirstName();
	}

	public String getLastName(long gamerId) {
		return this.fetchData(gamerId).getLastName();
	}

	public String getAccountId(long gamerId) {
		return this.fetchData(gamerId).getAccountId();
	}

	public Gamer getUserInfo(String cardId) {
		AccountDao acc = new AccountDaoImplHiber();
		long accId = acc.findCard(cardId);
		
		Transaction transaction = null;
		Session session = null;
		Gamer gamer = new Gamer();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
   			Criteria criteria = session.createCriteria(Gamer.class);
	        criteria.add(Restrictions.eq("accountId", String.valueOf(accId)));
	        gamer = (Gamer) criteria.uniqueResult();
	        transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return gamer;
	}

}
