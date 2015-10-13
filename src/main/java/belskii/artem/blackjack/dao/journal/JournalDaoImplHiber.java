package belskii.artem.blackjack.dao.journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class JournalDaoImplHiber implements JournalDao{
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public ArrayList getUserJournal(long userId) {
		ArrayList<Journal> arr=new ArrayList<Journal>();
		Transaction transaction = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			arr = (ArrayList<Journal>) session.createCriteria(Journal.class).add(Restrictions.eq("userId", userId)).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return arr;
	}

	public void newIvent(long userId, String action, long accountId, long amount) {
		Transaction transaction = null;
		Session session = null;
		Journal ivent=new Journal(action,userId, accountId, amount);
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(ivent);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

		
	}

}
