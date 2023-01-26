import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Center } from "../../bank/model/center.model";
import { CenterService} from "../../bank/services/center.service";
import {PageEvent} from '@angular/material/paginator';
import {TokenStorageService} from "../../bank/services/token-storage.service";
import {AdminService} from "../../bank/services/admin.service";
import {Admin} from "../../bank/model/admin.model";
import {Overlay} from "@angular/cdk/overlay";
import {MatDialog} from "@angular/material/dialog";
import {CenterViewComponent} from "../../bank/center-view/center-view.component";
import { StaffService } from '../../bank/services/staff.service';
import { UserToken } from '../../bank/model/user-token.model';


export interface Method {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [CenterService]
})
export class HomeComponent implements OnInit {

  public centers: Center[] = [];
  public admin: Admin = new Admin();
  public havePermission: boolean = false;
  public loggedUser: UserToken | undefined ;

  methods: Method[] = [
    {value: 'NameAsc', viewValue: 'Name A-Z'},
    {value: 'NameDesc', viewValue: 'Name Z-A'},
    {value: 'CityAsc', viewValue: 'City A-Z'},
    {value: 'CityDesc', viewValue: 'City Z-A'},
    {value: 'CountryAsc', viewValue: 'Country A-Z'},
    {value: 'CountryDesc', viewValue: 'Country Z-A'},
    {value: 'AverageGradeAsc', viewValue: 'Avg. grade 0-5'},
    {value: 'AverageGradeDesc', viewValue: 'Avg. grade 5-0'}

  ];
  method: Method = {value: 'NameAsc', viewValue: 'Name A-Z'};
  selectedGrade: string='0';

  // MatPaginator Output

  constructor( private dialog: MatDialog, private overlay: Overlay, private tokenStorageService: TokenStorageService, private centerService: CenterService, private router: Router, private adminService: AdminService) { }

  ngOnInit(): void {
    this.centerService.getCentersNameAsc().subscribe(res => {
      this.centers = res;
    })
    this.adminService.getAdmin(this.tokenStorageService.getUser().id).subscribe(res =>{
      this.admin = res;
      console.log(this.admin)
      console.log(this.admin.firstLogin)
      if(this.admin.firstLogin) {
        console.log("Sdfsdfsdfs")
        this.router.navigate(['/change-password']).then(
          ()=>{
            window.location.reload();
          }
        );
      }
    } )

    this.loggedUser = this.tokenStorageService.getUser();
    console.log("Ulogovani korisnik: ", this.loggedUser)
    if (this.loggedUser.role.toString() == 'STAFF') {
      this.havePermission = true;
    }
  }
  public sortCenters(newValue : Method) {
    this.method = newValue;
    this.centerService.getCentersSorted(this.method).subscribe(res => {
      this.centers = res;
    })
  }

  searchText: string = '';

  onSearchTextEntered(searchValue: string){
    this.searchText = searchValue;
  }

  public openCenter(id: number){
    const scrollStrategy = this.overlay.scrollStrategies.reposition();
    const dialogRef = this.dialog.open(CenterViewComponent, {scrollStrategy: scrollStrategy,
      width: '400px',
      data: {
        centerId: id
      }
    });
  }
}
