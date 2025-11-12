package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAll")
    public List<JournalEntry> getAllEntries(){
        return journalEntryService.getAll();
    }

    @PostMapping()
    public String createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return "request submitted successfully";
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("/id/{id}")
    public String deleteById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return ("Entry with " + id + " deleted successfully!");
    }

    @PutMapping("id/{id}")
    public String updateById(@PathVariable ObjectId id , @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
        }
        journalEntryService.saveEntry(old);
        return "request submitted successfully";
    }
}
