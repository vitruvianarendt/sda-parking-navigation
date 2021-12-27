import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart, ActivatedRoute } from '@angular/router';
import { ParkingsService } from '../../core/services/parkings.service';

@Component({
  selector: 'app-allparkings',
  templateUrl: './allparkings.component.html',
  styleUrls: ['./allparkings.component.scss']
})
export class AllparkingsComponent implements OnInit {

  public allParkingNames: Array<any>=[];
  constructor(
    public api: ParkingsService,
    private router: Router,
   ) { }

  ngOnInit() {
    this.api.getParkingNames().subscribe(data => {
      this.allParkingNames =  data;
      console.log(this.allParkingNames);
    })
  }
  getLanguage() {
    return localStorage.getItem("lang");
  }
}
