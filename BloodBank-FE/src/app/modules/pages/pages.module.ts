import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { HomeComponent } from './home/home.component';
import {MaterialModule} from "../../material/material.module";
import {FormsModule} from "@angular/forms";
import { SystemAdministratorComponent } from './system-administrator/system-administrator.component';


@NgModule({
  declarations: [
    HomeComponent,
    SystemAdministratorComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule
  ]
})
export class PagesModule { }