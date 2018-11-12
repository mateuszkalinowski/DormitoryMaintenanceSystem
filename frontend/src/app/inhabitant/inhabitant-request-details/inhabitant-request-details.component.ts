import { Component, OnInit } from '@angular/core';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';
import {ActivatedRoute, Router} from '@angular/router';
declare var $: any;

@Component({
  selector: 'app-inhabitant-request-details',
  templateUrl: './inhabitant-request-details.component.html',
  styleUrls: ['./inhabitant-request-details.component.css']
})
export class InhabitantRequestDetailsComponent implements OnInit {

  request: any;



  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  ngOnInit() {
    // $('#navigationNews').removeClass();
    // $('#navigationNewRequest').removeClass();
    // $('#navigationRequests').removeClass();
    // $('#navigationSettings').removeClass();
    //
    // $('#navigationNews').addClass('collection-item');
    // $('#navigationNews').addClass('black-text');
    // $('#navigationNewRequest').addClass('collection-item');
    // $('#navigationNewRequest').addClass('black-text');
    // $('#navigationRequests').addClass('collection-item');
    // $('#navigationRequests').addClass('black-text');
    // $('#navigationSettings').addClass('collection-item');
    // $('#navigationSettings').addClass('black-text');
    //
    // $('#navigationRequests').addClass('blue-grey');
    // $('#navigationRequests').addClass('active');
    // $('#navigationRequests').removeClass('black-text');

    this.getRequest();
    this.request = '';
  }

  getRequest() {
    const id = +this.route.snapshot.paramMap.get('id');

    this.apiService.callApi('api/inhabitant/task/' + id, 'GET', null, this.currentUser.token).then(
      data => {
        this.request = data;
      }
    ).catch(
      data => {
        this.router.navigate(['/inhabitantPage/requests']);
      }
    );

  }

  removeRequest() {
    const id = +this.route.snapshot.paramMap.get('id');

    this.apiService.callApi('api/inhabitant/task/' + id, 'DELETE', null, this.currentUser.token).then(
      data => {
        this.router.navigate(['/inhabitantPage/requests']);
      }
    );
  }
}
