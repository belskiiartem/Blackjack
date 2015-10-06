package belskii.artem.blackjack.dao.gamer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class GamerDaoImplHiber implements GamerDao{
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	private Gamer fetchData(long gamerId){
		Transaction transaction = null;
		Session session = null;
		Gamer gamer=null;
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
	public void setName(String firstName, String lastName) {
		Transaction transaction = null;
		Session session = null;
		Gamer user=null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			user=new Gamer();
			user.setFirstName(firstName);
			user.setLastName(lastName);
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
	
}
