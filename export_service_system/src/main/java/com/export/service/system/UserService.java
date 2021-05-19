package com.export.service.system;

import com.export.dao.system.UserDao;
import com.export.domain.system.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public PageInfo<User> findPageByCompanyId(String companyId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userDao.findAll(companyId);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User getById(String id) {
        return userDao.findById(id);
    }

    public void delete(String id) {
        userDao.delete(id);
    }
}
