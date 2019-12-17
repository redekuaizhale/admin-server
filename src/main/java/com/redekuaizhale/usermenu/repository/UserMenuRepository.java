package com.redekuaizhale.usermenu.repository;

import com.redekuaizhale.base.repository.BaseRepository;
import com.redekuaizhale.usermenu.entity.UserMenuEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMenuRepository extends BaseRepository<UserMenuEntity, String> {
}
