import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {LoginData} from '../dtos/loginData';
import {ApiService} from '../api.service';
import {CurrentUserService} from '../current-user.service';
import {Router} from '@angular/router';
declare var $: any;

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  @Input() loginData: LoginData;

  errorMessage: string;

  constructor(
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.errorMessage = '';
    this.loginData = new LoginData();
  }

  register() {
    this.router.navigate(['/register']);
  }

  login() {
    this.apiService.callApi('api/login', 'POST', this.loginData, null)
      .then(data => {
          this.currentUser.token = data.token;
          this.errorMessage = '';

          this.apiService.callApi('api/info', 'GET', null, this.currentUser.token)
            .then( loggedInUser => {
              this.currentUser.email = this.loginData.email;
              this.currentUser.firstname = loggedInUser.firstname;
              this.currentUser.lastname = loggedInUser.lastname;
              this.currentUser.role = loggedInUser.roles[0].userRole;
              this.router.navigate(['/']);
            }
          ).catch(loggedInUser => {
            this.errorMessage = 'Nie udało się zalogować';
          });
      }).catch(
        data => {
          this.errorMessage = 'Nie udało się zalogować';
        }
    );
  }
}
