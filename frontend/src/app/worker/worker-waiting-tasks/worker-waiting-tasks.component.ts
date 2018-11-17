import {AfterViewInit, Component, OnInit} from '@angular/core';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PaginationService} from '../../utils/pagination.service';

@Component({
  selector: 'app-worker-waiting-tasks',
  templateUrl: './worker-waiting-tasks.component.html',
  styleUrls: ['./worker-waiting-tasks.component.css']
})
export class WorkerWaitingTasksComponent implements OnInit {

  requests: any;
  numberOfItemsOnPage: number;

  activePage: number;

  numberOfPages: number;
  paginationInfo: PaginationInfo;

  loadingFinished: boolean;

  constructor(
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router,
    private paginationService: PaginationService
  ) { }

  ngOnInit() {
    this.loadingFinished = false;
    this.numberOfItemsOnPage = 8;
    this.paginationInfo = new PaginationInfo();
    this.showPage(0);
  }

  takeTask(taskId: number) {
    this.apiService.callApi('api/worker/task/' + taskId + '/assignToMe', 'post', null, this.currentUser.token).then(
      data => {
        this.showPage(this.activePage);
      }
    );
  }

  private showPage(pageNumber: number) {
    this.apiService.callApi('api/worker/task/waitingTasks?page=' + pageNumber + '&size=' + this.numberOfItemsOnPage,
      'GET', null, this.currentUser.token).then(
      data => {
        this.requests = data.content;

        this.numberOfPages = data.pageCount;

        this.paginationInfo = this.paginationService.pageContent(this.numberOfPages, pageNumber);
        this.activePage = pageNumber;
        this.loadingFinished = true;
      }
    );
  }
}
