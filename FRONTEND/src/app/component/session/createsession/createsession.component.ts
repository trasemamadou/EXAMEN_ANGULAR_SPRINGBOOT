import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';  
import { Session } from 'src/app/model/session/session';
import { CourseService } from 'src/app/service/course/course.service';
import { SessionService } from 'src/app/service/session/session.service'; 
import Swal from 'sweetalert2';
@Component({
  selector: 'app-createsession',
  templateUrl: './createsession.component.html',
  styleUrls: ['./createsession.component.scss']
})
export class CreatesessionComponent implements OnInit {
  session: Session = {
    id: undefined,
    name: '',
    description: '',
    archive: false,
    courseId: undefined
  }; 
  isSubmitted: boolean = false;
  listCourses: any[]= [];


  constructor(
    private sessionService: SessionService,
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService,
  ) {}


  ngOnInit(): void {
    this.getAllCourses();
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
  addSession(session: any) { 
    this.isSubmitted = true;
    this.sessionService.createSession(session).subscribe(
       (data: any) => {
            Swal.fire({
                    icon: 'success',
                    title: 'Succès',
                    text: 'L\'enregistrement a été ajouté avec succès!',
                    showConfirmButton: false,
                    timer: 2000
                  }); 
                  setTimeout(() => {
                    this.router.navigate(["RegistrationList"]);
                  }, 2000);
            },(error: any) => {  
                    console.log('Erreur retournée par le backend:', error); 
                    const statusCode = error?.status || 'N/A'; 
                    const errorMessage = error?.error?.message || 'Une erreur s\'est produite lors de l\'ajout de l\'enregistrement.';
                              
                    Swal.fire({
                      icon: 'error',
                      title: `Erreur - Code: ${statusCode}`,  
                      text: errorMessage,  
                    });
                  }
     
    );
   
  }
}
