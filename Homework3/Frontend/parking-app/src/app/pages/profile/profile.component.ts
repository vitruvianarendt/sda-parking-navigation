import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  name = 'Angular';

  stop(event: Event) {
    event.stopPropagation();
  }
  getLanguage() {
    return localStorage.getItem("lang");
  }
  getUser() {
    return localStorage.getItem("user");
  }
}
