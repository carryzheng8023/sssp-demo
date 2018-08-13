package xin.carryzheng.sssp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import xin.carryzheng.sssp.entity.Department;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author zhengxin
 * @date 2018-08-11 18:10:24
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @QueryHints({@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true")})
    @Query("from Department d")
    List<Department> getAll();
}
