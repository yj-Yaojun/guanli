package com.export.dao;

import com.export.domain.Company;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();

    void save(Company company);

    Company getById(String id);

    void update(Company company);

    void delete(String[] id);
}
