import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClasseService } from 'src/app/service/classe/classe.service';
import { CourseService } from 'src/app/service/course/course.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listclasse',
  templateUrl: './listclasse.component.html',
  styleUrls: ['./listclasse.component.scss']
})
export class ListclasseComponent implements OnInit {

 
  listClasses : any[] = [];
  courseId: any
    constructor(private classeService: ClasseService, private courseService: CourseService, private route: ActivatedRoute, private router: Router) { }
 
    ngOnInit(): void {
      this.getAllClasses();
      this.courseId= this.route.snapshot.paramMap.get('classeId');
    }
    getAllClasses(): void {
      this.classeService.getAllClasses().subscribe(
        (data: any) => {
          console.log("Données reçues :", data);  
          this.listClasses =data;
        },
        (error: any) => {
          console.error('Erreur lors de la récupération des cours :', error);
        }
      );
    }
    deleteClasse(id: number): void{
      this.classeService.deleteClasse(id).subscribe(
     (data: any) => {
         Swal.fire({
                 icon: 'success',
                 title: 'Succès',
                 text: 'La suppréssion a été éffectuée avec succès!',
                 showConfirmButton: false,
                 timer: 2000
               }).then(
                ()=>{
                  window.location.reload();
                }
               );
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
    updateClasse(id: number): void {
     this.router.navigate(["/ClasseUpdate/"+id])
    }
    showCreateClasse: boolean = false;

  toggleCreateClasse() {
    this.showCreateClasse = !this.showCreateClasse;
  }

}
