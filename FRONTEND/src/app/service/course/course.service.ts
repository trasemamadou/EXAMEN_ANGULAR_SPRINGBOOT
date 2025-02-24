import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from 'src/app/model/course/course';
import { environment } from 'src/environments/environment';
//var apiUrl = "http://localhost:8083/api/courses";
var apiUrl = `${environment.apiUrl}/api/courses`;

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  constructor(private http: HttpClient) {
   }
   public getAllCourses(): Observable<any[]>{
    return this.http.get<any[]>(apiUrl);
  }
  public getCourseById(id: number): Observable<any>{
    return this.http.get(`${apiUrl}/${id}`);
  }
  public createCourse(course: Course): Observable<any>{
    return this.http.post(apiUrl, course);
  }
  public updateCourse(id: number, course: Course): Observable<any>{
    return this.http.put(`${apiUrl}/${id}`, course);
  }
  public deleteCourse(id: number): Observable<any>{
    return this.http.delete(`${apiUrl}/${id}`);
  }
}
