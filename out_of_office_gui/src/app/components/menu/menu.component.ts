import { Component, OnInit } from '@angular/core';
import{ApiserviceService} from '../../shared/services/apiservice.service';
import { Router } from '@angular/router'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
})
export class MenuComponent implements OnInit {
  public expandIndicator: any;
  public menus: any;
  constructor(public api: ApiserviceService,private route:Router) { }

  ngOnInit(){
    this.api.get(`local/assets/menu/menu.json`).subscribe((r: any) => {this.menus = r; console.log(r);});
  }
  
  expand(){
    this.expandIndicator=!this.expandIndicator;
  }

  execMenu(route:any){
    this.route.navigate([route]);
  }
}
