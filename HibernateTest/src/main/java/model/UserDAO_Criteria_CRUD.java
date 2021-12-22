package model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernateUtil.HibernateUtil;

public class UserDAO_Criteria_CRUD {
	
	//CriteriaQuery 不支援 insert 資料
	
	public void select(Integer... id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder criteriaBulider = session.getCriteriaBuilder();
			CriteriaQuery<UserVO> criteriaQuery = criteriaBulider.createQuery(UserVO.class); // 決定最後傳回來的型別
			Root<UserVO> root = criteriaQuery.from(UserVO.class); // 相當於SQL的 from user
			if (id.length == 0) {
				criteriaQuery.select(root); // 相當於SQL的 select *
//				==========================================
//				大約相當於SQL的 select name,password
//				Path<String> name = root.get("name");
//				Path<String> password = root.get("password");
//				criteriaQuery.multiselect(name,password);
//				使用時需要在UserVO建立相關建構子才能使用
//				==========================================
			} else if (id.length == 1) {
				criteriaQuery.select(root).where(criteriaBulider.equal(root, id[0])); // 沒寫root.get("欄位")的時候 預設會取id;
//				Select * From user Where id = (你輸入的id)
//				==================================================
//				這裡是嘗試使用名字做搜尋
//				criteriaQuery.select(root).where(criteriaBulider.equal(root.get("name"),"alan"));
//				Select * From user Where name = "alan";
//				==================================================
			} else {
				throw new Exception("只提供沒有參數或1個Integer參數");
			}
			List<UserVO> list = session.createQuery(criteriaQuery).getResultList();
			for (UserVO userVO : list) {
				System.out.print("id:" + userVO.getId() + " ");
				System.out.print("name:" + userVO.getName() + " ");
				System.out.print("password:" + userVO.getPassword() + " ");
				System.out.println();
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void update(String name, Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<UserVO> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(UserVO.class);
			Root<UserVO> root = criteriaUpdate.from(UserVO.class); 
			criteriaUpdate.set(root.get("name"), name).where(criteriaBuilder.equal(root.get("id"), id));
//			Update user Set name = "name" Where id = id;
			int count = session.createQuery(criteriaUpdate).executeUpdate();
//			通過executeUpdate來確認完成更新(順便取得更新了幾筆資料)
			System.out.println("有" + count + "筆資料更新");
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void delete(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<UserVO> criteriaDelete = criteriaBuilder.createCriteriaDelete(UserVO.class);
			Root<UserVO> root = criteriaDelete.from(UserVO.class);
			criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
//			Delete from user Where id = id;
			int count = session.createQuery(criteriaDelete).executeUpdate();
			System.out.println("有"+count+"資料被刪除");
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public static void main(String[] args) {
		UserDAO_Criteria_CRUD userDAO = new UserDAO_Criteria_CRUD();
//		userDAO.select(1);
//		userDAO.update("vincent", 5);
		userDAO.delete(6);
	}
}
