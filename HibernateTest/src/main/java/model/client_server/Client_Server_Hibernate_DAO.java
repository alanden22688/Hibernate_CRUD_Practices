package model.client_server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernateUtil.HibernateUtil;
import model.client.ClientVO;
import model.server.ServerVO;

public class Client_Server_Hibernate_DAO implements Client_Server_interface{

	// 假設新客戶可以加入很多伺服器
	public void insert(String clientName, Integer... serverIds) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			ClientVO clientVO = new ClientVO();
			clientVO.setName(clientName);
			clientVO.setServer(new HashSet<ServerVO>());
			for (Integer serverId : serverIds) {
				ServerVO serverVO = session.get(ServerVO.class, serverId);
				clientVO.getServer().add(serverVO);
			}
			session.save(clientVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
	}

	public void update(Integer clientId, Integer... serverIds) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			ClientVO clientVO = session.get(ClientVO.class, clientId);
			clientVO.setServer(new HashSet<ServerVO>()); //沒有加這一行的話，只會在原本就有加入的伺服器上，再新增沒有重複的伺服器
			//例如 原本vincent有加入2號和3號伺服器，今天想變為加入1號和2號伺服器
			//沒有先清空的話，會變成1、2、3號伺服器都有加入到
			for (Integer serverId : serverIds) {
				ServerVO serverVO = session.get(ServerVO.class, serverId);
				clientVO.getServer().add(serverVO);
			}
			session.update(clientVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
	}
	
	public void delete(Integer clientId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			ClientVO clientVO = session.get(ClientVO.class, clientId);
			clientVO.setServer(new HashSet<ServerVO>()); //重要!，刪除之前要先把跟Server的關聯清空
			//沒做這個動作會讓資料都被刪除
			session.delete(clientVO);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
	}
	
	public void findByClientId(Integer id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			List<ClientVO> list = new ArrayList<>();
			Query<ClientVO> query = session.createQuery("From ClientVO Where id =:id", ClientVO.class);
			query.setParameter("id", id);
			list = query.list();
			for (ClientVO clientVO : list) {
				System.out.print("ClientId:" + clientVO.getId() + " ");
				System.out.print("ClientName:" + clientVO.getName() + " ");
				System.out.println();
				System.out.println("所加入的伺服器列表:");
				for (ServerVO serverVO : clientVO.getServer()) {
					System.out.print("ServerId:" + serverVO.getId() + " ");
					System.out.println("ServerAddress:" + serverVO.getAddress() + " ");
				}
				System.out.println("================================================");
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
	}

	public void getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			List<ClientVO> list = new ArrayList<>();
			Query<ClientVO> query = session.createQuery("From ClientVO", ClientVO.class);
			list = query.list();
			for (ClientVO clientVO : list) {
				System.out.print("ClientId:" + clientVO.getId() + " ");
				System.out.print("ClientName:" + clientVO.getName() + " ");
				System.out.println();
				System.out.println("所加入的伺服器列表:");
				for (ServerVO serverVO : clientVO.getServer()) {
					System.out.print("ServerId:" + serverVO.getId() + " ");
					System.out.println("ServerAddress:" + serverVO.getAddress() + " ");
				}
				System.out.println("================================================");
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
	}

	public static void main(String[] args) {
		Client_Server_interface client_Server_DAO = new Client_Server_Hibernate_DAO();
//		client_Server_DAO.getAll();
//		client_Server_DAO.findByClientId(2);
//		client_Server_DAO.insert("alan", 1,2,3);
//		client_Server_DAO.insert("vincent", 2,3);
//		client_Server_DAO.update(4, 2, 3);
//		client_Server_DAO.delete(4);
	}
}
