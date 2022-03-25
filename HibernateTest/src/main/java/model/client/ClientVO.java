package model.client;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import model.server.ServerVO;

@Entity
@Table(name = "client")
public class ClientVO {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
//	==================================================================================
//	@JoinColumn 通常是"主導(owning side)的一方"，通常定義在 ManyToOne 的一方，由自己來維護。
//	mappedby    通常是"被主導(inverse side)的一方"，通常定義在 OneToMany 的一方，由對方進行維護。
//	ManyToMany  任何一邊都可以是主導的一方，只能在其中一側設定owning side，另一側就設定 inverse side
//	以上皆為個人理解
//	==================================================================================
//	@JoinTable(name = "client_server") //預設name是用兩個表的名稱 加上 _ (底線)接在一起
	private Set<ServerVO> server;

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

	public Set<ServerVO> getServer() {
		return server;
	}

	public void setServer(Set<ServerVO> server) {
		this.server = server;
	}

}
