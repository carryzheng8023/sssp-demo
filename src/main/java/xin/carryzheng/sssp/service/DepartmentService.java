package xin.carryzheng.sssp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xin.carryzheng.sssp.dao.DepartmentRepository;
import xin.carryzheng.sssp.entity.Department;

import java.util.List;

/**
 * @author zhengxin
 * @date 2018-08-11 18:23:08
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<Department> getAll(){
        return departmentRepository.getAll();
    }

}
