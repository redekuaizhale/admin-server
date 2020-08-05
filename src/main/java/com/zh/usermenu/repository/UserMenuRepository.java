package com.zh.usermenu.repository;

import com.zh.base.repository.BaseRepository;
import com.zh.usermenu.entity.UserMenuEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMenuRepository extends BaseRepository<UserMenuEntity, String> {
}
