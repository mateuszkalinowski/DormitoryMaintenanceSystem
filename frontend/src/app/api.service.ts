import { Injectable } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CurrentUserService} from './current-user.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseAddress = 'http://localhost:8090';

  callApi(address: string, method: string, body: any, token: string) {

    const fetchData: RequestInit = {
      method: method,
      headers: new Headers(),
    };

    if (body != null) {
      fetchData.body = JSON.stringify(body);
    }


    if (token) {
      fetchData.headers = {'content-type': 'application/json', 'x-auth-token': token };
    } else {
      fetchData.headers = {'content-type': 'application/json'};
    }

    return fetch(this.baseAddress + '/' +  address, fetchData)
      .then(response => response.json()
        .then(json => ({json, response}))
        .catch(() => ({json: {}, response}))
      )
      .then(({json, response}) => {

        if (response.status === 403 && json.message === 'Access Denied') {
           // this.callApi('api/logout', 'GET', null, this.currentUser.token);
           this.currentUser.remove();
           this.router.navigate(['/']);
        }

        if (!response.ok) {
          const message = json && json.message ? json.message : 'Nieoczekiwany problem z serwerem, spróbuj ponownie później';
          return Promise.reject({message: message, status: response.status});
        }
        return json;
      });


  }

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) {
  }
}
