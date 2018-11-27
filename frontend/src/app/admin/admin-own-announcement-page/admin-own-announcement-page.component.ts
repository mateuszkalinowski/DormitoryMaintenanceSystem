import { Component, OnInit } from '@angular/core';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PaginationService} from '../../utils/pagination.service';

@Component({
  selector: 'app-admin-own-announcement-page',
  templateUrl: './admin-own-announcement-page.component.html',
  styleUrls: ['./admin-own-announcement-page.component.css']
})
export class AdminOwnAnnouncementPageComponent  implements OnInit {

  announcements: any;
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
    this.numberOfItemsOnPage = 4;
    this.paginationInfo = new PaginationInfo();
    this.showPage(0);
  }

  removeAnnouncement(id: number) {
    this.apiService.callApi('api/announcement/' + id, 'delete', null, this.currentUser.token).then(
      () => {
        if (this.announcements.length > 1 || this.activePage === 0) {
          this.showPage(this.activePage);
        } else {
          this.showPage(this.activePage - 1);
        }
      }
    );
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
          this.loadingFinished = true;
        }
      ).catch(
    );

  }

}
