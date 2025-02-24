import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; 
import { Classe } from 'src/app/model/classe/classe';
 
import { ClasseService } from 'src/app/service/classe/classe.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-createclasse',
  templateUrl: './createclasse.component.html',
  styleUrls: ['./createclasse.component.scss']
})
export class CreateclasseComponent implements OnInit {

classe: Classe = { 
  id: undefined,
  name: '',
  description:'',
  archive: false
};
 isSubmitted: boolean=false;
  constructor(private classeService: ClasseService, private router: Router) { }

  ngOnInit(): void {
 
  }
  addClasse(classe: any){
    this.isSubmitted=true;
    this.classeService.createClasse(classe).subscribe(
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
    )
    if(this.isSubmitted==true){
    this.router.navigate(["RegistrationList"]);
    }
  }

}
export class classe{
  id?: number
  name!: String ;
description!: String;
archive!: boolean 
form: any | undefined
}
