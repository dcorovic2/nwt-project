import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpRequest} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import * as queryParse from 'query-string';
import { environment } from '../../../environments/environment';
import {catchError } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ApiserviceService {
 
  private host: string = environment.apiHost;
  constructor(private http: HttpClient, private message:ToastrService) { }

  
  public get(path:string, param?:any, queryextend?:object): Observable<any> {
    return this.http.get(this.query(this.env(path), param, queryextend)).pipe(catchError((error:HttpErrorResponse)=>{
      /*this.loading.hide(path)*/;this.message.error(error.error.message||error.message);return throwError(error.message);
    }));
}

public post(path:string, param?:object,body?:any): Observable<any> {
  return this.http.post(this.query(this.env(path), param), body,{responseType: 'text'}).pipe(catchError((error:HttpErrorResponse)=>{
      /*this.loading.hide(path);*/ this.message.error(error.error.message||error.message);return throwError(error.message);
  })); 
}

public query(path:string, query?:any, queryextend?:object) {
  return Array.isArray(query) ? path + '/' + query.join('/') + (queryextend ? "?" + queryParse.stringify(queryextend) : ""): query ? path + '?'+ queryParse.stringify(query): path;
}

private env(path:string){return path.match(/local/)?path.replace(/local/,""):this.host+path;}
}
