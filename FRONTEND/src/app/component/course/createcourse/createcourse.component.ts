import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  
import { Course } from 'src/app/model/course/course';
import { ClasseService } from 'src/app/service/classe/classe.service';
import { CourseService } from 'src/app/service/course/course.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-createcourse',
  templateUrl: './createcourse.component.html',
  styleUrls: ['./createcourse.component.scss']
})
export class CreatecourseComponent implements OnInit {
 course: Course = {
  id: undefined,
  name: '',
  description: '',
  archive: false,
  classeId: undefined
 };

 isSubmitted: boolean=false;
listClasses: any[]=[];


  constructor(
    private courseService: CourseService, 
    private router: Router,
    private classeService: ClasseService,
  ) { }

  ngOnInit(): void {
    // this.addCourse(course);
    this.getAllClasses();
  }
  addCourse(course: any){
    this.isSubmitted=true;
    this.courseService.createCourse(this.course).subscribe(
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
      },(error: any) => {   
        console.log('Erreur retournée par le backend:', error);
        const errorMessage = error?.error?.error || error?.message || 'Une erreur s\'est produite lors de l\'ajout de l\'enregistrement.';
        const statusCode = error?.status || 'N/A'; 
        
        Swal.fire({
          icon: 'error',
          title: `Erreur - Code: ${statusCode}`,  
          text: errorMessage,  
        });
      }
    )
  }
  getAllClasses(): void {
    this.classeService.getAllClasses().subscribe(
      (data: any) => {
        console.log("Classes chargées avec succès");  
        this.listClasses =data;
      },
      (error: any) => {
        console.error('Erreur lors de la récupération des cours :', error);
      }
    );
  }

}
export class course{
  name: String | undefined;
description: String | undefined;
archive: boolean |  undefined
form: any | undefined
}