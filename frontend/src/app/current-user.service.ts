import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CurrentUserService {

  private _email: string;
  private _token: string;
  private _role: string;
  private _firstname: string;
  private _lastname: string;


  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
    localStorage.setItem('email', this._email);
  }

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
    localStorage.setItem('token', this._token);
  }

  get role(): string {
    return this._role;
  }

  set role(value: string) {
    this._role = value;
    localStorage.setItem('role', this._role);
  }

  get firstname(): string {
    return this._firstname;
  }

  set firstname(value: string) {
    this._firstname = value;
    localStorage.setItem('firstname', this._firstname);
  }

  get lastname(): string {
    return this._lastname;
  }

  set lastname(value: string) {
    this._lastname = value;
    localStorage.setItem('lastname', this._lastname);
  }

  remove() {
    this._firstname = '';
    this._lastname = '';
    this._token = '';
    this._role = '';
    this._email = '';
    localStorage.setItem('email', this._email);
    localStorage.setItem('role', this._email);
    localStorage.setItem('token', this._email);
    localStorage.setItem('lastname', this._email);
    localStorage.setItem('firstname', this._email);
  }

  constructor() {
    this._email = localStorage.getItem('email');
    this._token = localStorage.getItem('token');
    this._role = localStorage.getItem('role');
    this._firstname = localStorage.getItem('firstname');
    this._lastname = localStorage.getItem('lastname');
  }
}
