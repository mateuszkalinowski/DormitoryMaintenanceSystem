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
     this.inhabitantRegistrationData.dormitoryName = this.dormitoryNames[selectedDormitoryName];
    this.apiService.callApi('api/inhabitant/add', 'POST', this.inhabitantRegistrationData, null).then(
      data => {
        this.message = data.message;
      }
    ).catch(data => {
      this.message = data.message;
    });
  }


}
