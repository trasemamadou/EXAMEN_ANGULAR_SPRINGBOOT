import { Injectable } from '@angular/core';
 
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'; 
import { Session } from 'src/app/model/session/session';
import { environment } from 'src/environments/environment';
 
//var apiUrl = "http://localhost:8083/api/sessions";
var apiUrl = `${environment.apiUrl}/api/sessions`;
@Injectable({
  providedIn: 'root'
})
export class SessionService {
  constructor( private http: HttpClient) { }
  public getAllSessions(): Observable<any>{
    return this.http.get(apiUrl);
  }
  public getSessionById(id: any): Observable<any>{
    return this.http.get(`${apiUrl}/${id}`)
  }
  public getCourseSessions(courseId: number): Observable<any>{
    return this.http.get(`${apiUrl}/courseSessions/${courseId}`);
  }
  public createSession(session: Session): Observable<Session>{
    return this.http.post<any>(apiUrl, session);
  }
  public updateSession(id: number, session: Session): Observable<Session>{
    return this.http.put<any>(`${apiUrl}/${id}`, session);
  }
  public deleteSession(id: number): Observable<any>{
   return  this.http.delete<any>(`${apiUrl}/${id}`);
  }
}
