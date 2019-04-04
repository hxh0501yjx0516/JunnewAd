package hib.dialect;

import java.sql.Types;

import javax.persistence.Entity;
import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

@Entity
public class MySQLServerDialect extends SQLServerDialect {
	public  MySQLServerDialect() {
		 super();
		 registerHibernateType(Types.LONGVARCHAR, Hibernate.STRING.getName());
		}
}
