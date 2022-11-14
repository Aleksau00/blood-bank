import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Staff } from '../../bank/model/staff.model';
import { StaffService } from '../../bank/services/staff.service';

@Component({
  selector: 'app-staff-profile-update',
  templateUrl: './staff-profile-update.component.html',
  styleUrls: ['./staff-profile-update.component.css']
})
export class StaffProfileUpdateComponent implements OnInit {


  public staff: Staff | undefined;

  constructor(private staffService: StaffService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.staffService.getStaff(5).subscribe(res => {
    this.staff = res;
    })
  }

  updateStaff(): void {
    if (!this.isValidInput()) return;
    this.staffService.updateStaff(this.staff).subscribe(res => {
      this.router.navigate(['/staff-profile']);
    });
  }

  private isValidInput(): boolean {
    return this.staff?.firstName != '' && this.staff?.lastName != '';
  }
}
