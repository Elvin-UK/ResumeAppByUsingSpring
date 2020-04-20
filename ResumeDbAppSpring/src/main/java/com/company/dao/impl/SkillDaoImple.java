/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SkillDaoImple extends AbstractDao implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        String name = rs.getString("name");

        Skill emp = new Skill(null, name);
        return emp;
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {//mutleq try with resource etmeliyikki sebeke baglansin connect baglansin

            PreparedStatement stmt = c.prepareStatement("select * from skill");

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill skil = getSkill(rs);
                result.add(skil);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
//    private Skill getSkill(ResultSet rs) throws Exception {
////        Integer id=rs.getInt("id");
//        String name = rs.getString("name");
//
//        Skill skil = new Skill(null, name);
//        return skil;
//    }
//    @Override
//    public List<Skill> getAll() {
//        
//        
//        
//        List<Skill> result = new ArrayList<>();
//        try (Connection c = connect()) {//mutleq try with resource etmeliyikki sebeke baglansin connect baglansin
//
//            Statement stmt = c.createStatement();
//            
//            stmt.execute("select * from skill");
//            ResultSet rs = stmt.getResultSet();
//            while (rs.next()) {
////                Skill skil = getSkill(rs);
////                result.add(skil);
//                
//                String name = rs.getString("name");
//                Skill s = new Skill(null, name);
//                result.add(s);
//            }
//            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

    @Override
    public boolean insertSkill(Skill skl) {
        boolean b = true;
        try (Connection c = connect()) {

            PreparedStatement stmt = c.prepareStatement("insert skill (name) values (?); ", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skl.getName());

            b = stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                skl.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

}
