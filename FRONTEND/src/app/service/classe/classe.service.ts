import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Classe } from 'src/app/model/classe/classe';
import { Course } from 'src/app/model/course/course';
import { environment } from 'src/environments/environment';
//const apiUrl = 'http://localhost:8083/api/classes';
const apiUrl = `${environment.apiUrl}/api/classes`;
@Injectable({
  providedIn: 'root'
})
export class ClasseService { 
   constructor(private http: HttpClient) {
     }
     public getAllClasses(): Observable<any[]>{
      return this.http.get<any[]>(apiUrl);
    }
    public getClasseById(id: number): Observable<any>{
      return this.http.get(`${apiUrl}/${id}`);
    }
    public createClasse(classe: Classe): Observable<any>{
      return this.http.post(apiUrl, classe);
    }
    public updateClasse(id: number, classe: Classe): Observable<any>{
      return this.http.put(`${apiUrl}/${id}`, classe);
    }
    public deleteClasse(id: number): Observable<any>{
      return this.http.delete(`${apiUrl}/${id}`);
    }
}
