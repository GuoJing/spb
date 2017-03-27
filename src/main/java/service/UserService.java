package service;

import dto.UserDto;
import exceptions.IllegalArgumentException;
import exceptions.NotFoundException;

/**
 * Created by guojing on 2017/3/20.
 */
public interface UserService {
    UserDto getById(String id) throws NotFoundException;
    void  updateNameById(String name, String id) throws  NotFoundException, IllegalArgumentException;
}
