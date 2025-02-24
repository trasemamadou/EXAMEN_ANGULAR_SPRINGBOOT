import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { SessionService } from 'src/app/service/session/session.service';
import { course } from '../../course/createcourse/createcourse.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listsession',
  templateUrl: './listsession.component.html',
  styleUrls: ['./listsession.component.scss']
})
export class ListsessionComponent implements OnInit {

  listSessions : any[] = [];
  courseId: any;
    constructor(private sessionService: SessionService, private route: ActivatedRoute, private router: Router) { }
    ngOnInit(): void {
      const courseId= this.route.snapshot.paramMap.get('id');
      this.courseId= courseId  
      console.log(this.courseId)
      this.getAllSessions(courseId);
    }
    getAllSessions(courseId: any): void {
      
      if(this.courseId){
        this.sessionService.getCourseSessions(this.courseId).subscribe(
          (data: any) => {
            console.log("Données reçues :", data);  
            this.listSessions = Array.isArray(data.sessions) ? data.sessions : [];
          },
          (error: any) => {
            console.error('Erreur lors de la récupération des cours :', error);
          }
        );
      }else{
        this.sessionService.getAllSessions().subscribe(
          (data: any) => {
            console.log("Données reçues :", data);  
            this.listSessions = Array.isArray(data.sessions) ? data.sessions : [];
          },
          (error) => {
            console.error('Erreur lors de la récupération des cours :', error);
          }
        );
      }
}
deleteSession(sessionId: any) {
  this.sessionService.deleteSession(sessionId).subscribe(
    () => {
      console.log("Suppression réussie !");
      Swal.fire({
        icon: 'success',
        title: 'Succès',
        text: 'La session a été supprimée avec succès!',
        showConfirmButton: false,
        timer: 2000
      }).then(() => {
        window.location.reload();
      });
    },
    (error) => {
      console.error("Erreur de suppression :", error);
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Une erreur s\'est produite lors de la suppression.',
      });
    }
  );
}
showCreateSession: boolean = false;

toggleCreateSession() {
  this.showCreateSession = !this.showCreateSession;
}
}

