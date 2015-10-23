package belskii.artem.blackjack.dao.gamer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=
@UniqueConstraint(columnNames = {"accountId"}))
public class Gamer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	private String firstName;
	private String lastName;
	private long accountId;
	
	public Gamer(){}
	
	public Gamer(String firstName, String lastName, long accountID) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAccountId(accountID);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}