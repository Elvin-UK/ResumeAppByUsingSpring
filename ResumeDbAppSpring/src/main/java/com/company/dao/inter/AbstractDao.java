/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public abstract class AbstractDao {
    public  Connection connect() throws Exception {//bazaya qosulmag ucun istifade olunan kod
        Class.forName("com.mysql.jdbc.Driver"); //buna SE de ehtiyac yoxdur amma EE de ehtiyac var
//        com.mysql.jdbc.Driver s;
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "12345";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
    private static EntityManagerFactory emf=null;
    public EntityManager em(){
        if (emf==null) {
            emf = Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager entitymanager = emf.createEntityManager();
        return entitymanager;
        
        
    }
    public  void closeemf( ){
        
        emf.close();
        
    }
}
