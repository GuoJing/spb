package controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import dto.UserDto;
import service.UserService;

import javax.annotation.Resource;


/**
 * Created by guojing on 2017/3/18.
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable String id) throws NotFoundException{
        UserDto userDto = userService.getById(id);
        return userDto;
    }
}
