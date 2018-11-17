import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PaginationService} from '../../utils/pagination.service';

@Component({
  selector: 'app-admin-news-page',
  templateUrl: './admin-news-page.component.html',
  styleUrls: ['./admin-news-page.component.css']
})
export class AdminNewsPageComponent implements OnInit {


  @ViewChild('navigationNews') navigationNews: ElementRef;

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

  showPage(pageNumber: number) {

    this.apiService.callApi('api/announcement?page=' + pageNumber + '&size=' + this.numberOfItemsOnPage,
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

  removeAnnouncement(id: number) {
    this.apiService.callApi('api/announcement/asAdmin/' + id, 'delete', null, this.currentUser.token).then(
      () => {
        if (this.announcements.length > 1 || this.activePage === 0) {
          this.showPage(this.activePage);
        } else {
          this.showPage(this.activePage - 1);
        }
      }
    );
  }

}
