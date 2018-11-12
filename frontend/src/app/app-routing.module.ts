import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LogInComponent} from './log-in/log-in.component';
import {AppEntranceGuardService} from './guards/app-entrance-guard.service';
import {InhabitantPageComponent} from './inhabitant/inhabitant-page/inhabitant-page.component';
import {WorkerPageComponent} from './worker/worker-page/worker-page.component';
import {LoginPageGuardService} from './guards/login-page-guard.service';
import {InhabitantPageGuardService} from './guards/inhabitant-page-guard.service';
import {WorkerPageGuardService} from './guards/worker-page-guard.service';
import {WorkerRegisterComponent} from './registration/worker-register/worker-register.component';
import {InhabitantRegisterComponent} from './registration/inhabitant-register/inhabitant-register.component';
import {AdminPageGuardService} from './guards/admin-page-guard.service';
import {AdminPageComponent} from './admin-page/admin-page.component';
import {InhabitantNewsPageComponent} from './inhabitant/inhabitant-news-page/inhabitant-news-page.component';
import {InhabitantNewRequestPageComponent} from './inhabitant/inhabitant-new-request-page/inhabitant-new-request-page.component';
import {InhabitantRequestsPageComponent} from './inhabitant/inhabitant-requests-page/inhabitant-requests-page.component';
import {InhabitantSettingsPageComponent} from './inhabitant/inhabitant-settings-page/inhabitant-settings-page.component';
import {InhabitantRequestDetailsComponent} from './inhabitant/inhabitant-request-details/inhabitant-request-details.component';
import {WorkerWaitingTasksComponent} from './worker/worker-waiting-tasks/worker-waiting-tasks.component';

const routes: Routes = [
  { path: '', component: LogInComponent, canActivate: [AppEntranceGuardService] },
  { path: 'login', component: LogInComponent, canActivate: [LoginPageGuardService] },
  { path: 'workerRegistration', component: WorkerRegisterComponent },
  { path: 'inhabitantRegistration', component: InhabitantRegisterComponent},
  { path: 'inhabitantPage', component: InhabitantPageComponent, canActivate: [InhabitantPageGuardService],
    children: [
      { path: 'news', component: InhabitantNewsPageComponent },
      { path: 'newRequest', component: InhabitantNewRequestPageComponent },
      { path: 'requests', component: InhabitantRequestsPageComponent },
      { path: 'settings', component: InhabitantSettingsPageComponent },
      { path: 'requests/:id', component: InhabitantRequestDetailsComponent },
    ]},
  { path: 'workerPage', component: WorkerPageComponent, canActivate: [WorkerPageGuardService],

    children: [
      {path: 'waitingTasks', component: WorkerWaitingTasksComponent },
    ]},
  { path: 'adminPage', component: AdminPageComponent, canActivate: [AdminPageGuardService]},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
