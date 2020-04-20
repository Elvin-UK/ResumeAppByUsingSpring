/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDao;
import com.company.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author User
 */
@Repository("userDao1")
@Transactional
public class UserRepositoryCustomImpl extends AbstractDao implements UserRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override//jpql
    @Cacheable(value = "users")
    public List<User> getAll(String name, String surname, Integer nationalityId) {


        String jpql = "select u from User u where 1=1 ";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name= :name ";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname= :surname ";
        }
        if (nationalityId != null) {
            jpql += " and u.nationality.id= :nid ";
        }
        Query query = em.createQuery(jpql, User.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);

        }
        if (surname != null && !name.trim().isEmpty()) {
            query.setParameter("surname", surname);

        }
        if (nationalityId != null) {
            query.setParameter("nid", nationalityId);

        }
        return query.getResultList();

    }

    @Override
    public boolean upDateUser(User u) {
        em.merge(u);
        return true;
    }

    //jpql
    @Override
    public User getFindByEmailAndPassword(String email, String password) {

        Query q = em.createQuery("select u from User u where email=:e and password:p", User.class);
        //yuxarida gorduyumuz query nin neticesi user olacag ve usere doldurulacag deye user.class yazmisiq

        q.setParameter("e", email);
        q.setParameter("p", password);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }
//     //Native SQL
//     @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//
//        Query query= em.createNativeQuery("select * from user where email = ?", User.class);
//        query.setParameter(1, email);
////        
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        
//        em.close();
//
//        return null;
//
//    }
    //CriteriaBuilder
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q1 = cb.createQuery(User.class);
//        Root<User> postRoot = q1.from(User.class);
//        CriteriaQuery<User> q2 = q1
//                .where(cb.equal(postRoot.get("email"), email));
//        
//
//        Query query = em.createQuery(q2);
//
////        query.setParameter(1, email);
////        query.setParameter(2, password);
////        
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//
//    }
    //NamedQuery
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
//
//        Query query= em.createNamedQuery("User.findByEmail", User.class);
//        query.setParameter("email", email);
////        
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//
//    }

    @Override
    public User getFindByEmail(String email) {
        Query q = em.createQuery("select u from User u where u.email= :e", User.class);
        q.setParameter("e", email);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }
//    }public static void main(String[] args) {
//        User u = new User(0, "test","test","test","test",null,null,null,"","fdf");
//        u.setPassword("12345");
//        new UserDaoImpl().addUser(u);

    //        System.out.println(crypt.hashToString(4, "12345".toCharArray()));
    @Override
//    @CacheEvict(value = "users",allEntries = true)//buna ehtiyac yoxdu cunki onsuzda ayrica find edir,cahc evic yadda qalan cachi unudur
    public boolean removeUser(int id) {
        User u = em.find(User.class, id);
        em.remove(u);
        return true;
    }

    @Override
    public User getById(int userId) {
        User u = em.find(User.class, userId);
        return u;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        em.persist(u);
        return true;
    }

//    @Override
//    public List<UserSkill> getAllSkillByUserId(int userId) {   yeni ijmplement olunan clasimiz var deye silirik
//        List<UserSkill> result = new ArrayList<>();
//        try (Connection c = connect()) {//mutleq try with resource etmeliyikki sebeke baglansin connect baglansin
//
//            PreparedStatement stmt = c.prepareStatement("SELECT"
//                    + "	u.*,"
//                    + "	us.skill_id,"
//                    + "	s.NAME AS skill_name,"
//                    + "	us.power "
//                    + "FROM"
//                    + "	user_skill us"
//                    + "	LEFT JOIN USER u ON us.user_id = u.id"
//                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
//                    + "WHERE"
//                    + "	us.user_id =?");
//            stmt.setInt(1, userId);
//
//            stmt.execute();
//            ResultSet rs = stmt.getResultSet();
//            while (rs.next()) {
//                UserSkill u = getUserSkill(rs);
//                result.add(u);
//
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//
//    }
//
//    private UserSkill getUserSkill(ResultSet rs) throws Exception {
//        Integer userid = rs.getInt("id");
//        int skillid = rs.getInt("skill_id");
//        int power = rs.getInt("power");
//        String skillname = rs.getString("skill_name");
//        return new UserSkill(null, new User(userid), new Skill(skillid, skillname), power);
//    }


}
