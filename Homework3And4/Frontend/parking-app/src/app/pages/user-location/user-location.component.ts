import { Component, OnInit } from '@angular/core';
import { timeout } from 'rxjs';
declare const L: any;


@Component({
  selector: 'app-user-location',
  templateUrl: './user-location.component.html',
  styleUrls: ['./user-location.component.scss']
})

export class UserLocationComponent implements OnInit {
  title = 'UserLocation';

  ngOnInit() {
    if (!navigator.geolocation) {
      console.log('location is not supported');
    }

    navigator.geolocation.getCurrentPosition((position) => {
      const coords = position.coords;

      console.log(`lat: ${position.coords.latitude}, lon: ${position.coords.longitude} `);

      let map = L.map('map').setView([coords.latitude, coords.longitude], 13);

      L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoidGVyZXphMDciLCJhIjoiY2t4aXlkcTB2NHdibDJvbzFycHNqZjAyYSJ9.irVCUJrRQDaMxMGHnq1BCg',
        {
          attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
          maxZoom: 18,
          id: 'mapbox/streets-v11',
          tileSize: 512,
          zoomOffset: -1,
          accessToken: 'pk.eyJ1IjoidGVyZXphMDciLCJhIjoiY2t4aXlkcTB2NHdibDJvbzFycHNqZjAyYSJ9.irVCUJrRQDaMxMGHnq1BCg'
        }).addTo(map);

      let marker1 = L.marker([coords.latitude, coords.longitude]).addTo(map);
      marker1.bindPopup('<b>ME</b>').openPopup();

      let marker2 = L.marker([coords.latitude + 0.002, coords.longitude + 0.0007]).addTo(map);
      marker2.bindPopup('<b>FREE PARKING</b>').openPopup();


    });
    this.watchPosition();

  }

  watchPosition() {
    let desLat = 0;
    let desLon = 0;
    let id = navigator.geolocation.watchPosition(
      (position) => {
        console.log(
          `lat: ${position.coords.latitude}, lon: ${position.coords.longitude} `
        );
        if (position.coords.latitude == desLat) {
          navigator.geolocation.clearWatch(id);
        }

      }, (err) => {
        console.log(err);
      }, {
      enableHighAccuracy: true,
      timeout: 5000,
      maximumAge: 0
    })
  }
}









