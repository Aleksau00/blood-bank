import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateCenterComponent } from './create-center/create-center.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {FormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";



@NgModule({
  declarations: [
    CreateCenterComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    FormsModule,
    MatDialogModule,
    MatInputModule
  ]
})
export class BankModule { }
