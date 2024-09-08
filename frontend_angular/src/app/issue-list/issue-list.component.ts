import { Component, OnInit } from '@angular/core';
import { IssueService } from '../issue.service';
import { Router } from '@angular/router';
import { Issue } from '../issue';

@Component({
  selector: 'app-issue-list',
  templateUrl: './issue-list.component.html',
  styleUrl: './issue-list.component.css'
})
export class IssueListComponent implements OnInit{
  issues: Issue[] = [];

  constructor(private issueService: IssueService, private router: Router) {}

  ngOnInit() {
    this.loadIssues();
  }

  loadIssues(){
    this.issueService.getIssues().subscribe(data => {
      this.issues = data;
    });
  }

  editIssue(id: number) {
    this.router.navigate(['/edit', id]);
  }

  deleteIssue(id: number) {
    this.issueService.deleteAnIssue(id).subscribe(() => {
      this.loadIssues();//this.issues = this.issues.filter(issue => issue.id !== id);
    });
  }
}
