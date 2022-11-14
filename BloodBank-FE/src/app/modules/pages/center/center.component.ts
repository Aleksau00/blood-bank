import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Center } from '../../bank/model/center.model';
import { CenterService } from '../../bank/services/center.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css']
})
export class CenterComponent implements OnInit {

public center: Center | undefined;

  constructor(private centerService: CenterService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.centerService.getCenterById(1).subscribe(res => {
    this.center = res;
    })
  }

}
