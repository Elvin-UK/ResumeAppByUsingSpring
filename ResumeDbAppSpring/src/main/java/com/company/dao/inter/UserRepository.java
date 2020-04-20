package com.company.dao.inter;

import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>, UserRepositoryCustom {
}
