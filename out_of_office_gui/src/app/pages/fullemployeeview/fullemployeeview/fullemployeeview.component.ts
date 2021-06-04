import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
  OnInit,
} from '@angular/core';

import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';

import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-fullemployeeview',
  templateUrl: './fullemployeeview.component.html'
})
export class FullemployeeviewComponent implements OnInit {
  @ViewChild('modalContent', { static: true })
  modalContent!: TemplateRef<any>;  
  public show:boolean = false;
  loading = true;
  refresh: Subject<any> = new Subject();
  activeDayIsOpen: boolean = true;
  public user = {};
  public data = Array();
  public info = {allowance: "", remainingDays: ""};

  constructor(private modal: NgbModal, private route: Router, private router: ActivatedRoute, private api: ApiserviceService) { }

  ngOnInit(){
    let employeeId: any;
    this.router.queryParams.subscribe(params=>{
      this.api.get('employee/employee/username', {username: params.username, id: params.id}).subscribe((data:any)=>{
        employeeId = data.id;
        Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email});
        this.info = data; 
       })
    });
    this.api.get('holiday/getlistofholidays', {}, {}).subscribe((dataa: any) =>{
      for(let i = 0; i < dataa.length; i++) {
        let emploteesList = dataa[i].employees;
        for(let j = 0; j < emploteesList.length; j++) {
          if(emploteesList[j].id == employeeId) {
            this.data.push({title: dataa[i].holidayType.displayName, type: dataa[i].holidayType.code});
          }
        }
      }
      this.loading=false;
    });
  }
  
  
  public doSomething(): void {
    this.show = !this.show;
  }

  backOnTable(): void {
    this.route.navigate(['listofemployees']);
  }

  
}
