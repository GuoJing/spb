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

    @Resource
    private UserMapper userMapper;

    public UserDto getById(String id) throws NotFoundException {
        UserDomain userDomain = userMapper.getById(id);
        if (userDomain == null) {
            throw new NotFoundException("user not found");
        }
        return convertToDto(userDomain);
    }

    public void updateNameById(String name, String id) throws NotFoundException, IllegalArgumentException {
        UserDomain userDomain = userMapper.getById(id);
        if (userDomain == null) {
            throw new NotFoundException("user not found, should not update");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name should not be empty");
        }
        userMapper.updateNameById(name, id);
    }

    private UserDto convertToDto(final UserDomain userDomain){
        return new UserDto(userDomain.getId(), userDomain.getName());
    }
}
