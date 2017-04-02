package repository;

import model.document.UserProps;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by guojing on 2017/4/2.
 */
public interface UserPropsRepository extends MongoRepository<UserProps, String> {
    UserProps findOne(String id);
    UserProps save(UserProps props);
    UserProps update(UserProps props);
    void delete(UserProps props);
}
