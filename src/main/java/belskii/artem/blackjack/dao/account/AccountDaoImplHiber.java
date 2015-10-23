package belskii.artem.blackjack.dao.account;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import belskii.artem.blackjack.dao.gamer.Gamer;
import belskii.artem.blackjack.dao.journal.Journal;

public class AccountDaoImplHiber implements AccountDao{
	
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private Account fetchDate( long id){
		Transaction transaction = null;
		Session session = null;
		Account result=null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = session.get(Account.class,id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}
	
	public void addAccount(String cardNumber, long balance) {
		Transaction transaction = null;
		Session session = null;
		Account account=new Account();
		account.setCardNumber(cardNumber);
		account.setBalance(balance);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(account);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	public String getCardNumber(long id) {
		return this.fetchDate(id).getCardNumber();
	}

	public long getBalance(long id) {
		return this.fetchDate(id).getBalance();
	}

	public void updateBalance(long id, long newBalance) {
		Transaction transaction = null;
		Session session = null;
		Account account=this.fetchDate(id);
		account.setBalance(newBalance);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(account);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
	}

	
	public long findCard(String cardNumber) {
		Transaction transaction = null;
		Session session = null;
			long result = -1;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			//need to refactor!!! (can't find string value)
			//result=(ArrayList<Account>) session.createCriteria(Account.class).add(Restrictions.eq("balance", 10L)).list(); //work!
			//result=(ArrayList<Account>) session.createCriteria(Account.class).add(Restrictions.eq("cardNumber", cardNumber)).list(); //null
			ArrayList list = (ArrayList) session.createCriteria(Account.class).list();
			for (int i=0; i<list.size(); i++){
				Account acc = (Account) list.get(i);
					if (acc.getCardNumber().equals(cardNumber)){
						result=acc.getId();
					}
						}


			transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
			} finally {
				session.close();
			}
		return result;
	}

	public Account getInfo(long accId) {
		return this.fetchDate(accId);
	}
}
