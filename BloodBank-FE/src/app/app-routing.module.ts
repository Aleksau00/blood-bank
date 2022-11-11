import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomeComponent } from "./modules/pages/home/home.component";
import {SystemAdministratorComponent} from "./modules/pages/system-administrator/system-administrator.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'system-administrator', component: SystemAdministratorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }