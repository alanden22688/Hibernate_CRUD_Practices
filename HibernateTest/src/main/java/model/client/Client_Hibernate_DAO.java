package model.client;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernateUtil.HibernateUtil;
import model.server.ServerVO;

public class Client_Hibernate_DAO implements ClientDAO_interface {

	private static final String GET_All_STMT = "From ClientVO order by id";

	@Override
	public void insert(ClientVO clientVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(clientVO);
			session.getTransaction().commit();
		}catch(RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(ClientVO clientVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(clientVO);
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
			ClientVO clientVO = session.get(ClientVO.class, id);
			session.delete(clientVO);
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
			session.get(ClientVO.class, id);
			session.getTransaction().commit();
		}catch(RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<ClientVO> getAll() {
		List<ClientVO> list = new ArrayList<ClientVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<ClientVO> query = session.createQuery(GET_All_STMT,ClientVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}
	
//	單獨操作Client表
	public static void main(String[] args) {
		ClientDAO_interface clientDAO = new Client_Hibernate_DAO();
		ClientVO clientVO = new ClientVO();
		clientVO.setName("alan");
		clientDAO.insert(clientVO);
	}
}
