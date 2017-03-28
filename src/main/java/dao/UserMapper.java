package dao;

import domain.UserDomain;

/**
 * Created by guojing on 2017/3/20.
 */
public interface UserMapper {
    /**
     * User get by user id.
     * @param id user id
     * @return user domain object
     */
    UserDomain getById(String id);

    /**
     * Update user name by user id.
     * @param name user name
     * @param id user id
     */
    void updateNameById(String name, String id);
}
