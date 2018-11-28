import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {WorkerRegistrationData} from '../../dtos/workerRegistrationData';

@Component({
  selector: 'app-admin-worker-register-page',
  templateUrl: './admin-worker-register-page.component.html',
  styleUrls: ['./admin-worker-register-page.component.css']
})
export class AdminWorkerRegisterPageComponent implements OnInit {

  categories: string[];
  workerRegistrationData: WorkerRegistrationData;
  message: string;
  confirmPassword: string;
  status: boolean;

  constructor(
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  register() {
    if (this.workerRegistrationData.email === ''
      || this.workerRegistrationData.password === ''
      || this.workerRegistrationData.lastName === ''
      || this.workerRegistrationData.firstName === '' ) {
      this.message = 'Wypełnij wszystkie pola';
      this.status = false;
      return;
    }
    if (this.workerRegistrationData.categories.length === 0) {
      this.message = 'Dodaj chociaż jedną kategorię';
      this.status = false;
      return;
    }

    if (this.workerRegistrationData.password !== this.confirmPassword) {
      this.message = 'Podane hasła nie zgadzają się';
      this.status = false;
      return;
    }

    this.apiService.callApi('api/worker/add', 'post', this.workerRegistrationData, this.currentUser.token).then(
      () => {
        this.message = 'Pracownik został dodany';
        this.status = true;
      }
    ).catch(
      data => {
        this.message = data.message;
        this.status = false;
      }
    );

  }

  ngOnInit() {
    this.workerRegistrationData = new WorkerRegistrationData();
    this.workerRegistrationData.firstName = '';
    this.workerRegistrationData.lastName = '';
    this.workerRegistrationData.email = '';
    this.workerRegistrationData.password = '';
    this.workerRegistrationData.categories = new Array();

    this.apiService.callApi('api/utils/categories', 'GET', null, this.currentUser.token).then(
      data => {
        this.categories = data;
      }
    );
  }

  addCategory(id: number) {
    if (!this.workerRegistrationData.categories.includes(this.categories[id])) {
      this.workerRegistrationData.categories.push(this.categories[id]);
    }
  }

  removeCategory(category: string) {
    this.workerRegistrationData.categories = this.workerRegistrationData.categories.filter(element => element !== category);

  }

}
