import { Component } from '@angular/core';
import { IssueService } from '../issue.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-issue-create',
  templateUrl: './issue-create.component.html',
  styleUrl: './issue-create.component.css'
})
export class IssueCreateComponent {
  issue = {id: 0, title: '', description: '' };

  constructor(private issueService: IssueService, private router: Router) {}

  onSubmit() {
    this.issueService.createIssue(this.issue).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
