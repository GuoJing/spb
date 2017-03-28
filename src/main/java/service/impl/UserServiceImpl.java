package service.impl;

import dao.UserMapper;
import domain.UserDomain;
import dto.UserDto;
import exceptions.NotFoundException;
import exceptions.IllegalArgumentException;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;

/**
 * Created by guojing on 2017/3/20.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * User mapper.
     */
    @Resource
    private UserMapper userMapper;

    /**
     * Get user dto by user id.
     * @param id user id
     * @return user dto
     * @throws NotFoundException user not found
     */
    public final UserDto getById(final String id) throws NotFoundException {
        UserDomain userDomain = userMapper.getById(id);
        if (userDomain == null) {
            throw new NotFoundException("user not found");
        }
        return convertToDto(userDomain);
    }

    /**
     * Update user name by user id.
     * @param name user name
     * @param id user id
     * @throws NotFoundException user not found
     * @throws IllegalArgumentException args invalid
     */
    public final void updateNameById(final String name, final String id)
            throws NotFoundException, IllegalArgumentException {
        UserDomain userDomain = userMapper.getById(id);
        if (userDomain == null) {
            throw new NotFoundException("user not found, should not update");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name should not be empty");
        }
        userMapper.updateNameById(name, id);
    }

    /**
     * Convert user domain to user dto object.
     * @param userDomain user domain object
     * @return user dto object
     */
    private UserDto convertToDto(final UserDomain userDomain) {
        return new UserDto(userDomain.getId(), userDomain.getName());
    }
}
