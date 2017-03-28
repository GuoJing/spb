package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.UserDomain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.TestConfig;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by guojing on 2017/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.DaoConfig.class)
public class UserTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Before
    public void setUp() throws JsonProcessingException {
        logger.info("Test is setUp");
    }

    @After
    public void tearDown() {
        logger.info("Test is tearDown");
    }

    @Test
    public void simpleTest() throws Exception {
        String id = "10000";
        UserDomain userDomain = userMapper.getById(id);
        assertThat(userDomain).isNotNull();
        assertThat(userDomain.getName()).isEqualTo("guojing");
    }
}
