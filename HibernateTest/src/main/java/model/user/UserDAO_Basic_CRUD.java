package model.user;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernateUtil.HibernateUtil;

public class UserDAO_Basic_CRUD {
	//Hibernate的CRUD
	public void insert(UserVO userVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx= session.beginTransaction();
		session.save(userVO); //Persistent狀態
		tx.commit();
		session.close(); //Detached狀態
	}
	
	public void update(UserVO userVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(userVO); //Persistent狀態
		tx.commit();
		session.close(); //Detached狀態
	}

	public void select(Integer Id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserVO userVO = (UserVO) session.get(UserVO.class, Id); //Persistent狀態
		session.close(); //Detached狀態
		System.out.println(userVO.getId()+","+userVO.getName()+","+userVO.getPassword());
	}
	
	public void saveOrUpdate(UserVO userVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(userVO); //Persistent狀態
		tx.commit();
		session.close(); //Detached狀態
	}
	
	public void delete(Integer Id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		UserVO userVO = (UserVO) session.get(UserVO.class, Id); //直接進到Persistent狀態
		session.delete(userVO); //從Persistent進到Removed
		tx.commit();
		session.close(); //Detached狀態
		System.out.println("刪除成功");
	}
	
	//測試區
	public static void main(String[] args) {
		UserDAO_Basic_CRUD userDAO = new UserDAO_Basic_CRUD();
		UserVO userVO = new UserVO(); //new一個物件，進入Transient狀態
//		userVO.setId(1);
//		userVO.setName("terry");
//		userVO.setPassword("123456");

//		新增
//		userDAO.insert(userVO);
//		更新
//		userDAO.update(userVO);
//		查詢
//		userDAO.select(1);
//		新增或更新(看有沒有setId到已存在的Id，沒有該Id就進行save()做insert的動作，有對應Id就進行update()做update動作
//		userDAO.saveOrUpdate(userVO);
//		刪除
//		userDAO.delete(2);
		
//		System.out.println(userVO.getId()+","+userVO.getName()+userVO.getPassword());
		

//		生命週期觀察
//		====================================從Persistent到Detached，透過update讓UserVO從Detached狀態變回Persistent
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		UserVO userVO = session.get(UserVO.class, 3); //這裡直接進到Persistent狀態
//		session.close(); //關閉session，userVO進到Detached狀態
//		
//		Session session2 = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = session2.beginTransaction();
//		userVO.setName("test");
//		session2.update(userVO); //userVO回到Persistent狀態
//		tx.commit();
//		session2.close(); //Detached狀態

		
	}
}
