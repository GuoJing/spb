package dao.impl;

import dao.UserMapper;
import domain.UserDomain;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guojing on 2017/3/20.
 */
@Repository
public class UserDaoImpl implements UserMapper {
    /**
     * sqlSession.
     */
    @Resource
    private SqlSession sqlSession;

    /**
     * logger.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Get user by user id.
     * @param id String user id
     * @return sqlSession select one from UserMapper.getById
     */
    @Override
    public UserDomain getById(final Long id) {
        int seq = getDatabaseSequence(id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("seq", seq);
        return sqlSession.selectOne("UserMapper.getById", params);
    }

    /**
     * Update user name by user id.
     * @param name user name
     * @param id user id
     */
    @Override
    public void updateNameById(final String name, final Long id) {
        int seq = getDatabaseSequence(id);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("seq", seq);
        params.put("name", name);
        sqlSession.update("UserMapper.updateNameById", params);
    }

    private int getDatabaseSequence(Long id) {
        return (Math.abs(Math.toIntExact(id) % 10));
    }
}
