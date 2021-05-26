import { Component, OnInit } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
 public settings = true;
 public user = {};
  constructor(private api: ApiserviceService) { }

  ngOnInit(): void {
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id: data.id});
    })
  }

}
