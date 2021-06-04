import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject} from 'rxjs';

import { Observable } from 'rxjs';




@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  constructor()  {}   
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   // let token = this.auth.getToken();
    let token = localStorage.getItem('token');
    let id = localStorage.getItem('session-id');
    if(token) req = req.clone({setHeaders: {
      'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9;application/json', 
      'Authorization':`Bearer ${token}`

    }});
    return next.handle(req);
}
}
