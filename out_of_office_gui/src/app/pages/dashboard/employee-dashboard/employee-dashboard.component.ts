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
  public tmpDisabled:boolean = false;

  public doSomething(): void {
    this.show = !this.show;
  }
  
  constructor(private api: ApiserviceService){}

  ngOnInit(): void {
    this.api.get('holiday/getlistofholidays', {}, {}).subscribe((dataa: any) =>{
      let dataa1 = dataa.filter((dataFiltered: any) => dataFiltered.holidayType.code == "Not for all");
      
      for(let i = 0; i < dataa1.length; i++) {
        let emploteesList = dataa1[i].employees;

        for(let j = 0; j < emploteesList.length; j++) { console.log()
          if(emploteesList[j].id == this.user.id) {
            this.tmpDisabled = true;
          }
        }
      }

      this.types = dataa1;
    });
  }

  submitForm(): void {    
    console.log(this.selectControl.value);
    console.log(this.user.id);
    console.log(this.user.firstnameLastName);

    this.api.patch('holiday/holiday/' + this.selectControl.value + '/' + this.user.id + '/' + this.user.firstnameLastName, {}, {}).subscribe((dataa: any) =>{
      
    });
  }
}
