import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ActionService {
  [x: string]: any;
  public notif: any;
  constructor() { }

  public set(index: string, item: any) { this[index] = item; return this;}
  public setNotif(notif:any){this.notif = notif;}
  public getNotif(){return this.notif}
}
