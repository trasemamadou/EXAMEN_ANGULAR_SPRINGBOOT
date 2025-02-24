import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Registration } from 'src/app/model/registration/registration';
import { environment } from 'src/environments/environment';
//const apiUrl='http://localhost:8083/api/registrations';
const apiUrl=`${environment.apiUrl}/api/registrations`;
@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

 constructor(private http: HttpClient) {
     }
     public getAllRegistrations(): Observable<any[]>{
      return this.http.get<any[]>(apiUrl);
    }
    public getRegistrationById(id: number): Observable<any>{
      return this.http.get(`${apiUrl}/${id}`);
    }
    public createRegistration(registration: Registration): Observable<any>{
      return this.http.post(apiUrl, registration,);
    }
    public updateRegistration(id: number, registration: Registration): Observable<any>{
      return this.http.put(`${apiUrl}/${id}`, registration);
    }
    public deleteRegistration(id: number): Observable<any>{
      return this.http.delete(`${apiUrl}/${id}`);
    }
}
