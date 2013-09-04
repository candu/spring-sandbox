package net.savageinter.springpad;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public String allNotes(Model model) throws Exception {
		List<Note> notes = noteService.listNotes();
		model.addAttribute("notes", notes);
		return "notes";
	}
	
	@RequestMapping(value = "/notes.json", method = RequestMethod.GET)
	@ResponseBody
	public List<Note> allNotesJSON() throws Exception {
		return noteService.listNotes();
	}
	
	@RequestMapping(value = "/notes/{id}/edit", method = RequestMethod.GET)
	public String editNote(@ModelAttribute Note note,
						   BindingResult result,
						   Model model) {
		model.addAttribute("note", note);
		return "edit";
	}
	
	@RequestMapping(value = "/notes/new", method = RequestMethod.GET)
	public String newNote(@ModelAttribute Note note,
						  BindingResult result,
					      Model model) {
		model.addAttribute("note", new Note());
		return "new";
	}
	
	@RequestMapping(value = "/notes", method = RequestMethod.POST)
	public String createNote(@ModelAttribute Note note,
			                 BindingResult result,
			                 HttpServletRequest request) {
		if (result.hasErrors()) {
			// TODO: signal this somehow...
			return "redirect:" + request.getHeader("Referer");
		}
		noteService.addNote(note);
		return "redirect:/notes";
	}
	
	@RequestMapping(value = "/notes.json", method = RequestMethod.POST)
	@ResponseBody
	public Note createNoteJSON(@ModelAttribute Note note,
			                 	 BindingResult result,
			                 	 HttpServletRequest request) {
		if (result.hasErrors()) {
			// TODO: signal this somehow...
			return null;
		}
		noteService.addNote(note);
		return note;
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
	public String readNote(@PathVariable String id,
						   Model model) {
		Note note = noteService.getNote(id);
		model.addAttribute("note", note);
		return "note";
	}
	
	@RequestMapping(value = "/notes/{id}.json", method = RequestMethod.GET)
	@ResponseBody
	public Note readNoteJSON(@PathVariable String id) {
		return noteService.getNote(id);
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.PUT)
	public String updateNote(@ModelAttribute Note note,
			   				 @PathVariable String format) {
		noteService.updateNote(note);
		return "redirect:/notes/" + note.getId();
	}
	
	@RequestMapping(value = "/notes/{id}.json", method = RequestMethod.PUT)
	@ResponseBody
	public Note updateNoteJSON(@ModelAttribute Note note) {
		noteService.updateNote(note);
		return note;
	}
	
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
	public String deleteNote(@ModelAttribute Note note) {
		noteService.deleteNote(note);
		return "redirect:/notes";
	}
	
	@RequestMapping(value = "/notes/{id}.json", method = RequestMethod.DELETE)
	@ResponseBody
	public Note deleteNoteJSON(@ModelAttribute Note note) {
		noteService.deleteNote(note);
		return note;
	}
	
	@RequestMapping(value = "/bad", method = RequestMethod.GET)
	public String bad(Model model) {
		// cause NPE
		Integer x = null;
		x.byteValue();
		return null;
	}
	
}
