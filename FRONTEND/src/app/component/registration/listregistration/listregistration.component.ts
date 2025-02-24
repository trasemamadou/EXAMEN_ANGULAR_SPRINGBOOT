import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'; 
import { ClasseService } from 'src/app/service/classe/classe.service';
import { RegistrationService } from 'src/app/service/registration/registration.service'; 
import Swal from 'sweetalert2';
@Component({
  selector: 'app-listregistration',
  templateUrl: './listregistration.component.html',
  styleUrls: ['./listregistration.component.scss']
})
export class ListregistrationComponent implements OnInit { 
  listRegistrations : any[] = [];
   registrationId: any
     constructor(private registrationService: RegistrationService, private classeeService: ClasseService, private route: ActivatedRoute, private router: Router) { }
     ngOnInit(): void {
       this.getAllRegistrations();
       this.registrationId= this.route.snapshot.paramMap.get('classeId');
     }
     getAllRegistrations(): void {
       this.registrationService.getAllRegistrations().subscribe(
         (data: any) => {
           console.log("Données reçues :", data); // Debugging
           this.listRegistrations = data;
         },
         (error: any) => {
           console.error('Erreur lors de la récupération des cours :', error);
         }
       );
     }
     deleteRegistration(id: number): void {
      this.registrationService.deleteRegistration(id).subscribe(
         (data: any) => {
        Swal.fire({
                      icon: 'success',
                      title: 'Succès',
                      text: 'L\'enregistrement a été ajouté avec succès!',
                      showConfirmButton: false,
                      timer: 2000
                    }).then(
                      ()=>{
                        window.location.reload();
                      }
                    ); 
                    setTimeout(() => {
                      this.router.navigate(["RegistrationList"]);
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
        // }
      );
    }
    
     updateRegistration(id: number): void {
      this.router.navigate(["/RegistrationUpdate/"+id])
     }
     showCreateRegistration: boolean = false;
 
     toggleCreateRegistration() {
       this.showCreateRegistration = !this.showCreateRegistration;
     }
}
