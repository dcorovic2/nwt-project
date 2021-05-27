import { Component, OnInit, Input} from '@angular/core';
import { Router } from '@angular/router';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-createholidaytype',
  templateUrl: './createholidaytype.component.html'
})
export class CreateholidaytypeComponent implements OnInit {
  @Input() user = {};
  public addemp = true;
  public loading = true;  
  public employees: any;
  public employees2: any;
  public employeeId: any;

  constructor(private route:Router, private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {}

  submitForm(): void { 
    let code = (<HTMLInputElement>document.getElementById('code')).value;
    let text = (<HTMLInputElement>document.getElementById('text')).value;
    let type = (<HTMLInputElement>document.getElementById('type')).value;
    let displayname = (<HTMLInputElement>document.getElementById('displayname')).value;
    let params: HttpParams = new HttpParams();
    let jsonObj = {
      "code": code,
      "displayName": text,
      "text": type,
      "type": displayname
    }

    //pozivanje holiday type post apija
      this.api.post('holiday/holidayType', {}, {"code": code, "displayName": text, "text": type, "type": displayname}).subscribe(data=>{
         console.log(data);

      });

    //(<HTMLInputElement>document.getElementById('displayname')).value = "";
    //(<HTMLInputElement>document.getElementById('type')).value = "";
    //(<HTMLInputElement>document.getElementById('text')).value = "";
    //(<HTMLInputElement>document.getElementById('code')).value = "";
  }
}
