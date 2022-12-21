import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Center } from "../../bank/model/center.model";
import { CenterService} from "../../bank/services/center.service";
import {PageEvent} from '@angular/material/paginator';


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
  methods: Method[] = [
    {value: 'NameAsc', viewValue: 'Name A-Z'},
    {value: 'NameDesc', viewValue: 'Name Z-A'},
    {value: 'CityAsc', viewValue: 'City A-Z'},
    {value: 'CityDesc', viewValue: 'City Z-A'},
    {value: 'CountryAsc', viewValue: 'Country A-Z'},
    {value: 'CountryDesc', viewValue: 'Country A-Z'},
    {value: 'AverageGradeAsc', viewValue: 'Avg. grade 0-5'},
    {value: 'AverageGradeDesc', viewValue: 'Avg. grade 5-0'}

  ];
  method: Method = {value: 'NameAsc', viewValue: 'Name A-Z'};
  selectedGrade: string='0';

  // MatPaginator Output

  constructor(private centerService: CenterService, private router: Router) { }

  ngOnInit(): void {
    this.centerService.getCentersNameAsc().subscribe(res => {
      this.centers = res;
    })
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
}
