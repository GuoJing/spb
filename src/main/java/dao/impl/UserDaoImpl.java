package dao.impl;

import dao.UserMapper;
import domain.UserDomain;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by guojing on 2017/3/20.
 */
@Repository
public class UserDaoImpl implements UserMapper {
    @Resource
    private SqlSession sqlSession;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDomain getById(String id) {
        logger.info("xxxxxxxxxxxxxxxxxxxxxxx");
        logger.info(String.format("get by id domain id is %s", id));
        return sqlSession.selectOne("UserMapper.getById");
    }
}
