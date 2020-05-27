package com.redekuaizhale.userrole.repository;

import com.redekuaizhale.base.repository.BaseRepository;
import com.redekuaizhale.userrole.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRoleEntity, String> {
}
