import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';

@Component({
  selector: 'app-admin-task-details-page',
  templateUrl: './admin-task-details-page.component.html',
  styleUrls: ['./admin-task-details-page.component.css']
})
export class AdminTaskDetailsPageComponent implements OnInit {

  task: any;
  comment: string;
  message: string;
  resultType: boolean;

  loadingFinished: boolean;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.loadingFinished = false;
    const id = +this.route.snapshot.paramMap.get('id');

    this.apiService.callApi('api/admin/task/' + id + '/taskDetails', 'GET', null, this.currentUser.token).then(
      data => {
        this.task = data;
        this.loadingFinished = true;
      }
    );

  }

  delete() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.apiService.callApi('api/admin/task/' + id + '/deleteTask', 'delete', null, this.currentUser.token).then(
      () => {
        this.router.navigate(['/adminPage/tasks']);
      }
    );
  }

}
