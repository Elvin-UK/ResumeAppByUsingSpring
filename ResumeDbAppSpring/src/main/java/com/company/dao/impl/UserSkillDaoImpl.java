/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.UserSkill;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
//@Repository
//@Scope("prototype")
public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {
{
    System.out.println("salam userskill");
}

public static void foo(){
    System.out.println("alma");
}

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {//mutleq try with resource etmeliyikki sebeke baglansin connect baglansin

            PreparedStatement stmt = c.prepareStatement("SELECT"
                    + "	u.*,"
                    + "	us.skill_id,"
                    + "	s.NAME AS skill_name,"
                    + "	us.power "
                    + "FROM"
                    + "	user_skill us"
                    + "	LEFT JOIN USER u ON us.user_id = u.id"
                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE"
                    + "	us.user_id =?");
            stmt.setInt(1, userId);

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }
@Qualifier("foo")
    private UserSkill getUserSkill(ResultSet rs) throws Exception {
//        System.out.println("salam");
        Integer userid = rs.getInt("id");
        int skillid = rs.getInt("skill_id");
        int power = rs.getInt("power");
        String skillname = rs.getString("skill_name");
//        return new UserSkill(null, new User(userid), new Skill(skillid, skillname), power);
        return null;
    }

    @Override
    public boolean removeskill(int id) {
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("delete from user_skill where id= ?" + id);
            stmt.setInt(1, id);
            System.out.println("id:" + String.valueOf(id));
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
