package net.savageinter.springpad;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NoteService {
	@Autowired
	private MongoTemplate mongo;
	
	public static final String COLLECTION_NAME = "notes";
	
	public void addNote(Note note) {
		if (!mongo.collectionExists(Note.class)) {
			mongo.createCollection(Note.class);
		}
		note.setId(new ObjectId());
		mongo.insert(note, COLLECTION_NAME);
	}
	
	public List<Note> listNotes() {
		return mongo.findAll(Note.class, COLLECTION_NAME);
	}
	
	public Note getNote(ObjectId id) {
		return mongo.findById(id, Note.class, COLLECTION_NAME);
	}
	
	public Note getNote(String id) {
		ObjectId oid = new ObjectId(id);
		return getNote(oid);
	}
	
	public void deleteNote(Note note) {
		mongo.remove(note);
	}
	
	public void updateNote(Note note) {
		mongo.insert(note);
	}
}
