import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HomeComponent } from './home/home.component';
import {MaterialModule} from "../../material/material.module";
import {FormsModule} from "@angular/forms";
import { SystemAdministratorComponent } from './system-administrator/system-administrator.component';
import { RegistrationComponent } from './registration/registration.component';
import { UsersComponent } from './users/users.component';
import { SearchComponent } from './search/search.component';
import { ProfileComponent } from './profile/profile.component';
import { CenterComponent } from './center/center.component';
import { StaffProfileComponent } from './staff-profile/staff-profile.component';
import { AddAdministratorComponent } from './add-administrator/add-administrator.component';
import { WorkingScheduleComponent } from './working-schedule/working-schedule.component';
import {ScheduleModule} from "@syncfusion/ej2-angular-schedule";

@NgModule({
  declarations: [
    HomeComponent,
    SystemAdministratorComponent,
    RegistrationComponent,
    UsersComponent,
    SearchComponent,
    ProfileComponent,
    CenterComponent,
    StaffProfileComponent,
    AddAdministratorComponent,
    WorkingScheduleComponent

],
  imports: [
    CommonModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule,
    ScheduleModule
  ]
})
export class PagesModule { }
