import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {CurrentUserService} from '../current-user.service';

@Injectable({
  providedIn: 'root'
})
export class AppEntranceGuardService implements CanActivate {

  constructor(
    private currentUserService: CurrentUserService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.currentUserService.email) {
        if (this.currentUserService.role === 'INHABITANT') {
          this.router.navigate(['/inhabitantPage/news']);
          return false;
        } else if (this.currentUserService.role === 'WORKER') {
          this.router.navigate(['/workerPage/waitingTasks']);
          return false;
        } else if (this.currentUserService.role === 'ADMIN') {
          this.router.navigate(['/adminPage/news']);
          return false;
        } else {
          this.router.navigate(['/login']);
        }
      } else {
        this.router.navigate(['/login']);
        return false;
      }
    return false;
  }
}
