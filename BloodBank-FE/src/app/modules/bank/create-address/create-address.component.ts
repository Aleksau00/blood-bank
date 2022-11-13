import {Component, Inject, OnInit} from '@angular/core';
import {AddressDTO} from "../dto/addressDTO.model";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create-address',
  templateUrl: './create-address.component.html',
  styleUrls: ['./create-address.component.css']
})
export class CreateAddressComponent implements OnInit {


  public addressDialog: AddressDTO = new AddressDTO();
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private dialogRef: MatDialogRef<CreateAddressComponent>) { }

  ngOnInit(): void {
  }

  public createAddress() {
    if (!this.isValidInput()){
      alert("Fields can't be empty.");
      return;
    }
    this.dialogRef.close({data: this.addressDialog});
    alert("Address saved.")
  }

  public return() {
    this.dialogRef.close( {data: this.addressDialog
    })
  }

  public isValidInput(): boolean {
    return (this.addressDialog.country != '' && this.addressDialog.city != '' && this.addressDialog.street != '' && this.addressDialog.number != '' && this.addressDialog.postalCode != '')
  }

}
