import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from 'src/app/service/course/course.service';
import { SessionService } from 'src/app/service/session/session.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-updatesession',
  templateUrl: './updatesession.component.html',
  styleUrls: ['./updatesession.component.scss']
})

export class UpdatesessionComponent implements OnInit {

  sessionId: any;
  listCourses: any[]=[]
  isSubmitted: boolean= false
  isEditing = false; 
  session: any = {
    id: undefined,
    name: '',
    description: '',
    archive: false,
    courseId: undefined
  };

  constructor(
    private sessionService: SessionService, 
    private courseService: CourseService,
    private router: Router,
     private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.findSessionById(this.sessionId);
    this.sessionId = this.route.snapshot.paramMap.get('id'); 
    this.getAllCourses()
  }

  findSessionById(sessionId: any): void { 
    this.sessionService.getSessionById(sessionId).subscribe(
      (data: any) => { 
         this.session = data;
         console.log(this.session)
        console.log("Session chargée avec succès");
      },
      (error) => {
        console.error("Erreur lors de la récupération de la session :", error);
      }
    );
  }

  getAllCourses(): void{
    this.courseService.getAllCourses().subscribe(
      (data: any) =>{
        console.log("Cours chargés avec succès");
        this.listCourses=Array.isArray(data.courses) ? data.courses : [];;
      },(error: any)=>{
        console.log("erreur lors du chargement des cours");
      }
    )
  }
  updateSession(): void {
    this.isSubmitted=true
    this.sessionService.updateSession(this.sessionId, this.session).subscribe(
      () => {
       Swal.fire({
                      icon: 'success',
                      title: 'Succès',
                      text: 'L\'enregistrement a été ajouté avec succès!',
                      showConfirmButton: false,
                      timer: 2000
                    })
        this.goToSessionList();
      },
      (error: any) => {  
              const errorMessage = error?.error?.message || 'Une erreur s\'est produite lors de l\'ajout de l\'enregistrement.';
              Swal.fire({
                icon: 'error',
                title: 'Erreur',
                text: errorMessage,   
              });
              console.log("Erreur lors du chargement de la classe", error);
            }
    );
  }

  goToSessionList(): void {
    this.router.navigate(['/SessionList']);
  }
}
