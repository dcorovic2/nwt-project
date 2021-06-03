import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ActionService {
  [x: string]: any;
  public notif: any;
  public id: any;
  public notiflist: boolean = false;
  constructor() { }

  public set(index: string, item: any) { this[index] = item; return this;}
  public setNotif(notif:any){this.notif = notif;}
  public getNotif(){return this.notif}
  public setId(id:any){this.id=id}
  public getId(){return this.id}
  public setNotificationList(indicator:any){this.notiflist=indicator}
  public getNotificationList(){return this.notiflist}
}
