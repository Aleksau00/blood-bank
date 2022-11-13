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
import { CenterUpdateComponent } from './center-update/center-update.component';
import { StaffProfileUpdateComponent } from './staff-profile-update/staff-profile-update.component';


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
    CenterUpdateComponent,
    StaffProfileUpdateComponent
],
  imports: [
    CommonModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule
  ]
})
export class PagesModule { }
