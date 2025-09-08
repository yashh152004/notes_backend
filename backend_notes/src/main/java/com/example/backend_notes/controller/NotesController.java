package com.example.backend_notes.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_notes.model.User;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    // ----------------- In-memory Notes -----------------
    @SuppressWarnings("FieldMayBeFinal")
    private Map<Integer, Map<String, String>> notes = new HashMap<>();
    private int currentId = 1;

    // ----------------- In-memory Users -----------------
    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, String> users = new HashMap<>(); // email -> password

    // ----------------- NOTES CRUD -----------------

    // Get all notes
    @GetMapping
    public Collection<Map<String, String>> getNotes() {
        return notes.values();
    }

    // Get single note
    @GetMapping("/{id}")
    public Map<String, String> getNote(@PathVariable int id) {
        return notes.get(id);
    }

    // Create note
    @PostMapping
    public Map<String, String> createNote(@RequestBody Map<String, String> note) {
        note.put("id", String.valueOf(currentId));
        notes.put(currentId, note);
        currentId++;
        return note;
    }

    // Update note
    @PostMapping("/{id}")
    public Map<String, String> updateNote(@PathVariable int id, @RequestBody Map<String, String> updatedNote) {
        Map<String, String> note = notes.get(id);
        if (note != null) {
            note.putAll(updatedNote);
        }
        return note;
    }

    // Delete note
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable int id) {
        notes.remove(id);
    }

    // Share note
    @PostMapping("/{id}/share")
    public Map<String, String> shareNote(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        response.put("share_url", "http://localhost:3000/notes/" + id + "/shared");
        return response;
    }

    // ----------------- AUTH -----------------

    // Register user
    @PostMapping("/auth/register")
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if (users.containsKey(user.getEmail())) {
            response.put("message", "User already exists");
        } else {
            users.put(user.getEmail(), user.getPassword());
            response.put("token", "dummy-jwt-token");
        }
        return response;
    }

    // Login user
    @PostMapping("/auth/login")
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        String storedPassword = users.get(user.getEmail());

        if (storedPassword != null && storedPassword.equals(user.getPassword())) {
            response.put("token", "dummy-jwt-token");
        } else {
            response.put("message", "Invalid credentials");
        }
        return response;
    }
}
