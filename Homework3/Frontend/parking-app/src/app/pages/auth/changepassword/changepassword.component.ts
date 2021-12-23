import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Changepassword } from 'src/app/core/models/changepassword';
import { ChangepasswordService } from 'src/app/core/services/changepassword.service';

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
  language:any = '';
  isSubmitted = false;
  constructor(public formBuilder: FormBuilder, private changePasswordService: ChangepasswordService, private router: Router) { }
  ngOnInit() {
    this.language=localStorage.getItem("lang");
    this.changePasswordForm = this.formBuilder.group({
      oldpassword: ['', [Validators.required]],
      newpassword: ['', [Validators.required, Validators.pattern(/^(?=.*[A-z])(?=.*[0-9])\S{6,128}$/)]],
      confirmnewpassword: ['', [Validators.required]],

    })
  }

  submitForm() {
    this.isSubmitted = true;
    if (!this.changePasswordForm.valid) {
      console.log('Please provide all the required values!');
      return false;
    } else {
      let data = new Changepassword().deserialize({ old_password: this.changePasswordForm.controls['oldpassword'].value, new_password: this.changePasswordForm.controls['newpassword'].value });
      let token = localStorage.getItem('token');
      this.changePasswordService.changePassword(data, token).subscribe((res) => {
        if (res.status == false) {
          console.log("oops");
        } else if (res.status === true) {
          localStorage.removeItem('token');
          console.log("successful");
          this.router.navigate(['auth/login']);
        } 
      });
      this.changePasswordForm.reset();
    }
    return;
  }

}
