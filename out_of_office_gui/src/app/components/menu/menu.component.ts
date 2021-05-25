import { Component, Input, OnInit } from '@angular/core';
import{ApiserviceService} from '../../shared/services/apiservice.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
})
export class MenuComponent implements OnInit {
  public expandIndicator: any;
  public menus: any;
  @Input() role: any;
  constructor(public api: ApiserviceService,private route:Router) { }

  ngOnInit(){
    this.role == 'employee' ? this.api.get(`local/assets/menu/menu.json`).subscribe((r: any) => {this.menus = r; console.log(r);})
                            : this.api.get(`local/assets/menu/menuadmin.json`).subscribe((r: any) => {this.menus = r; console.log(r);});
  }
  
  expand(){
    this.expandIndicator=!this.expandIndicator;
  }

  execMenu(route:any){
    if(route=='') localStorage.clear();
    this.route.navigate([route]);
  }
}
