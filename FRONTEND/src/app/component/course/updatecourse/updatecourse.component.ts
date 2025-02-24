import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/model/course/course'; 
import { CourseService } from 'src/app/service/course/course.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-updatecourse',
  templateUrl: './updatecourse.component.html',
  styleUrls: ['./updatecourse.component.scss']
})
export class UpdatecourseComponent implements OnInit {
  courseId: any;
  course: Course = new Course();
  isSubmitted: boolean = false;
  

  constructor(
    private courseService: CourseService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Récupérer l'ID du cours depuis l'URL
    this.courseId = this.route.snapshot.paramMap.get('id');
    if (this.courseId) {
      this.getCourseById(this.courseId);
    } else {
      console.error("Aucun courseId trouvé dans l'URL !");
    }
  }

  // Récupérer les informations du cours à partir de son ID
  getCourseById(courseId: any) {
    this.courseService.getCourseById(courseId).subscribe(
      (data: Course) => {
        this.course = data;
      },
      (error) => {
        console.error("Erreur lors de la récupération du cours :", error);
      }
    );
  }

  // Mettre à jour les informations du cours
  updateCourse() {
    this.courseService.updateCourse(this.courseId, this.course).subscribe(
       (data: any) => {
                 Swal.fire({
                                 icon: 'success',
                                 title: 'Succès',
                                 text: 'L\'enregistrement a été ajouté avec succès!',
                                 showConfirmButton: false,
                                 timer: 2000
                               }); 
                               setTimeout(() => {
                                 this.router.navigate(["SessionList"]);
                               }, 2000);
            },
     (error: any) => {   
             console.log('Erreur retournée par le backend:', error);
             const errorMessage = error?.error?.error || error?.message || 'Une erreur s\'est produite lors de l\'ajout de l\'enregistrement.';
             const statusCode = error?.status || 'N/A'; 
             
             Swal.fire({
               icon: 'error',
               title: `Erreur - Code: ${statusCode}`,  
               text: errorMessage,  
             });
           }
    );
  }
}
