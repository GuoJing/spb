package controller;

import dto.StatusDto;
import exceptions.IllegalArgumentException;
import exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDto;
import service.UserService;

import javax.annotation.Resource;


/**
 * Created by guojing on 2017/3/18.
 */
@RestController
public class UserController {
    /**
     * user service.
     */
    @Resource
    private UserService userService;

    /**
     * logger object.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * GET request url for /user/{id}.
     * @param id user id
     * @return user dto
     * @throws NotFoundException no such a user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public final UserDto getUser(@PathVariable final Long id)
            throws NotFoundException {
        return userService.getById(id);
    }

    /**
     * Post update user json body class.
     */
    private static class UpdateJSONBody {
        /**
         * Post json body name mapping.
         */
        private String name;

        /**
         * Post json body name getter.
         * @return String name
         */
        public String getName() {
            return name;
        }
    }

    /**
     * POST request url for /user/{id}, will update user name by id.
     * @param json json body
     * @param id user id
     * @return status dot
     * @throws NotFoundException user not found
     * @throws IllegalArgumentException argument is invalid
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public final StatusDto updateNameById(
            @RequestBody final UpdateJSONBody json,
            @PathVariable final Long id)
            throws NotFoundException,
            IllegalArgumentException {
        String name = json.getName();
        logger.info(String.format("name is %s and id is %s", name, id));
        userService.updateNameById(name, id);
        return new StatusDto("ok");
    }
}
