package belskii.artem.blackjack.dao.party;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class PartyDaoImplHiber implements PartyDao {
	Party party = null;
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	PartyDaoImplHiber() {
		party = new Party();
	}

	public void shuffleDeck(String partyId) {
		String[] color = { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] rank = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		ArrayList<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < color.length; i++) {
			for (int i2 = 0; i2 < rank.length; i2++) {
				deck.add(new Card(color[i], rank[i2]));
			}
		}

		Transaction transaction = null;
		Session session = null;
		try {
			Collections.shuffle(deck);
			party.setPartyId(partyId);
			party.setDeck(deck);
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(party);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

	}

	public List<Card> gamerCardsOnHend(String partyId) {
		return this.getCurrentPaty(partyId).getGamerCardsOnHend();
	}

	public List<Card> bankCardsOnHend(String partyId) {
		return this.getCurrentPaty(partyId).getBankCardsOnHend();
	}

	public Party gamerHit(String partyId) {
		Transaction transaction = null;
		Session session = null;
		Party paty = new Party();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			paty = this.getCurrentPaty(partyId);
			ArrayList<Card> empty = new ArrayList<Card>();
			paty.gamerHit();
			session.saveOrUpdate(paty);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return paty;
	}

	public Party bankHit(String partyId) {
		Transaction transaction = null;
		Session session = null;
		Party paty = new Party();
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			paty = this.getCurrentPaty(partyId);
			paty.bankHit();
			session.saveOrUpdate(paty);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return paty;
	}

	private Party getCurrentPaty(String partyId) {
		Transaction transaction = null;
		Session session = null;
		Party party = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			party = (Party) session.createCriteria(Party.class).add(Restrictions.eq("partyId", partyId)).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Exception:");
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return party;
	}

}
