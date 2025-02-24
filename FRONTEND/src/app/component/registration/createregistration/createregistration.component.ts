import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
 
import { Registration } from 'src/app/model/registration/registration';
import { ClasseService } from 'src/app/service/classe/classe.service';
import { RegistrationService } from 'src/app/service/registration/registration.service';
import { StudentService } from 'src/app/service/student/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-createregistration',
  templateUrl: './createregistration.component.html',
  styleUrls: ['./createregistration.component.scss']
})


export class CreateregistrationComponent implements OnInit {

  registration: Registration = {
    id: undefined,
    name: '',
    description: '',
    archive: false,
    studentId: undefined,
    classeId: undefined,
  };
  studentsList:any[]=[]
  classesList: any[]=[]
 isSubmitted: boolean=false;

constructor(private registrationService: RegistrationService,  private classeService: ClasseService, private studentService: StudentService, private router: Router) { }

  ngOnInit(): void { 
    this.getListClasses();
    this.getListStudents();
  }
  
  addRegistration(course: any) {
    this.isSubmitted = true;
    this.registrationService.createRegistration(this.registration).subscribe(
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
      },
      (error: any) => { 
        Swal.fire({
          icon: 'error',
          title: 'Erreur',
          text: 'Une erreur s\'est produite lors de l\'ajout de l\'enregistrement.',
        });
        console.log("Erreur lors du chargement de la classe");
      }
    );
  }
  
  getListClasses(): void{
      this.classeService.getAllClasses().subscribe(
        
          (dataClasses: any) => {
            console.log("Les classes sont chargés avec succès");
            this.classesList=dataClasses;
          }
      )
  }

getListStudents(): void{
this.studentService.getAllStudents().subscribe(
  (dataStudents: any) =>{
    console.log("Etudiants chargés avec succès",
      this.studentsList=dataStudents
    )
  },
  
  (error: any)=>{
    console.log("Erreur lors du chargement des étudiants")
  }
)
  }


}
export class classe{
  name: String | undefined;
description: String | undefined;
archive: boolean |  undefined
form: any | undefined
}
