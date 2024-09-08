import { Component } from '@angular/core';
import { IssueService } from '../issue.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Issue } from '../issue';

@Component({
  selector: 'app-issue-edit',
  templateUrl: './issue-edit.component.html',
  styleUrl: './issue-edit.component.css'
})
export class IssueEditComponent {
  issue = {id: 0, title: '', description: '' };

  constructor(
    private route: ActivatedRoute,
    private issueService: IssueService,
    private router: Router
  ) {}

  ngOnInit() {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam ? Number(idParam) : null;

    if(id != null){
      this.issueService.getAnIssue(id).subscribe(data => {
        this.issue = data;
      });
    }else{
      console.error('Issue ID not found');
    }
  }

  onSubmit() {
    this.issueService.updateIssue(this.issue).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
