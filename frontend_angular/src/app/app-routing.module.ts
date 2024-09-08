import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IssueListComponent } from './issue-list/issue-list.component';
import { IssueCreateComponent } from './issue-create/issue-create.component';
import { IssueEditComponent } from './issue-edit/issue-edit.component';

const routes: Routes = [
  { path: '', component: IssueListComponent },       // List issues
  { path: 'create', component: IssueCreateComponent }, // Create issue
  { path: 'edit/:id', component: IssueEditComponent }  // Edit issue
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
