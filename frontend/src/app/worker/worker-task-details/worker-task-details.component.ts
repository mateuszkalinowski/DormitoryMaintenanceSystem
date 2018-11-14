import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';

@Component({
  selector: 'app-worker-task-details',
  templateUrl: './worker-task-details.component.html',
  styleUrls: ['./worker-task-details.component.css']
})
export class WorkerTaskDetailsComponent implements OnInit {

  task: any;
  states: string[];
  comment: string;
  message: string;
  resultType: boolean;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');

    this.apiService.callApi('api/utils/possibleTaskStates', 'get', null, null).then(
      data => {
        this.states = data;
      }
    );

    this.apiService.callApi('api/worker/task/' + id + '/taskDetails', 'GET', null, this.currentUser.token).then(
      data => {
        this.task = data;
      }
    );

  }

  send(status: number) {
    const id = +this.route.snapshot.paramMap.get('id');
      const taskUpdate = {
        status: status,
        comment: this.task.comment
      };
    if (this.task.comment.length > 512) {
      this.resultType = false;
      this.message = 'Komentarz jest zbyt długi';
      return;
    }

      this.apiService.callApi('api/worker/task/' + id + '/update', 'post', taskUpdate, this.currentUser.token).then(
        () => {
          this.apiService.callApi('api/worker/task/' + id + '/taskDetails', 'GET', null, this.currentUser.token).then(
            data => {
              this.task = data;
            }
          );
          this.resultType = true;
          this.message = 'Zadanie zostało pomyślnie zaktualizowane';
        }
      ).catch(
        () => {
          this.resultType = false;
          this.message = 'Wystąpił błąd przy aktualizacji zgłoszenia';
        }
      );
  }

}
