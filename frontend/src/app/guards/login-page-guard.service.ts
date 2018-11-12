import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {CurrentUserService} from '../current-user.service';

@Injectable({
  providedIn: 'root'
})
export class LoginPageGuardService implements CanActivate {

  constructor(
    private currentUserService: CurrentUserService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.currentUserService.email) {
      this.router.navigate(['/']);
      return false;
    } else {
      return true;
    }
  }
}
