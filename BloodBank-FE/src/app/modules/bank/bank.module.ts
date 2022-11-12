import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreatePollComponent } from './create-poll/create-poll.component';
import { MaterialModule} from "../../material/material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreateCenterComponent } from './create-center/create-center.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";




@NgModule({
  declarations: [
    CreatePollComponent,
    CreateCenterComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    CreateCenterComponent
  ]
})
export class BankModule { }
