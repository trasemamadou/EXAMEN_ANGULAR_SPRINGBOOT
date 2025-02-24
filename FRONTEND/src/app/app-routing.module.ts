import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component'; 
import { ListsessionComponent } from './component/session/listsession/listsession.component';
import { CreatesessionComponent } from './component/session/createsession/createsession.component';
import { UpdatesessionComponent } from './component/session/updatesession/updatesession.component';
import { ListcourseComponent } from './component/course/listcourse/listcourse.component';
import { CreatecourseComponent } from './component/course/createcourse/createcourse.component';
import { UpdatecourseComponent } from './component/course/updatecourse/updatecourse.component';
import { UpdateclasseComponent } from './component/classe/updateclasse/updateclasse.component';
import { ListclasseComponent } from './component/classe/listclasse/listclasse.component';
import { CreateregistrationComponent } from './component/registration/createregistration/createregistration.component';
import { UpdateregistrationComponent } from './component/registration/updateregistration/updateregistration.component';
import { ListregistrationComponent } from './component/registration/listregistration/listregistration.component';
import { CreateclasseComponent } from './component/classe/createclasse/createclasse.component';
import { ListstudentComponent } from './component/student/liststudent/liststudent.component';
import { CreatestudentComponent } from './component/student/createstudent/createstudent.component';
import { UpdatestudentComponent } from './component/student/updatestudent/updatestudent.component';
const routes: Routes = [
  { path: '', redirectTo: 'Home', pathMatch: 'full'},
  { path: 'Home', component: HomeComponent },
  // ********************* URLS POUR SESSION *****************//
  {path: 'SessionList', component: ListsessionComponent},
  {path: 'SessionCreate', component: CreatesessionComponent},
  {path: 'SessionUpdate/:id', component: UpdatesessionComponent},
   // ********************* URLS POUR COURSE *****************//
   {path: 'CourseList', component: ListcourseComponent},
   {path: 'CourseCreate', component: CreatecourseComponent},
   {path: 'CourseUpdate/:id', component: UpdatecourseComponent},
  
      // ********************* URLS POUR CLASSE *****************//
  {path: 'ClasseList', component: ListclasseComponent},
   {path: 'ClasseCreate', component: CreateclasseComponent},
  {path: 'ClasseUpdate/:id', component: UpdateclasseComponent}, 
   // ********************* URLS POUR REGISTRATION *****************//
 {path: 'RegistrationList', component: ListregistrationComponent},
  {path: 'RegistrationCreate', component: CreateregistrationComponent}, 
  {path: 'RegistrationUpdate/:id', component: UpdateregistrationComponent}, 
     // ********************* URLS POUR STUDENT *****************//
   {path: 'StudentList', component: ListstudentComponent},
  {path: 'StudentCreate', component: CreatestudentComponent},
  {path: 'StudentUpdate/:id', component: UpdatestudentComponent},  

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }