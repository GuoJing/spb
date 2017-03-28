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
import java.util.Objects;

/**
 * Created by guojing on 2017/3/20.
 */
@Repository
public class UserDaoImpl implements UserMapper {
    @Resource
    private SqlSession sqlSession;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDomain getById(final String id) {
        return sqlSession.selectOne("UserMapper.getById", id);
    }

    @Override
    public final void updateNameById(final String name, final String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        sqlSession.update("UserMapper.updateNameById", params);
    }
}
