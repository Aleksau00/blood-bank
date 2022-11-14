import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Center } from '../../bank/model/center.model';
import { CenterService } from '../../bank/services/center.service';

@Component({
  selector: 'app-center-update',
  templateUrl: './center-update.component.html',
  styleUrls: ['./center-update.component.css']
})
export class CenterUpdateComponent implements OnInit {

  public center: Center | undefined;

  constructor(private centerService: CenterService, private route: ActivatedRoute,  private router: Router) { }

  ngOnInit(): void {
    this.centerService.getCenterById(1).subscribe(res => {
    this.center = res;
    })
  }

  public updateCenter(): void {
    this.centerService.updateCenter(this.center).subscribe(res => {
      this.router.navigate(['/center']);
    });
  }
}
