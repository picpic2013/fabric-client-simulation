package site.grec.fabricclientsimulation.database.mongo;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import site.grec.fabricclientsimulation.dao.user.BasicUser;

import java.util.List;

@Component
public class BasicUserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(BasicUser user) {
        mongoTemplate.save(user);
    }

    public BasicUser getUserByID(String userID) {
        Query query = new Query(Criteria.where("userID").is(userID));
        List<BasicUser> users = mongoTemplate.find(query, BasicUser.class);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public long updateUser(BasicUser user) {
        Query query = new Query(Criteria.where("userID").is(user.getUserID()));
        Update update = new Update().set("userID", user.getUserID())
                .set("balance", user.getBalance())
                .set("devices", user.getDevices());
        UpdateResult result = mongoTemplate.updateFirst(query, update, BasicUser.class);
        return result.getMatchedCount();
    }

    public void deleteByID(String userID) {
        Query query = new Query(Criteria.where("userID").is(userID));
        mongoTemplate.remove(query, BasicUser.class);
    }
}
