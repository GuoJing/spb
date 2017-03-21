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
        try {
            UserDto userDto = userService.getById(id);
            if (userDto != null) {
                return userDto;
            } else {
                logger.info("user dto is still a null");
            }
        } catch (NotFoundException e) {
            logger.info("Not found exception");
        }
        return new UserDto(20000, "User is null, this is a exception message :(");
    }
}
