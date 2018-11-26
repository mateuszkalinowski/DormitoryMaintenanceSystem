import { Component, OnInit } from '@angular/core';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PaginationService} from '../../utils/pagination.service';

@Component({
  selector: 'app-admin-inhabitants-page',
  templateUrl: './admin-inhabitants-page.component.html',
  styleUrls: ['./admin-inhabitants-page.component.css']
})
export class AdminInhabitantsPageComponent implements OnInit {

  inhabitants: any;
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
    this.numberOfItemsOnPage = 12;
    this.paginationInfo = new PaginationInfo();
    this.showPage(0);
  }

  showPage(pageNumber: number) {

    this.apiService.callApi('api/inhabitant/getAll?page=' + pageNumber + '&size=' + this.numberOfItemsOnPage,
      'GET', null, this.currentUser.token)
      .then(
        data => {
          this.inhabitants = data.content;

          this.numberOfPages = data.pageCount;

          if ( pageNumber >= this.numberOfPages) {
            pageNumber = this.numberOfPages - 1;
            this.showPage(pageNumber);
            return;
          }

          this.paginationInfo = this.paginationService.pageContent(this.numberOfPages, pageNumber);
          this.activePage = pageNumber;
          this.loadingFinished = true;

        }
      ).catch(
    );
  }

  activateUser(id: number) {

    this.apiService.callApi('api/user/setAccepted/' + id, 'post', null, this.currentUser.token).then(() => {
      this.showPage(this.activePage);
    });
  }

  rejectUser(id: number) {
    this.apiService.callApi('api/user/setRejected/' + id, 'post', null, this.currentUser.token).then(() => {
      this.showPage(this.activePage);
    });
  }

  suspendUser(id: number) {
    this.apiService.callApi('api/user/setSuspended/' + id, 'post', null, this.currentUser.token).then(() => {
      this.showPage(this.activePage);
    });
  }


}
