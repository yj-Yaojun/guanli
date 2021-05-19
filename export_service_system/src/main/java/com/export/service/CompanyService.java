package com.export.service;

import com.export.dao.CompanyDao;
import com.export.domain.Company;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public PageInfo<Company> findAll(Integer page, Integer size){
        PageHelper.startPage(page, size);
        List<Company> list = companyDao.findAll();
        PageInfo<Company> companyPageInfo = new PageInfo<>(list);
        return companyPageInfo;
    }

    public void save(Company company) {
        companyDao.save(company);
    }

    public Company getById(String id) {
        return companyDao.getById(id);
    }

    public void update(Company company) {
        companyDao.update(company);
    }

    public void delete(String[] id) {
        companyDao.delete(id);
    }
}
