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
import {LoginComponent} from "./modules/pages/login/login.component";
import {AddCenterAppointmentComponent} from "./modules/bank/add-center-appointment/add-center-appointment.component";
import {ChangePasswordComponent} from "./modules/pages/change-password/change-password.component";
import {AddUserAppointmentComponent} from "./modules/bank/add-user-appointment/add-user-appointment.component";
import {
  AvailableCentersForAppointments
} from "./modules/bank/available-centers-for-appointment/available-centers-for-appointment.component";
import {UserAppointmentsComponent} from "./modules/bank/user-appointments/user-appointments.component";
import {CenterViewComponent} from "./modules/bank/center-view/center-view.component";



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
  { path: 'login', component: LoginComponent},
  { path: 'add-center-appointment', component: AddCenterAppointmentComponent},
  { path: 'change-password', component: ChangePasswordComponent},
  { path: 'add-center-appointment', component: AddCenterAppointmentComponent},
  { path: 'add-user-appointment', component: AddUserAppointmentComponent},
  { path: 'available-centers-for-appointment', component: AvailableCentersForAppointments},
  { path: 'user-appointments', component: UserAppointmentsComponent},
  { path: 'center-view', component: CenterViewComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
