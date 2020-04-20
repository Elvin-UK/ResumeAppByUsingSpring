/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author User
 */
@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {
    @Autowired
    @Qualifier("userDao1")
    private UserRepositoryCustom userDao;

    @Override//jpql
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname, nationalityId);

    }

    @Override
    public boolean upDateUser(User u) {
        return userDao.upDateUser(u);
    }

    //jpql
    @Override
    public User getFindByEmailAndPassword(String email, String password) {
        return userDao.getFindByEmailAndPassword(email, password);
    }

    @Override
    public User getFindByEmail(String email) {

        return userDao.getFindByEmail(email);
    }

    @Override
    public boolean removeUser(int id) {

        return userDao.removeUser(id);
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        return userDao.addUser(u);
    }
}