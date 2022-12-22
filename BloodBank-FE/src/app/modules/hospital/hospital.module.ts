import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { MaterialModule } from "src/app/material/material.module";
import { CreateRoomComponent } from "./create-room/create-room.component";
import { RoomDetailComponent } from "./room-detail/room-detail.component";
import { RoomsComponent } from "./rooms/rooms.component";
import { UpdateProfileComponent } from "./update-profile/update-profile.component";
import { StaffProfileUpdateComponent } from "./staff-profile-update/staff-profile-update.component";
import { CenterUpdateComponent } from "./center-update/center-update.component";
import {AppModule} from "../../app.module";

const routes: Routes = [
  { path: 'rooms', component: RoomsComponent },
  { path: 'rooms/add', component: CreateRoomComponent },
  { path: 'rooms/:id', component: RoomDetailComponent },
  { path: 'profile/update', component: UpdateProfileComponent },
  { path: 'staff-profile/update', component: StaffProfileUpdateComponent },
  { path: 'center/update', component: CenterUpdateComponent },
];

@NgModule({
  declarations: [
    RoomsComponent,
    RoomDetailComponent,
    CreateRoomComponent,
    UpdateProfileComponent,
    StaffProfileUpdateComponent,
    CenterUpdateComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ],
  exports: [ RouterModule ]
})
export class HospitalModule { }
