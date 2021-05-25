import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ActionService {
  [x: string]: any;
  constructor() { }

  public set(index: string, item: any) { this[index] = item; return this;}
}
