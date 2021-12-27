import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Setpassword } from 'src/app/core/models/setpassword.model';
import { ChangepasswordService } from 'src/app/core/services/changepassword.service';

@Component({
  selector: 'app-forgotpassword',
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.scss']
})
export class ForgotpasswordComponent implements OnInit {

  
  forgotPasswordForm = new FormGroup({
    newpassword: new FormControl(),
    confirmnewpassword: new FormControl()
});  

  isSubmitted = false;
  constructor(public formBuilder: FormBuilder, private forgotPasswordService: ChangepasswordService, private router: Router) { }
  ngOnInit() {
    this.forgotPasswordForm = this.formBuilder.group({
      newpassword: ['', [Validators.required, Validators.pattern(/^(?=.*[A-z])(?=.*[0-9])\S{6,128}$/)]],
      confirmnewpassword: ['', [Validators.required]],

    })
  }
  getLanguage() {
    return localStorage.getItem("lang");
  }
  submitForm() {
    this.isSubmitted = true;
    if (!this.forgotPasswordForm.valid) {
      console.log('Please provide all the required values!');
      return false;
    } else {
      let data = new Setpassword().deserialize({new_password: this.forgotPasswordForm.controls['newpassword'].value });
      this.forgotPasswordService.changePassword(data).subscribe((res) => {
        if (res.status == false) {
          console.log("oops");
          this.router.navigate(['auth/login']);
        } else if (res.status === true) {
          console.log("successful");
          this.router.navigate(['auth/login']);
        } 
      });
      this.forgotPasswordForm.reset();
    }
    return;
  }

}
