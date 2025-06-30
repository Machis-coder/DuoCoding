import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import to, {headers} from "./utils.service";
import ConstUrls from "../../shared/contants/const-urls";
import { User } from 'src/app/interfaces/user';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) {}

  private END_POINT_URL = ConstUrls.API_URL + ConstUrls.CONTEXT_LOGIN;

  doLogin(username: string, password: string) {
    const body = {
      username,
      password
    };

    return this.http.post<User>(this.END_POINT_URL + '/signin', body, {
              //headers: headers,
              observe: "response",
            })
  }  

}
