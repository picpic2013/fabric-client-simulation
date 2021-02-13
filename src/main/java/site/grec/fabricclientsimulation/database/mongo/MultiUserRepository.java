package site.grec.fabricclientsimulation.database.mongo;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import site.grec.fabricclientsimulation.dao.user.MultiUser;

import java.util.List;

@Component
public class MultiUserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveUser(MultiUser user) {
        mongoTemplate.save(user);
    }

    public MultiUser getUserByID(String userID) {
        Query query = new Query(Criteria.where("userID").is(userID));
        List<MultiUser> users = mongoTemplate.find(query, MultiUser.class);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    public long updateUser(MultiUser user) {
        Query query = new Query(Criteria.where("userID").is(user.getUserID()));
        Update update = new Update().set("userID", user.getUserID())
                .set("divisionDescription", user.getDivisionDescription())
                .set("subAddresses", user.getSubAddresses())
                .set("lockedIncome", user.getLockedIncome())
                .set("dividends", user.getDividends())
                .set("proposal", user.getProposal());
        UpdateResult result = mongoTemplate.updateFirst(query, update, MultiUser.class);
        return result.getMatchedCount();
    }

    public void deleteByID(String userID) {
        Query query = new Query(Criteria.where("userID").is(userID));
        mongoTemplate.remove(query, MultiUser.class);
    }
}
