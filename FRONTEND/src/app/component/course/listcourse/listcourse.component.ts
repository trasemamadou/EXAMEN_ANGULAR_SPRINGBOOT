import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; 
import { ClasseService } from 'src/app/service/classe/classe.service';
import { CourseService } from 'src/app/service/course/course.service';
import { SessionService } from 'src/app/service/session/session.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listcourse',
  templateUrl: './listcourse.component.html',
  styleUrls: ['./listcourse.component.scss']
})
export class ListcourseComponent implements OnInit {

 listCourses : any[] = [];
 courseId: any
   constructor(
    private courseService: CourseService, 
    private sessionService: SessionService,
     private route: ActivatedRoute,
      private router: Router
    ) {}


   ngOnInit(): void {
     this.getAllCourses();
     this.courseId= this.route.snapshot.paramMap.get('courseId');
   }


   getAllCourses(): void {
     this.courseService.getAllCourses().subscribe(
       (data: any) => {
         console.log("Données reçues :", data); // Debugging
         this.listCourses = Array.isArray(data.courses) ? data.courses : [];
       },
       (error) => {
         console.error('Erreur lors de la récupération des cours :', error);
       }
     );
   }
   getCourseSessions(courseId: number){
    this.sessionService.getCourseSessions(courseId).subscribe(
      (data: any) => {
        console.log("Données reçues :", data); // Debugging
        this.listCourses = Array.isArray(data.courses) ? data.courses : [];
      },
      (error: any) => {
        console.error('Erreur lors de la récupération des cours :', error);
      }
    );
   }
 
   updateCourse(id: number): void {
    this.router.navigate(["CourseUpdate/"+id])
   }
   deleteCourse(id: number): void{
    this.courseService.deleteCourse(id).subscribe(
      ()=>{
       Swal.fire({
                      icon: 'success',
                      title: 'Succès',
                      text: 'L\'enregistrement a été ajouté avec succès!',
                      showConfirmButton: false,
                      timer: 2000
                    }).then(()=>window.location.reload())},
     (error: any) => {  
             const errorMessage = error?.error?.message || 'Une erreur s\'est produite lors de l\'ajout de l\'enregistrement.';
             Swal.fire({
               icon: 'error',
               title: 'Erreur',
               text: errorMessage,   
             });
             console.log("Erreur lors du chargement de la classe", error);
           }
    )
   }
   showCreateCourse: boolean = false;

   toggleCreateCourse() {
     this.showCreateCourse = !this.showCreateCourse;
   }

}
