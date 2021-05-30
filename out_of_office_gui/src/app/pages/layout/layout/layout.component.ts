import { Component, OnInit } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  public user  = {id:"", firstnameLastName:"", email:""};
  constructor(private api: ApiserviceService) { }

  ngOnInit(): void {
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});
      console.log(this.user);
     })
     console.log(this.user);
  }

}
