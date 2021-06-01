import { Component, Input, OnInit } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-deleteemployee',
  templateUrl: './deleteemployee.component.html'
})
export class DeleteemployeeComponent implements OnInit {

  constructor(private api: ApiserviceService, private action: ActionService) { }
  public hide:boolean = true;
  @Input() employeeId: any;

  hidePopUp(): void {
      this.hide=!this.hide;
  }
  
  ngOnInit(): void {
    console.log(this.employeeId);
  }

  deleteEmployee(){
     this.api.delete('employee/employee',{id: this.employeeId}).subscribe((data)=>console.log(data));
     this.hide = false;
     setTimeout(()=>{this.action.getEmployees()}, 5000); 
  }

}
