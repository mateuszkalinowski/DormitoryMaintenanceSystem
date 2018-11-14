import {Component, Input, OnInit} from '@angular/core';
import {InhabitantRegistrationData} from '../../dtos/inhabitantRegistrationData';
import {ApiService} from '../../api.service';
declare var $: any;

@Component({
  selector: 'app-inhabitant-register',
  templateUrl: './inhabitant-register.component.html',
  styleUrls: ['./inhabitant-register.component.css'],
})
export class InhabitantRegisterComponent implements OnInit {

  inhabitantRegistrationData: InhabitantRegistrationData;
  message: string;
  confirmPassword: string;
  confirmEmail: string;
  dormitoryNames: string[];
  status: boolean;

  constructor(
    private apiService: ApiService,
  ) { }

  ngOnInit() {
    this.inhabitantRegistrationData = new InhabitantRegistrationData();
    this.inhabitantRegistrationData.email = '';
    this.inhabitantRegistrationData.password = '';
    this.inhabitantRegistrationData.roomNumber = '';
    this.inhabitantRegistrationData.firstName = '';
    this.inhabitantRegistrationData.lastName = '';
    this.message = '';

    this.apiService.callApi('api/utils/dormitoryNames', 'GET', null, null).then(
      data => {
        this.dormitoryNames = data;
      }
    );
  }

  register(selectedDormitoryName: number) {

    if ( this.inhabitantRegistrationData.email === ''
      || this.inhabitantRegistrationData.firstName === ''
      || this.inhabitantRegistrationData.lastName === ''
      || this.inhabitantRegistrationData.password === ''
      || this.inhabitantRegistrationData.roomNumber === ''
      || this.inhabitantRegistrationData.dormitoryName === '') {
      this.message = 'Wypełnij wszystkie pola';
      this.status = false;
      return;
    }

    if (this.inhabitantRegistrationData.password !== this.confirmPassword) {
      this.message = 'Wprowadzona hasła nie zgadzają się';
      this.status = false;
      return;
    }

     this.inhabitantRegistrationData.dormitoryName = this.dormitoryNames[selectedDormitoryName];
    this.apiService.callApi('api/inhabitant/add', 'POST', this.inhabitantRegistrationData, null).then(
      data => {
        this.message = data.message;
        this.status = true;
      }
    ).catch(data => {
      this.message = data.message;
      this.status = false;
    });
  }


}
