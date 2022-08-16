package com.acehibernate.dao;

import com.acehibernate.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname: UsersDao
 * @Date: 2022/8/15 下午 02:35
 * @Author: kalam_au
 * @Description:
 */


@Repository
@Transactional
public interface UsersDao extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

    Users findById(long id);

    Users findByUserAccount(String userAccount);
}
