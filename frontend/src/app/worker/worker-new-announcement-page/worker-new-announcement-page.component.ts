import { Component, OnInit } from '@angular/core';
import {NewAnnouncement} from '../../dtos/newAnnouncement';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../../api.service';
import {CurrentUserService} from '../../current-user.service';

@Component({
  selector: 'app-worker-new-announcement-page',
  templateUrl: './worker-new-announcement-page.component.html',
  styleUrls: ['./worker-new-announcement-page.component.css']
})
export class WorkerNewAnnouncementPageComponent implements OnInit {

  newAnnouncement: NewAnnouncement;
  message: string;
  resultType: boolean;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private currentUser: CurrentUserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.newAnnouncement = new NewAnnouncement();
    this.message = '';
    this.newAnnouncement.title = '';
    this.newAnnouncement.content = '';
  }

  send() {
    if (this.newAnnouncement.title === '') {
      this.message = 'Wypełnij tytuł ogłoszenia';
      this.resultType = false;
      return false;
    }

    if (this.newAnnouncement.content === '') {
      this.message = 'Wypełnij zawartość ogłoszenia';
      this.resultType = false;
      return false;
    }

    this.apiService.callApi('api/announcement', 'post', this.newAnnouncement, this.currentUser.token).then(
      () => {
        this.message = 'Ogłoszenie zostało opublikowane';
        this.resultType = true;

        this.newAnnouncement.title = '';
        this.newAnnouncement.content = '';

        this.router.navigate(['/workerPage/myAnnouncements']);

      }
    ).catch(() => {
      this.message = 'Ogłoszenie nie zostało opublikowane z powodu nieznanego błędu';
      this.resultType = false;
    });
  }

}
