import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./modules/pages/home/home.component";
import { SystemAdministratorComponent} from "./modules/pages/system-administrator/system-administrator.component";
import { UsersComponent} from "./modules/pages/users/users.component";
import { ProfileComponent} from "./modules/pages/profile/profile.component";
import {RegistrationComponent} from "./modules/pages/registration/registration.component";
import {CenterComponent} from "./modules/pages/center/center.component";
import { StaffProfileComponent } from "./modules/pages/staff-profile/staff-profile.component";


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'system-administrator', component: SystemAdministratorComponent},
  { path: 'users', component: UsersComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'center', component: CenterComponent },
  { path: 'staff-profile', component: StaffProfileComponent}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
