package xin.carryzheng.sssp;

import org.hibernate.ejb.QueryHints;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xin.carryzheng.sssp.dao.DepartmentRepository;
import xin.carryzheng.sssp.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhengxin
 * @date 2018-08-11 12:48:38
 */
public class SSSPTest {



    private ApplicationContext ctx = null;
    private DepartmentRepository departmentRepository;
    private EntityManagerFactory entityManagerFactory;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        departmentRepository = ctx.getBean(DepartmentRepository.class);
        entityManagerFactory = ctx.getBean(EntityManagerFactory.class);
    }

    @Ignore
    @Test
    public void testJpaSecondLevelCache(){

        String jpql = "From Department d";

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql);
        List<Department> departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        departments = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
        entityManager.close();
    }

    @Ignore
    @Test
    public void testRepositorySecondLevelCache(){
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }

    @Ignore
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}
