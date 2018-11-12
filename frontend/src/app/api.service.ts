import { Injectable } from '@angular/core';
import {ResponseContentType} from '@angular/http';
import {HttpHeaders, HttpRequest} from '@angular/common/http';

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
        //  console.log(response.status);
        //  console.log(response.statusText);


        if (!response.ok) {
          const message = json && json.message ? json.message : 'Nieoczekiwany problem z serwerem, spróbuj ponownie później';
          return Promise.reject({message: message, status: response.status});
        }
        return json;
      });


  }

  testFunction(num: number, num2: number) {
    num = num + 10;
    num2 = num2 + 15;

    return [num, num2];
  }

  constructor() {
  }
}
