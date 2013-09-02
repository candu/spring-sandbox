package net.savageinter.springpad;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.Mongo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoteController {
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public String allNotes(Model model) throws Exception {
		MongoOperations mongoOps = new MongoTemplate(new Mongo(), "database");
		if (mongoOps.collectionExists(Note.class)) {
			mongoOps.dropCollection(Note.class);
		}
		mongoOps.createCollection(Note.class);
		mongoOps.insert(new Note("title", "data"));
		List<Note> results = mongoOps.findAll(Note.class);
		model.addAttribute("results", results);
		return "notes";
	}
	
	@RequestMapping(value = "/notes", method = RequestMethod.POST)
	public String createNote() {
		// TODO: implement this
		return "redirect:/notes";
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
	public String readNote(@PathVariable int id, Model model) {
		return "note";
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
	public String updateNote(@PathVariable int id) {
		return "redirect:/notes/" + id;
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
	public String deleteNote(@PathVariable int id) {
		return "redirect:/notes";
	}
	
	@RequestMapping(value = "/bad", method = RequestMethod.GET)
	public String bad(Model model) {
		// cause NPE
		Integer x = null;
		x.byteValue();
		return null;
	}
	
}
