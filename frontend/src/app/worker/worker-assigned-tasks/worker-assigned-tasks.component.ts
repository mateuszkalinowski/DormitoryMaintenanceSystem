import { Component, OnInit } from '@angular/core';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PaginationService} from '../../utils/pagination.service';

@Component({
  selector: 'app-worker-assigned-tasks',
  templateUrl: './worker-assigned-tasks.component.html',
  styleUrls: ['./worker-assigned-tasks.component.css']
})
export class WorkerAssignedTasksComponent implements OnInit {

  requests: any;
  numberOfItemsOnPage: number;

  activePage: number;

  numberOfPages: number;
  paginationInfo: PaginationInfo;

  constructor(
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router,
    private paginationService: PaginationService
  ) { }

  ngOnInit() {
    this.numberOfItemsOnPage = 8;
    this.paginationInfo = new PaginationInfo();
    this.showPage(0);
  }

  private showPage(pageNumber: number) {
    this.apiService.callApi('api/worker/task/assignedTasks?page=' + pageNumber + '&size=' + this.numberOfItemsOnPage,
      'GET', null, this.currentUser.token).then(
      data => {
        this.requests = data.content;

        this.numberOfPages = data.pageCount;

        this.paginationInfo = this.paginationService.pageContent(this.numberOfPages, pageNumber);
        this.activePage = pageNumber;
      }
    );
  }
}
