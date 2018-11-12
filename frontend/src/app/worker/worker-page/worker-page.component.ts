import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-worker-page',
  templateUrl: './worker-page.component.html',
  styleUrls: ['./worker-page.component.css']
})
export class WorkerPageComponent implements OnInit, AfterViewInit {

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
