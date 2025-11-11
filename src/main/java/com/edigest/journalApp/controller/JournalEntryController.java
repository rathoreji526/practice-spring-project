package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Integer,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping("/abc")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping()
    public String createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId() , myEntry);
        return "request submitted successfully";
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable int id){
        return journalEntries.get(id);
    }
}
