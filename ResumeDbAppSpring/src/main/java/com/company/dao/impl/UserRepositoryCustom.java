/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;

import java.util.List;

/**
 *
 * @author User
 */
public interface UserRepositoryCustom {

    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User getById(int u);

    public boolean upDateUser(User u);
    public User getFindByEmailAndPassword(String email, String password);
    public User getFindByEmail(String email);
    public boolean addUser(User u);

    public boolean removeUser(int id);

//    public List<UserSkill> getAllSkillByUserId(int userId); silirik niye cunki yeni interfacemizi var

}
