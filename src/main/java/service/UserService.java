package service;

import dto.UserDto;
import org.apache.ibatis.javassist.NotFoundException;

/**
 * Created by guojing on 2017/3/20.
 */
public interface UserService {
    UserDto getById(String id) throws NotFoundException;
}
