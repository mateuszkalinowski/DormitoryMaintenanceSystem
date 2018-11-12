import { Injectable } from '@angular/core';
import {PaginationInfo} from '../dtos/paginationInfo';

@Injectable({
  providedIn: 'root'
})
export class PaginationService {

  constructor() { }

  pageContent(numberOfPages: number, pageNumber: number ) {
    let paginationInfo: PaginationInfo;

    paginationInfo = new PaginationInfo();

    paginationInfo.pagesAfterActive = [];
    paginationInfo.pagesBeforeActive = [];


    if (numberOfPages <= 2) {
      if (pageNumber === 0) {
        paginationInfo.pagesBeforeActive = [];
        paginationInfo.pagesAfterActive.push(1);
      }
      if (pageNumber === 1) {
        paginationInfo.pagesBeforeActive.push(0);
      }
    } else {

      if (pageNumber === numberOfPages - 1) {
        if (numberOfPages >= 5) {
          paginationInfo.pagesBeforeActive.push(pageNumber - 4);
        }
        if (numberOfPages >= 4) {
          paginationInfo.pagesBeforeActive.push(pageNumber - 3);
        }
      }

      if (pageNumber === numberOfPages - 2) {
        if (numberOfPages >= 5) {
          paginationInfo.pagesBeforeActive.push(pageNumber - 3);
        }
      }

      if (pageNumber > 1) {
        paginationInfo.pagesBeforeActive.push(pageNumber - 2);
      }
      if (pageNumber > 0) {
        paginationInfo.pagesBeforeActive.push(pageNumber - 1);
      }
      if (pageNumber + 1 < numberOfPages) {
        paginationInfo.pagesAfterActive.push(pageNumber + 1);
      }
      if (pageNumber + 2 < numberOfPages) {
        paginationInfo.pagesAfterActive.push(pageNumber + 2);
      }

      if (pageNumber === 0) {
        if (numberOfPages >= 4) {
          paginationInfo.pagesAfterActive.push(pageNumber + 3);
        }
        if (numberOfPages >= 5) {
          paginationInfo.pagesAfterActive.push(pageNumber + 4);
        }
      }
      if (pageNumber === 1) {
        if (numberOfPages >= 5) {
          paginationInfo.pagesAfterActive.push(pageNumber + 3);
        }
      }


    }

    return paginationInfo;
  }
}
