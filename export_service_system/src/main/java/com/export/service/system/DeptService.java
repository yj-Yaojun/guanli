package com.export.service.system;

import com.export.dao.system.DeptDao;
import com.export.domain.system.Dept;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptDao deptDao;

    public PageInfo<Dept> findByCompanyId(String companyId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Dept> list = deptDao.findAll(companyId);
        PageInfo<Dept> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public List<Dept> findByCompanyIdNew(String companyId) {
        return deptDao.findAll(companyId);
    }

    public void add(Dept dept) {
        deptDao.add(dept);
    }

    public void update(Dept dept) {
        deptDao.edit(dept);
    }

    public Dept findById(String id) {
        return deptDao.findById(id);
    }


    public void delete(String id) {
        deptDao.delete(id);
    }

    public List<Dept> findDeptName() {
        return deptDao.findDeptName();
    }
}
