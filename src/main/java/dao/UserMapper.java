package dao;

import domain.UserDomain;

/**
 * Created by guojing on 2017/3/20.
 */
public interface UserMapper {
    UserDomain getById(String id);
}
