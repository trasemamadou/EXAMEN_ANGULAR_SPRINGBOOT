import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClasseService } from 'src/app/service/classe/classe.service';
import { RegistrationService } from 'src/app/service/registration/registration.service';
import { StudentService } from 'src/app/service/student/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-updateregistration',
  templateUrl: './updateregistration.component.html',
  styleUrls: ['./updateregistration.component.scss']
})
export class UpdateregistrationComponent implements OnInit {

  registrationId: any;
  isSubmitted: boolean = false;
  studentList: any[] = [];
  classeList: any[] = [];
  registration: any = {
    id: undefined,
    name: '',
    description: '',
    archive: false,
    studentId: undefined,
    classeId: undefined
  };

  constructor(
    private registrationService: RegistrationService,
    private router: Router,
    private route: ActivatedRoute,
    private studentService: StudentService,
    private classeService: ClasseService
  ) {}

  ngOnInit(): void {
    this.registrationId = this.route.snapshot.paramMap.get('id');  
    if (this.registrationId) {
      this.findRegistrationById(this.registrationId);
    }
    this.getListClasses();
    this.getListStudents();
  }

  findRegistrationById(registrationId: any): void {
    this.registrationService.getRegistrationById(registrationId).subscribe(
      (data: any) => {
        this.registration = data;
        console.log("Enregistrement chargé avec succès", data);
      },
      (error) => {
        console.error("Erreur lors de la récupération de l'enregistrement :", error);
      }
    );
  }

  updateRegistration(): void {
    this.isSubmitted = true;
    this.registrationService.updateRegistration(this.registrationId, this.registration).subscribe(
      () => {
        Swal.fire({
          icon: 'success',
          title: 'Succès',
          text: 'L\'enregistrement a été mis à jour avec succès!',
          showConfirmButton: false,
          timer: 2000
        });
        this.goToRegistrationList();
      },
      (error: any) => {
        const errorMessage = error?.error?.message || 'Une erreur s\'est produite lors de la mise à jour de l\'enregistrement.';
        Swal.fire({
          icon: 'error',
          title: 'Erreur',
          text: errorMessage,
        });
        console.log("Erreur lors de la mise à jour de l'enregistrement", error);
      }
    );
  }

  getListClasses(): void {
    this.classeService.getAllClasses().subscribe(
      (dataClasses: any) => {
        console.log("Les classes sont chargées avec succès");
        this.classeList = dataClasses;
      }
    );
  }

  getListStudents(): void {
    this.studentService.getAllStudents().subscribe(
      (dataStudents: any) => {
        console.log("Etudiants chargés avec succès", dataStudents);
        this.studentList = dataStudents;
      }
    );
  }

  goToRegistrationList(): void {
    this.router.navigate(['/RegistrationList']);
  }
}
