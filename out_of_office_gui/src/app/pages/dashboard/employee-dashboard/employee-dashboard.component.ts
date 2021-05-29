import {
  Component,
  ViewChild,
  TemplateRef,
  OnInit,
  Input,
} from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html'
})
export class EmployeeDashboardComponent implements OnInit {
  

  @ViewChild('modalContent', { static: true })
  modalContent!: TemplateRef<any>;
  @Input() user  = {id:"", firstnameLastName:"", email:""};
  @Input() info = {allowance: "", remainingDays: ""};
  public show:boolean = false;
  selectControl: FormControl = new FormControl();
  public types: any;

  public doSomething(): void {
    this.show = !this.show;
  }
  
  constructor(private api: ApiserviceService){}

 
  ngOnInit(): void {
    let tmpData: any;
    this.api.get('holiday/getlistofholidays', {}, {}).subscribe((dataa: any) =>{
      console.log(typeof dataa);
      this.types = dataa;
    });

  }

  submitForm(): void {    
    console.log(this.selectControl.value);
  }

}
