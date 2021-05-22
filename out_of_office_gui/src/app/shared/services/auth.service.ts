import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,  HttpErrorResponse } from '@angular/common/http';

import { environment as env } from '../../../environments/environment';

import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from "@angular/router";

import { catchError, mapTo, tap } from 'rxjs/operators';



//import * as moment from "moment";
import * as jwt from 'jwt-decode';
import { Observable } from 'rxjs';


@Injectable()
export class AuthService implements CanActivate {
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        throw new Error('Method not implemented.');
    }
    /*
    private user:any;
    private token:any = {};
    private expire:any = {};
    private code:string;
    private guid:string;
    private readonly JWT_TOKEN = 'JWT_TOKEN';
    private readonly REFRESH_TOKEN = 'REFRESH_TOKEN';
    constructor(private api:HttpClient, private http: HttpClient, private action:ActionService, private OAuth:OAuthService){
        this.code = new URLSearchParams(window.location.search).get('code')||null;
        this.action.user = localStorage.getItem('user')? JSON.parse(localStorage.getItem('user')):undefined;
        this.action.set('getGUID', ()=>this.getGUID());
        Object.assign(this.OAuth,{loginUrl:env.auth, redirectUri:env.host, clientId:env.appId});
        this.OAuth.setStorage(sessionStorage);
        //this.OAuth.tryLogin({});      
        console.log('this.OAuth', this.OAuth);
        //this.OAuth.initImplicitFlow();
        console.log('done');
    }

    public canActivate() {return this.isLogged()/*||this.authorize();}

    //public getToken(token) {return this.token[token] = this.token[token]||localStorage.getItem(token);}

    public getGUID() {return this.guid = this.guid||localStorage.getItem('guid');}

  public logout(){ localStorage.clear(); return this.redirect(); }
    isLogged() {
        return !!this.getToken();
      }
    
      refreshToken() {
        let options = {headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded'})};
        let queryUrl = `grant_type=refresh_token&client_id=${env.appId}&refresh_token=${this.getRefreshToken()}`;
        return this.http.post<any>(env.auth+'token',queryUrl, options).pipe(tap((tokens: Tokens) => {
          this.storeJwtToken(tokens.jwt);
        }));
      }
      private setGUID(){
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
           var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
           return v.toString(16);
        });
     }
      getToken() {
        return localStorage.getItem(this.JWT_TOKEN);
      }
    
    
      private getRefreshToken() {
        return localStorage.getItem(this.REFRESH_TOKEN);
      }
    
      private storeJwtToken(jwt: string) {
        let identity =  jwt(jwt);
        localStorage.setItem('user', JSON.stringify(this.action.user = {name:identity.name, email:identity.email, username:identity.preferred_username }));
        localStorage.setItem('guid', this.guid = this.setGUID());
        localStorage.setItem(this.JWT_TOKEN, jwt);
      }
    
      private storeTokens(tokens: Tokens) {
        localStorage.setItem(this.JWT_TOKEN, tokens.jwt);
        localStorage.setItem(this.REFRESH_TOKEN, tokens.refreshToken);
      }
    
      private removeTokens() {
        localStorage.removeItem(this.JWT_TOKEN);
        localStorage.removeItem(this.REFRESH_TOKEN);
      }
      private redirect() {
        localStorage.clear(); 
        window.location.href = `${env.auth}auth?response_type=code&client_id=${env.appId}&redirect_uri=${window.location.href}`;
        return false;
    }
   public isLogged():boolean {
        let y : number = +this.getExpirTime('access');
        return new Date().getTime() < y?true:false;  
    }

    getExpirTime(token) {
        return this.expire[token] = this.expire[token]||localStorage.getItem('expire_'+token)
    }

     private authorize() {
        let y : number = +this.getExpirTime('refresh');
        return new Date().getTime() < y ? this.refreshToken() : this.code ? this.getRawToken() : this.redirect();
    }

    private redirect() {
        localStorage.clear(); 
        window.location.href = `${env.auth}auth?response_type=code&client_id=${env.appId}&redirect_uri=${window.location.href}`;
        return false;
    }

    private set(token:any){
        localStorage.setItem('access', this.token.access = token.access_token);
        localStorage.setItem('refresh', this.token.refresh = token.refresh_token);
        localStorage.setItem('id', this.token.id = token.access_token);
        localStorage.setItem('expire_access', this.expire.access = (new Date().getTime() + token.expires_in * 1000).toString());
        localStorage.setItem('expire_refresh', this.expire.refresh = (new Date().getTime() + token.refresh_expires_in * 1000).toString());
        localStorage.setItem('guid', this.guid = this.setGUID());
        let identity =  jwt(token.access_token);
        localStorage.setItem('user', JSON.stringify(this.action.user = {name:identity.name, email:identity.email, username:identity.preferred_username }));
        this.code = undefined;
    }

    private setGUID(){
       return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
          var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
          return v.toString(16);
       });
    }

    async getRawToken(){
        let queryUrl = `grant_type=authorization_code&client_id=${env.appId}&code=${this.code}&redirect_uri=${window.location.href}`;
        await this.post(queryUrl).subscribe(token=>this.set(token));
        return true;
    }

    async refreshToken(){
        let queryUrl = `grant_type=refresh_token&client_id=${env.appId}&refresh_token=${this.getToken('refresh')}`;
        await this.post(queryUrl).subscribe(token=>this.set(token));
        return true;
    }

    private post(query:string){
        let options = {headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded'})};
        return this.api.post(env.auth+'token', query, options).catch((error:HttpErrorResponse)=> {
            console.log(error.error.message||error.message); this.redirect(); return Observable.throw(error.message);
        });
    }*/
    
}
