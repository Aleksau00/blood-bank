import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./modules/pages/home/home.component";
import { SystemAdministratorComponent} from "./modules/pages/system-administrator/system-administrator.component";
import { UsersComponent} from "./modules/pages/users/users.component";
import { ProfileComponent} from "./modules/pages/profile/profile.component";
import {RegistrationComponent} from "./modules/pages/registration/registration.component";
import {CenterComponent} from "./modules/pages/center/center.component";
import { StaffProfileComponent } from "./modules/pages/staff-profile/staff-profile.component";
import {AddAdministratorComponent} from "./modules/pages/add-administrator/add-administrator.component";
import {WorkingScheduleComponent} from "./modules/pages/working-schedule/working-schedule.component";
import { AppointmentStaffComponent } from "./modules/hospital/appointment-staff/appointment-staff.component";
import { HistoryExaminationsComponent } from "./modules/hospital/history-examinations/history-examinations.component";
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'system-administrator', component: SystemAdministratorComponent},
  { path: 'users', component: UsersComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'center', component: CenterComponent },
  { path: 'staff-profile', component: StaffProfileComponent},
  { path: 'add-administrator', component: AddAdministratorComponent},
  { path: 'working-schedule', component: WorkingScheduleComponent},
  { path: 'appointment-staff', component: AppointmentStaffComponent},
  { path: 'history-examinations', component: HistoryExaminationsComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
