import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PasswordChangeData} from '../../dtos/passwordChangeData';
declare var $: any;

@Component({
  selector: 'app-inhabitant-settings-page',
  templateUrl: './inhabitant-settings-page.component.html',
  styleUrls: ['./inhabitant-settings-page.component.css']
})
export class InhabitantSettingsPageComponent implements OnInit {

  name: string;
  surname: string;
  roomNumber: number;
  emailAddress: string;

  message: string;

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

    // $('#navigationNews').removeClass();
    // $('#navigationNewRequest').removeClass();
    // $('#navigationRequests').removeClass();
    // $('#navigationSettings').removeClass();
    //
    // $('#navigationNews').addClass('collection-item');
    // $('#navigationNews').addClass('black-text');
    // $('#navigationNewRequest').addClass('collection-item');
    // $('#navigationNewRequest').addClass('black-text');
    // $('#navigationRequests').addClass('collection-item');
    // $('#navigationRequests').addClass('black-text');
    // $('#navigationSettings').addClass('collection-item');
    // $('#navigationSettings').addClass('black-text');
    //
    // $('#navigationSettings').addClass('blue-grey');
    // $('#navigationSettings').addClass('active');
    // $('#navigationSettings').removeClass('black-text');


    this.apiService.callApi('api/info', 'GET', null, this.currentUser.token)
      .then( loggedInUser => {
          this.currentUser.firstname = loggedInUser.firstname;
          this.roomNumber = loggedInUser.roomNumber;
        }
      );
    }

    changePassword() {
      if (this.passwordRepeat === this.passwordChangeData.newPassword) {
          this.apiService.callApi('api/password', 'PUT', this.passwordChangeData, this.currentUser.token).then(
            data => {
              this.message = 'Hasło zostało zmienione poprawnie';
            }
          ).catch(
            data => {
              this.message = 'Podczas zmiany hasła wystąpił błąd';
            }
          )
      } else {
        this.message = 'Wprowadzone nowe hasła nie zgadzają się';
      }
    }

}
