import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'parking-app';
  languages: string[] = ["EN", "AL", "MK"];
  selectLanguage = this.languages[1];
 onChange(newVal: any) {
    console.log(newVal);
    this.selectLanguage = newVal;
    localStorage.setItem("lang", this.selectLanguage);
 }
 ngOnInit(){
  localStorage.setItem("lang", "EN");
 }
 getLanguage() {
  return localStorage.getItem("lang");
}
}
