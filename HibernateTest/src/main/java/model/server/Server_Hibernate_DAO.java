package model.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernateUtil.HibernateUtil;

public class Server_Hibernate_DAO implements ServerDAO_interface {
	private static final String GET_All_STMT = "From ServerVO order by id";

	@Override
	public void insert(ServerVO serverVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(serverVO);
			session.getTransaction().commit();
		}catch(RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(ServerVO serverVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(serverVO);
			session.getTransaction().commit();
		}catch(RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ServerVO serverVO = session.get(ServerVO.class, id);
			session.delete(serverVO);
			session.getTransaction().commit();
		}catch(RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void findByPrimaryKey(Integer id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.get(ServerVO.class, id);
			session.getTransaction().commit();
		}catch(RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<ServerVO> getAll() {
		List<ServerVO> list = new ArrayList<ServerVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<ServerVO> query = session.createQuery(GET_All_STMT,ServerVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}
	
//	單獨操作Server表
	public static void main(String[] args) {
		ServerDAO_interface serverDAO = new Server_Hibernate_DAO();
		ServerVO serverVO = new ServerVO();
		serverVO.setAddress("新北市板橋區中山路1段87巷10樓");
		ServerVO serverVO2 = new ServerVO();
		serverVO2.setAddress("新北市板橋區中山路2段87巷20樓");
		ServerVO serverVO3 = new ServerVO();
		serverVO3.setAddress("新北市板橋區中山路1段228巷5樓");
		serverDAO.insert(serverVO);
		serverDAO.insert(serverVO2);
		serverDAO.insert(serverVO3);
	}
}
