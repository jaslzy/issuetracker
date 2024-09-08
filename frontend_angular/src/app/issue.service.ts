import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Issue } from './issue'; // Create an Issue interface for type safety
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IssueService {
  private apiUrl = 'http://localhost:8080/api/issues';

  constructor(private http: HttpClient) { }

  createIssue(issue: Issue): Observable<Issue> {
    return this.http.post<Issue>(this.apiUrl, issue);
  }

  updateIssue(issue: Issue): Observable<Issue> {
    return this.http.put<Issue>(this.apiUrl, issue);
  }

  getIssues(): Observable<Issue[]> {
    return this.http.get<Issue[]>(this.apiUrl);
  }

  getAnIssue(id: number):  Observable<Issue> {
    return this.http.get<Issue>(`${this.apiUrl}/${id}`);
  }

  /*
  deleteIssues(): Observable<void> {
    return this.http.delete<void>(this.apiUrl);
  }*/

  deleteAnIssue(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
