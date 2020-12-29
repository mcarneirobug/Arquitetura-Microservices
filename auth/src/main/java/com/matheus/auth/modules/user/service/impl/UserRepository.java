package com.matheus.auth.modules.user.service.impl;

import com.matheus.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User u "
            + "WHERE u.userName = :userName")
    User findByUserName(@Param("userName") String userName);

}
