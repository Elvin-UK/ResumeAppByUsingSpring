/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CountryDaoImple extends AbstractDao implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int id=rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        Country emp = new Country(id, name, nationality);
        return emp;
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {//mutleq try with resource etmeliyikki sebeke baglansin connect baglansin

            PreparedStatement stmt = c.prepareStatement("select * from Country");

            stmt.execute();
            ResultSet rs = stmt.getResultSet();
           
            while (rs.next()) {
                Country country = getCountry(rs);
                
                result.add(country);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
