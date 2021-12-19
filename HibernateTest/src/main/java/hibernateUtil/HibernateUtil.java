package hibernateUtil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;

	static {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
//			sessionFactory = new Configuration().configure().buildSessionFactory();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Throwable ex) {
			StandardServiceRegistryBuilder.destroy(registry);
			System.err.println("Initial SessionFactory creation Failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

}
