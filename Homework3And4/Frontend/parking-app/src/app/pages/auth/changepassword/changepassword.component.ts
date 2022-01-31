import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; 
import { ChangepasswordService } from 'src/app/core/services/changepassword.service';
import { Observable, throwError } from 'rxjs';
import { Setpassword } from 'src/app/core/models/setpassword.model';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.scss']
})
export class ChangepasswordComponent implements OnInit {

 
  changePasswordForm = new FormGroup({
    oldpassword: new FormControl(),
    newpassword: new FormControl(),
    confirmnewpassword: new FormControl()
});  
  isSubmitted = false;
  constructor(public formBuilder: FormBuilder, private changePasswordService: ChangepasswordService, private router: Router) { }
  ngOnInit() {
    this.changePasswordForm = this.formBuilder.group({
      oldpassword: ['', [Validators.required]],
      newpassword: ['', [Validators.required, Validators.pattern(/^(?=.*[A-z])(?=.*[0-9])\S{6,128}$/)]],
      confirmnewpassword: ['', [Validators.required]],

    })
  }
  getLanguage() {
      return localStorage.getItem("lang");
  }
  submitForm() {
    this.isSubmitted = true;
    if (!this.changePasswordForm.valid) {
      console.log('Please provide all the required values!');
      return false;
    } else {
      let data = new Setpassword().deserialize({password: this.changePasswordForm.controls['newpassword'].value });
      this.changePasswordService.changePassword(data).subscribe((res) => {
        if (res.status == false) {
          console.log("oops");
          this.router.navigate(['auth/login']);
        } else if (res.status === true) {
          console.log("successful");
          this.router.navigate(['auth/login']);
        } 
      });
      this.changePasswordForm.reset();
    }
    return;
  }

}
