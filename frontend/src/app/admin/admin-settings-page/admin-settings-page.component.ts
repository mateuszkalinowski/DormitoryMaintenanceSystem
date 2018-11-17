import { Component, OnInit } from '@angular/core';
import {PasswordChangeData} from '../../dtos/passwordChangeData';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin-settings-page',
  templateUrl: './admin-settings-page.component.html',
  styleUrls: ['./admin-settings-page.component.css']
})
export class AdminSettingsPageComponent implements OnInit {

  name: string;
  surname: string;
  emailAddress: string;

  contactEmail: string;
  phoneNumber: string;

  firstname: string;
  lastname: string;

  message: string;

  infoMessage: string;

  passwordRepeat: string;

  passwordChangeData: PasswordChangeData;

  constructor(
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.passwordChangeData = new PasswordChangeData();
    this.message = '';
    this.name = this.currentUser.firstname;
    this.surname = this.currentUser.lastname;
    this.emailAddress = this.currentUser.email;

    this.apiService.callApi('api/info', 'GET', null, this.currentUser.token)
      .then( loggedInUser => {
          this.contactEmail = loggedInUser.contactEmail;
          this.phoneNumber = loggedInUser.phoneNumber;
          this.firstname = loggedInUser.firstname;
          this.lastname = loggedInUser.lastname;
        }
      );
  }

  changeInfo() {
    const updateData = {
      contactEmail: this.contactEmail,
      phone: this.phoneNumber,
      firstname: this.firstname,
      lastname: this.lastname
    };

    this.apiService.callApi('api/admin/updateInfo', 'post', updateData, this.currentUser.token).then(
      () => {
        this.apiService.callApi('api/info', 'GET', null, this.currentUser.token)
          .then( loggedInUser => {
              this.contactEmail = loggedInUser.contactEmail;
              this.phoneNumber = loggedInUser.phoneNumber;
              this.firstname = loggedInUser.firstname;
              this.lastname = loggedInUser.lastname;
            }
          );

        this.infoMessage = 'Dane zauktualizowane poprawnie';
      }
    ).catch(
      () => {
        this.infoMessage = 'Wystąpił problem przy próbie aktualizacji danych';
      }
    );


  }

  changePassword() {
    if (this.passwordChangeData.newPassword === undefined || this.passwordChangeData.oldPassword === undefined ||
      this.passwordRepeat === undefined) {
      this.message = 'Wypełnij wszystkie pola';
      return;
    }
    if (this.passwordRepeat === this.passwordChangeData.newPassword) {
      this.apiService.callApi('api/password', 'PUT', this.passwordChangeData, this.currentUser.token).then(
        data => {
          this.message = 'Hasło zostało zmienione poprawnie';
        }
      ).catch(
        data => {
          this.message = 'Podczas zmiany hasła wystąpił błąd';
        }
      );
    } else {
      this.message = 'Wprowadzone nowe hasła nie zgadzają się';
    }
  }
}
