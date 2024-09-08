package com.example.issuetracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "http://localhost:4200") // Allow angular to talk, instead of unable to.
public class IssueController {
    
    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("/test")
    public String test() {
        return "Greetings from Spring Boot";
    }

    private boolean createOrUpdate_Issue(IssueDTO issueDTO){
        if(issueDTO.getId() == null){
            return false;
        }

        Issue issue = new Issue(
            issueDTO.getId(),
            issueDTO.getTitle(),
            issueDTO.getDescription()
        );

        // if already got same issueId, instead of using a unique issueId
        Optional<Issue> issueOptional = issueRepository.findByIdField( issueDTO.getId() ); 
        boolean isExisting = issueOptional.isPresent();
        if (isExisting) { 
            issue.setKeyno( issueOptional.get().getKeyno() ); 
        }

        Issue savedIssue = issueRepository.save(issue);

        boolean hasDBUpdateSucceed = (savedIssue.getKeyno() != null);
        return hasDBUpdateSucceed;
    }

    // Create and update an issue [ ]
    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})//@PostMapping
    public ResponseEntity<?> createOrUpdateIssue(@RequestBody /*Issue issue*/IssueDTO issueDTO) {
        System.out.println("Create Issue: " + issueDTO); //log
        if(createOrUpdate_Issue(issueDTO)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    // Get all issues [Done & tested.]
    @GetMapping
    public List<IssueDTO> getIssues() {
        List<Issue> dbResults = issueRepository.findAll();
        List<IssueDTO> res = new ArrayList<>();

        for(Issue element : dbResults){
            IssueDTO elementDTO = new IssueDTO(element.getIdField(),element.getTitle(),element.getDescription());
            res.add(elementDTO);
        }

        return res;
        
    }

    // Get an issue by id [ ]
    @GetMapping("/{id}")
    public ResponseEntity<?> getIssue(@PathVariable Long id) {
        Optional<Issue> issueOptional = issueRepository.findByIdField(id); 
    
        if (issueOptional.isPresent()) {
            Issue element = issueOptional.get();
            IssueDTO elementDTO = new IssueDTO(element.getIdField(),element.getTitle(),element.getDescription());
        
            return ResponseEntity.ok(elementDTO);
        }
        
        return ResponseEntity.ok("");//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue not found"); // Return a 404 with a custom message
    }
    
    // Delete all issues [Done & tested.]
    @DeleteMapping
    public void deleteIssues() {
        issueRepository.deleteAll();
    }

    // Delete an issue [ ]
    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) {
        System.out.println("Delete Issue Id: " + id); //log

        Optional<Issue> issueOptional = issueRepository.findByIdField(id);
        if (issueOptional.isPresent()) {
            issueRepository.deleteById( issueOptional.get().getKeyno() );
        }
    }


}
