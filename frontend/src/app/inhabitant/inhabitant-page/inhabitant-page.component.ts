import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
declare var $: any;
@Component({
  selector: 'app-inhabitant-page',
  templateUrl: './inhabitant-page.component.html',
  styleUrls: ['./inhabitant-page.component.css']
})
export class InhabitantPageComponent implements OnInit, AfterViewInit {

  constructor(
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  logout() {
    this.apiService.callApi('api/logout', 'GET', null, this.currentUser.token);
    this.currentUser.remove();
    this.router.navigate(['/']);
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    // $('.dropdown-trigger').dropdown();
    // $(document).ready(function() {
    //   $('.fixed-action-btn').floatingActionButton();
    // });
  }

}
