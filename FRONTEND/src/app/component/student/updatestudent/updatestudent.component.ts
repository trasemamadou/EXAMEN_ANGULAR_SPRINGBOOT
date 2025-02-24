import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from 'src/app/service/student/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-updatestudent',
  templateUrl: './updatestudent.component.html',
  styleUrls: ['./updatestudent.component.scss']
})
export class UpdatestudentComponent implements OnInit {

  studentId: any; 
  isSubmitted: boolean= false
  isEditing = false; 

  student: any = {
    id: 0,
    firstName: '',
    lastName: '',
    emailPro: '',
    emailPerso: '',
    phoneNumber: '',
    address: '',
    archive: false,
    registrationNu: ''
  };

  constructor(
    private studentService: StudentService,  
    private router: Router,
     private route: ActivatedRoute) {}


  ngOnInit(): void {
    this.findStudentById(this.studentId);
    this.studentId = this.route.snapshot.paramMap.get('id');  
  }

  findStudentById(sessionId: any): void { 
    this.studentService.getStudentById(sessionId).subscribe(
      (data: any) => { 
         //this.student = data;
         console.log(this.student)
        console.log("Session chargée avec succès");
      },
      (error) => {
        console.error("Erreur lors de la récupération de la session :", error);
      }
    );
  }

 
  updateStudent(): void {
    this.isSubmitted=true
    this.studentService.updateStudent(this.studentId, this.student).subscribe(
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

  goToStudentList(): void {
    this.router.navigate(['/StudentList']);
  }
}
