import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {CurrentUserService} from '../current-user.service';

@Injectable({
  providedIn: 'root'
})

export class WorkerPageGuardService implements CanActivate {

  constructor(
    private currentUserService: CurrentUserService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.currentUserService.email && this.currentUserService.role === 'WORKER') {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}
