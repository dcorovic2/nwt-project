import {
  Component,
  ViewChild,
  TemplateRef,
  OnInit,
  Input,
} from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { FormControl } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html'
})
export class EmployeeDashboardComponent implements OnInit {
  isLoadingOne = false;
  public option2Error: boolean = false; 

  @ViewChild('modalContent', { static: true })
  modalContent!: TemplateRef<any>;
  @Input() user  = {id:"", firstnameLastName:"", email:""};
  @Input() info = {allowance: "", remainingDays: ""};
  @Input() leavehistory : any;
  public show:boolean = false;
  selectControl: FormControl = new FormControl();
  public types: any;
  public tmpDisabled:boolean = false;

  
  constructor(private api: ApiserviceService, private message1: NzMessageService){}

  ngOnInit(): void {
    this.api.get('holiday/getlistofholidays', {}, {}).subscribe((data: any) =>{
      let individualHolidays = data.filter((dataFiltered: any) => dataFiltered.holidayType.code == "Not for all");
      for(let i = 0; i < individualHolidays.length; i++) {
        if(individualHolidays[i].employees.find((employee:any)=>{employee=this.user.id})) this.tmpDisabled = true;
      }
      this.types = individualHolidays;
    });
  }

  submitForm(): void {  
    this.option2Error = false;  
    if(this.selectControl.value == null) this.option2Error = true;
   else {  
       this.isLoadingOne = true;
        this.api.patch('holiday/holiday/' + this.selectControl.value + '/' + this.user.id + '/' + this.user.firstnameLastName, {}, {}).subscribe((dataa: any) =>{
        this.tmpDisabled = true;
        this.isLoadingOne = false;
        this.message1.create('success', `You submitted new leave request successfully!`);
      });}
  }

  changeShow(event:any){
    this.show = event;
  }
}
