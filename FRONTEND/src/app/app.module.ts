import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './component/home/home.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';  
import { CreatesessionComponent } from './component/session/createsession/createsession.component';
import { UpdatesessionComponent } from './component/session/updatesession/updatesession.component';
import { ListsessionComponent } from './component/session/listsession/listsession.component';
import { ListcourseComponent } from './component/course/listcourse/listcourse.component';
import { UpdatecourseComponent } from './component/course/updatecourse/updatecourse.component';
import { CreatecourseComponent } from './component/course/createcourse/createcourse.component'; 
import { ListclasseComponent } from './component/classe/listclasse/listclasse.component';
import { CreateclasseComponent } from './component/classe/createclasse/createclasse.component';
import { UpdateclasseComponent } from './component/classe/updateclasse/updateclasse.component';
import { UpdateregistrationComponent } from './component/registration/updateregistration/updateregistration.component';
import { CreateregistrationComponent } from './component/registration/createregistration/createregistration.component';
import { ListregistrationComponent } from './component/registration/listregistration/listregistration.component';
import { RouterModule } from '@angular/router';
import { ListstudentComponent } from './component/student/liststudent/liststudent.component';
import { CreatestudentComponent } from './component/student/createstudent/createstudent.component';
import { UpdatestudentComponent } from './component/student/updatestudent/updatestudent.component';
 
 
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent, 
    CreatesessionComponent,
    UpdatesessionComponent,
    ListsessionComponent,
    ListcourseComponent,
    UpdatecourseComponent,
    CreatecourseComponent,
    ListclasseComponent,
    ListregistrationComponent,
    ListclasseComponent,
    CreateclasseComponent,
    UpdateclasseComponent,
    UpdateregistrationComponent,
    CreateregistrationComponent,
    ListregistrationComponent,
    ListstudentComponent,
    CreatestudentComponent,
    UpdatestudentComponent, 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
