package net.savageinter.springpad;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;

import com.mongodb.DBObject;

public class UserMongoEventListener extends AbstractMongoEventListener<User> {
	@Override
	public void onBeforeSave(User user, DBObject dbo) {
		// set salt, password in user object
		User.beforeSave(user);
	}
}
