import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreatePollComponent } from './create-poll/create-poll.component';
import { MaterialModule} from "../../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreateCenterComponent } from './create-center/create-center.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import { CreateAddressComponent } from './create-address/create-address.component';
import {AddCenterAppointmentComponent} from "./add-center-appointment/add-center-appointment.component";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from "@angular/material/core";
import {AddUserAppointmentComponent} from "./add-user-appointment/add-user-appointment.component";
import {
  AvailableCentersForAppointments
} from "./available-centers-for-appointment/available-centers-for-appointment.component";
import {UserAppointmentsComponent} from "./user-appointments/user-appointments.component";


@NgModule({
  declarations: [
    CreatePollComponent,
    CreateCenterComponent,
    CreateAddressComponent,
    AddCenterAppointmentComponent,
    AddUserAppointmentComponent,
    AvailableCentersForAppointments,
    UserAppointmentsComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ]
})
export class BankModule { }
