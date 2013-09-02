package net.savageinter.springpad;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Note {
	@Id
	private ObjectId id;
	
	private String title;
	private String data;
	private List<String> tags = new ArrayList<String>();
	
	public Note(String title, String data) {
		this.title = title;
		this.data = data;
	}
	
	public ObjectId getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public List<String> getTags() {
		return tags;
	}	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
