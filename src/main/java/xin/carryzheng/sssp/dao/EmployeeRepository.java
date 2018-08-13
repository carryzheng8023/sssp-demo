package xin.carryzheng.sssp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xin.carryzheng.sssp.entity.Employee;

/**
 * @author zhengxin
 * @date 2018-08-11 13:33:18
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee getByLastName(String lastName);


}
