package com.zh.userrole.repository;

import com.zh.base.repository.BaseRepository;
import com.zh.userrole.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRoleEntity, String> {
}
