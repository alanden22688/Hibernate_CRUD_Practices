package model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="backup_user")
public class Backup_UserVO {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
//	@Column(name="name")
	private String name;
//	@Column(name="password")
	private String password;
	
	public Backup_UserVO() {
		super();
	}
	
	public Backup_UserVO(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
