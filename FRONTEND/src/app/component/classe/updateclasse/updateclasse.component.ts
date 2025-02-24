import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClasseService } from 'src/app/service/classe/classe.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-updateclasse',
  templateUrl: './updateclasse.component.html',
  styleUrls: ['./updateclasse.component.scss']
})
export class UpdateclasseComponent implements OnInit {


  classeId: any; 
  isSubmitted: boolean= false
  isEditing = false; 

  classe: any = {
    id: undefined,
    name: '',
    description:'',
    archive: false
  };

  constructor(
    private classeService: ClasseService,  
    private router: Router,
     private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.findClasseById(this.classeId);
    this.classeId = this.route.snapshot.paramMap.get('id');  
  }

  findClasseById(sessionId: any): void { 
    this.classeService.getClasseById(sessionId).subscribe(
      (data: any) => { 
         this.classe = data;
         console.log(this.classe)
        console.log("Classes chargées avec succès");
      },
      (error) => {
        console.error("Erreur lors de la récupération de la session :", error);
      }
    );
  }

 
  updateClasse(): void {
    this.isSubmitted=true
    this.classeService.updateClasse(this.classeId, this.classe).subscribe(
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

  goToClasseList(): void {
    this.router.navigate(['/ClasseList']);
  }
}
