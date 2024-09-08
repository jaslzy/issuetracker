package com.example.issuetracker;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long> {
    Optional<Issue> findByIdField(Long id); //Optional<Issue> findByIssuesId(Long issuesId);
}
