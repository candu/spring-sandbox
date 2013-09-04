package net.savageinter.springpad;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.Index.Duplicates;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {
	@Autowired
	private MongoTemplate mongo;
	
	public static final String COLLECTION_NAME = "users";

	public void addUser(User user) {
		if (!mongo.collectionExists(User.class)) {
			mongo.createCollection(User.class);
			Index emailIndex = new Index().on("email", Order.ASCENDING).unique(Duplicates.DROP);
			mongo.indexOps(User.class).ensureIndex(emailIndex);
		}
		user.setId(new ObjectId());
		mongo.insert(user, COLLECTION_NAME);
	}
	
	public User getUser(ObjectId id) {
		return mongo.findById(id, User.class, COLLECTION_NAME);
	}
	
	public User getUser(String id) {
		ObjectId oid = new ObjectId(id);
		return getUser(oid);
	}
}
