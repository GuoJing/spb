package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import config.TestConfig;
import dao.UserMapper;
import domain.UserDomain;
import dto.UserDto;
import exceptions.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by guojing on 2017/3/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.ServiceConfig.class)
public class UserServiceTest {
    @MockBean
    UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDomain userDomain;

    private UserDto userDto;

    @Test
    public void testGetUserById() throws NotFoundException, JsonProcessingException {
        mockFindUserById();
        UserDto user = userService.getById(100L);
        assertThat(user.getId()).isEqualTo(100L);
        assertThat(user.getName()).isEqualTo("guojing");
    }

    private void mockFindUserById() {
        userDomain = new UserDomain();
        userDomain.setName("guojing");
        userDomain.setId(100L);
        given(userMapper.getById(100L)).willReturn(userDomain);
    }
}
