import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/core/models/user.model';
import { RegisterService } from 'src/app/core/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    email: new FormControl(),
    username: new FormControl(),
    password: new FormControl()
}); 
  isSubmitted = false;
  constructor(public formBuilder: FormBuilder, private registerService: RegisterService, private router: Router) { }
  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[A-z])(?=.*[0-9])\S{6,128}$/)]],
      confirmPassword: ['', [Validators.required]],
    })
  }
  getLanguage() {
    return localStorage.getItem("lang");
  }
  get errorControl() {
    return this.registerForm.controls;
  }
  
  submitForm() {
    this.isSubmitted = true;
    if (!this.registerForm.valid) {
      console.log('Please provide all the required values!');
      return false;
      
    } else {
      let user = new User().deserialize({ username: this.registerForm.controls['username'].value, email: this.registerForm.controls['email'].value, password: this.registerForm.controls['password'].value });
     console.log(user);
      this.registerService.register(user).subscribe((res) => {
        if (res.status == false) {
          console.log("oops");
          this.router.navigate(['auth/login']);
        } else if (res.status === true) {
          console.log("successful");        
          localStorage.setItem('token', res.data.token);   
          localStorage.setItem('user', res.data.user);  
          this.router.navigate(['../home']);
        } 
      });
      
    }
    this.registerForm.reset();
    return;
  }
}
