package com.matheus.auth.modules.permission.service.impl;

import com.matheus.auth.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("FROM Permission p "
            + "WHERE p.description = :description")
    Permission findByDescription(@Param("description") String description);

}
