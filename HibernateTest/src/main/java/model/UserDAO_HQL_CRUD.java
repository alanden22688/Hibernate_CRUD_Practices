package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hibernateUtil.HibernateUtil;

public class UserDAO_HQL_CRUD {
	// HQL沒有提供insert into ...Values...
	// 只有提供insert into ... select ...
	// 把其他表的東西 新增到另一個表
	// 最容易懂的其中一個作用就是 備份(個人見解)
	public void insert() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "insert into model.Backup_UserVO(name,password)"
					+ " select name,password from model.UserVO as user"
					+ " Where Not Exists(From model.Backup_UserVO as backup Where user.id = backup.id)";
			// 將user表的東西備份到backup_user這張表(有篩選掉重複的)
			Query<?> query = session.createQuery(hql);
			int count = query.executeUpdate();
			tx.commit();
			System.out.println("有" + count + "筆資料新增");
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public void update(String name, Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "Update UserVO Set name = :name where id = :id" ;
			Query query = session.createQuery(hql);
			query.setParameter("name", name);
			query.setParameter("id", id);
			int count = query.executeUpdate();
			tx.commit();
			System.out.println("有" + count + "筆資料更新");
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}
	
	public void delete(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql = "Delete From UserVO Where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			int count = query.executeUpdate();
			tx.commit();
			System.out.println("有"+count+"筆資料被刪除");
		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
		}
	}

	public void selectAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from UserVO";
		Query<UserVO> query = session.createQuery(hql, UserVO.class);
		// 從第幾個開始
		query.setFirstResult(0);
		// 最多顯示多少個結果
		query.setMaxResults(50);
		List<UserVO> list = query.list();
		for (UserVO userVO : list) {
			System.out.print("ID:" + userVO.getId() + ",");
			System.out.print("Name:" + userVO.getName() + ",");
			System.out.print("Password:" + userVO.getPassword());
			System.out.println();
		}
		session.close();
	}

	public void select(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "from UserVO as user where user.id=:id";
		Query<UserVO> query = session.createQuery(hql, UserVO.class);
		query.setParameter("id", id);
		List<UserVO> list = query.list();
		for (UserVO userVO : list) {
			System.out.print("ID:" + userVO.getId() + ",");
			System.out.print("Name:" + userVO.getName() + ",");
			System.out.print("Password:" + userVO.getPassword());
			System.out.println();
		}
		session.close();
	}

	// ================================測試================================================
	// 查詢屬性(只顯示一個屬性)
	public void selectOneAttribute() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select id from UserVO";
		Query<Integer> query = session.createQuery(hql, Integer.class);
		List<Integer> list = query.list();
		for (Integer id : list) {
			System.out.print("id:" + id);
			System.out.println();
		}
		session.close();
	}

	// 查詢屬性(顯示兩個以上的屬性)
	public void selectMultiAttribute() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "select id , name,password from UserVO";
		Query<Object[]> query = session.createQuery(hql, Object[].class);
		List<Object[]> list = query.list();
		for (Object[] aArray : list) {
			System.out.print("ID:" + aArray[0] + " ");
			System.out.print("Name:" + aArray[1] + " ");
			System.out.print("Password:" + aArray[2] + " ");
//			=========================================
//			for(Object attribute : aArray) {
//				//嘗試判斷物件是Integer還是String
//				if(attribute instanceof Integer) {
//					System.out.print("id:"+ attribute+" ");
//				}
//				if(attribute instanceof String) {
//					System.out.print("Name:"+attribute+" ");
//				}
//			}
//			=========================================
			System.out.println();
		}
	}

	public static void main(String[] args) {
		UserDAO_HQL_CRUD userDAO = new UserDAO_HQL_CRUD();
//		userDAO.selectAll();
//		userDAO.select(1);
//		userDAO.selectOneAttribute();
//		userDAO.selectMultiAttribute();
//		userDAO.insert();
//		userDAO.update("jacky", 4);
		userDAO.delete(5);
	}
}
