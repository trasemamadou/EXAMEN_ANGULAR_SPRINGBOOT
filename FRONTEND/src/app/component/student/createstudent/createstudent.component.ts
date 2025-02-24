import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; 
import { Student } from 'src/app/model/student/student';
import { StudentService } from 'src/app/service/student/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-createstudent',
  templateUrl: './createstudent.component.html',
  styleUrls: ['./createstudent.component.scss']
})
export class CreatestudentComponent implements OnInit {

  student: Student = {
    id: undefined,
    firstName: '',
    lastName: '',
    emailPro: '',
    emailPerso: '',
    phoneNumber: '',
    address: '',
    archive: false,
    registrationNu: ''
  };

  isSubmitted: boolean = false;

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit(): void {}

  addStudent() {
    this.isSubmitted = true;
    this.studentService.createStudent(this.student).subscribe(
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
    if(this.isSubmitted==true){
      this.router.navigate(['RegistrationList']);
    }
  }

}
export class student {
  id?: number;
  firstName!: string;
  lastName!: string;
  emailPro!: string;
  emailPerso!: string;
  phoneNumber!: string;
  address!: string;
  archive!: boolean;
  registrationNu!: string;
}
