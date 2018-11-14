import { Component, OnInit } from '@angular/core';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PaginationService} from '../../utils/pagination.service';

@Component({
  selector: 'app-worker-own-announcements-page',
  templateUrl: './worker-own-announcements-page.component.html',
  styleUrls: ['./worker-own-announcements-page.component.css']
})
export class WorkerOwnAnnouncementsPageComponent implements OnInit {

  announcements: any;
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
    this.numberOfItemsOnPage = 4;
    this.paginationInfo = new PaginationInfo();
    this.showPage(0);
  }

  showPage(pageNumber: number) {

    this.apiService.callApi('api/announcement/getAllBySender?page=' + pageNumber + '&size=' + this.numberOfItemsOnPage,
      'GET', null, this.currentUser.token)
      .then(
        data => {
          this.announcements = data.content;

          this.numberOfPages = data.pageCount;

          this.paginationInfo = this.paginationService.pageContent(this.numberOfPages, pageNumber);
          this.activePage = pageNumber;

        }
      ).catch(
    );
  }

}