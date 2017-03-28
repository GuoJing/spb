package service;

import dto.UserDto;
import exceptions.IllegalArgumentException;
import exceptions.NotFoundException;

/**
 * Created by guojing on 2017/3/20.
 */
public interface UserService {
    /**
     * Get user by user id.
     * @param id user id
     * @return user dto
     * @throws NotFoundException user not found
     */
    UserDto getById(final String id) throws NotFoundException;

    /**
     * Update user name by user id.
     * @param name user name
     * @param id user id
     * @throws NotFoundException user not found
     * @throws IllegalArgumentException args invalid
     */
    void  updateNameById(final String name, final String id)
            throws  NotFoundException, IllegalArgumentException;
}
