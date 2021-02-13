package site.grec.fabricclientsimulation.database.mongo;

import com.mongodb.client.result.UpdateResult;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import site.grec.fabricclientsimulation.dao.copyright.Copyright;

import java.util.List;

@Component
public class CopyrightRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveCopyright(Copyright copyright) {
        mongoTemplate.save(copyright);
    }

    public Copyright getCopyrightByID(String assetID) {
        Query query = new Query(Criteria.where("assetID").is(assetID));
        List<Copyright> copyrights = mongoTemplate.find(query, Copyright.class);
        if (copyrights.size() > 0) {
            return copyrights.get(0);
        }
        return null;
    }

    public long updateCopyright(Copyright copyright) {
        Query query = new Query(Criteria.where("assetID").is(copyright.getAssetID()));
        Update update = new Update().set("assetID", copyright.getAssetID())
                .set("authorAddress", copyright.getAuthorAddress())
                .set("lockedIncome", copyright.getLockedIncome())
                .set("copyrightInfo", copyright.getCopyrightInfo())
                .set("paricyInfo", copyright.getPiracyInfo())
                .set("politicalBool", copyright.isPoliticalBool())
                .set("workLockBool", copyright.isWorkLockBool())
                .set("quotedMaterials", copyright.getQuotedMaterials())
                .set("CEK", copyright.getCEK());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Copyright.class);
        return result.getMatchedCount();
    }

    public void deleteByID(String assetID) {
        Query query = new Query(Criteria.where("assetID").is(assetID));
        mongoTemplate.remove(query, Copyright.class);
    }
}
