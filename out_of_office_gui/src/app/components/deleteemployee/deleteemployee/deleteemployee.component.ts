import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-deleteemployee',
  templateUrl: './deleteemployee.component.html'
})
export class DeleteemployeeComponent implements OnInit {
  @Input() employeeId: any;
  @Output() hide = new EventEmitter<boolean>();

  constructor(private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {}

  deleteEmployee(){
     this.api.delete('employee/employee',{id: this.employeeId}).subscribe((data)=>{});
     this.hide.emit(true);
     setTimeout(()=>{this.action.getEmployees()}, 1000); 
  }

  hidePopUp(): void {this.hide.emit(true)}
  

}
