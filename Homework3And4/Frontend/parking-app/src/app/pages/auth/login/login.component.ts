import { Component, OnInit } from '@angular/core';
import { Loginuser } from 'src/app/core/models/loginuser.model';
import { LoginService } from 'src/app/core/services/login.service';
import { FormGroup, FormBuilder, Validators, FormControl } from "@angular/forms";
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
 loginForm = new FormGroup({
    email: new FormControl(),
    password: new FormControl()
}); 
  isSubmitted = false;
  constructor(public formBuilder: FormBuilder, private loginService: LoginService, private router: Router) { }
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[A-z])(?=.*[0-9])\S{6,128}$/)]],  
    })
    console.log(this.loginForm);
  }
  getLanguage() {
    return localStorage.getItem("lang");
  }
  submitForm() {
    this.isSubmitted = true;
    if (!this.loginForm.valid) {
      console.log('Please provide all the required values!');
      console.log(this.loginForm);
      return false;
    } else {
      let user = new Loginuser().deserialize({ email: this.loginForm.controls['email'].value, password: this.loginForm.controls['password'].value });
      this.loginService.logIn(user).subscribe((res) => {
        if (res.status == false) {
          console.log("oops");
          this.router.navigate(['../home']);
        } else if (res.status === true) {
          console.log("successful");
          localStorage.setItem('user', res.data.user);
          this.router.navigate(['../home']);
        } 
      });
    }
      this.loginForm.reset();
      return; 
  }
}
