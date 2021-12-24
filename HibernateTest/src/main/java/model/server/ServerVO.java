package model.server;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import model.client.ClientVO;

@Entity
@Table(name= "server")
public class ServerVO {
	@Id
	@Column(name = "id",nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="address")
	private String address;
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "server")
//	==================================================================================
//	@JoinColumn 通常是"主導(owning side)的一方"，通常定義在 ManyToOne 的一方，由自己來維護。
//	mappedby    通常是"被主導(inverse side)的一方"，通常定義在 OneToMany 的一方，由對方進行維護。
//	ManyToMany  任何一邊都可以是主導的一方，只能在其中一側設定owning side，另一側就設定 inverse side
//	以上皆為個人理解
//	==================================================================================
	private Set<ClientVO> client;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set<ClientVO> getClient() {
		return client;
	}
	public void setClient(Set<ClientVO> client) {
		this.client = client;
	}
}
