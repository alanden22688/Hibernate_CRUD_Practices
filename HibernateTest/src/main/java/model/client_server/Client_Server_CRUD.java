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

public class Client_Server_CRUD {
	public void getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			List<ClientVO> list = new ArrayList<>();
			Query<ClientVO> query = session.createQuery("From ClientVO",ClientVO.class);
			list = query.list();
			for(ClientVO clientVO : list) {
				System.out.print("ClientId:"+clientVO.getId()+" ");
				System.out.print("ClientName:"+clientVO.getName()+" ");
				System.out.println();
				System.out.println("所加入的伺服器列表:");
				for(ServerVO serverVO:clientVO.getServer()) {
					System.out.print("ServerId:"+serverVO.getId()+" ");
					System.out.println("ServerAddress:"+serverVO.getAddress()+" ");	
				}
				System.out.println("================================================");
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
	}

	public static void main(String[] args) {
//		Client_Server_CRUD client_Server_DAO = new Client_Server_CRUD();
//		client_Server_DAO.getAll();
	}
}
