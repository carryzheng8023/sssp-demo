package xin.carryzheng.sssp.entity;

import javax.persistence.*;

/**
 * @author zhengxin
 * @date 2018-08-11 13:00:28
 */
@Cacheable
@Table(name = "departments")
@Entity
public class Department {

    private Integer id;
    private String departmentName;

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "department_name")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
