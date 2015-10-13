package belskii.artem.blackjack.dao.journal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenerationTime;

@Entity
public class Journal {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	private String actionType;
	private long userId;
	private long accountId;
	private long amount;
	private Date date=new Date();
	
	public Journal(){}
	
	public Journal(String actionType, long userId, long accountId, long amount){
		this.setActionType(actionType);
		this.setUserId(userId);
		this.setAmount(amount);
		this.setAccountId(accountId);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String action) {
		this.actionType = action;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
