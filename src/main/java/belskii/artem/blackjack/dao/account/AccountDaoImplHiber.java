package belskii.artem.blackjack.dao.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import belskii.artem.blackjack.dao.user.Users;

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
	
	public void addAccount(String cardId, long balance) {
		Transaction transaction = null;
		Session session = null;
		Account account=new Account();
		account.setCardId(cardId);
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

	public String getCardId(long id) {
		return this.fetchDate(id).getCardId();
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

}
