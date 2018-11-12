import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {Router} from '@angular/router';
import {PageRequest} from '../../dtos/pageRequest';
import {PaginationInfo} from '../../dtos/paginationInfo';
import {PaginationService} from '../../utils/pagination.service';
declare var $: any;

@Component({
  selector: 'app-inhabitant-news-page',
  templateUrl: './inhabitant-news-page.component.html',
  styleUrls: ['./inhabitant-news-page.component.css']
})
export class InhabitantNewsPageComponent implements OnInit {

  @ViewChild('navigationNews') navigationNews: ElementRef;

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

    this.apiService.callApi('api/announcement?page=' + pageNumber + '&size=' + this.numberOfItemsOnPage,
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
