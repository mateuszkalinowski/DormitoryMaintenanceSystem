import {NgModule} from '@angular/core';
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
import {AdminPageComponent} from './admin/admin-page/admin-page.component';
import {InhabitantNewsPageComponent} from './inhabitant/inhabitant-news-page/inhabitant-news-page.component';
import {InhabitantNewRequestPageComponent} from './inhabitant/inhabitant-new-request-page/inhabitant-new-request-page.component';
import {InhabitantRequestsPageComponent} from './inhabitant/inhabitant-requests-page/inhabitant-requests-page.component';
import {InhabitantSettingsPageComponent} from './inhabitant/inhabitant-settings-page/inhabitant-settings-page.component';
import {InhabitantRequestDetailsComponent} from './inhabitant/inhabitant-request-details/inhabitant-request-details.component';
import {WorkerWaitingTasksComponent} from './worker/worker-waiting-tasks/worker-waiting-tasks.component';
import {WorkerAssignedTasksComponent} from './worker/worker-assigned-tasks/worker-assigned-tasks.component';
import {WorkerTaskDetailsComponent} from './worker/worker-task-details/worker-task-details.component';
import {WorkerSettingsPageComponent} from './worker/worker-settings-page/worker-settings-page.component';
import {WorkerNewAnnouncementPageComponent} from './worker/worker-new-announcement-page/worker-new-announcement-page.component';
import {WorkerOwnAnnouncementsPageComponent} from './worker/worker-own-announcements-page/worker-own-announcements-page.component';
import {AdminNewsPageComponent} from './admin/admin-news-page/admin-news-page.component';
import {AdminInhabitantsPageComponent} from './admin/admin-inhabitants-page/admin-inhabitants-page.component';
import {AdminWorkersPageComponent} from './admin/admin-workers-page/admin-workers-page.component';
import {AdminWorkerRegisterPageComponent} from './admin/admin-worker-register-page/admin-worker-register-page.component';
import {AdminTasksListPageComponent} from './admin/admin-tasks-list-page/admin-tasks-list-page.component';
import {AdminSettingsPageComponent} from './admin/admin-settings-page/admin-settings-page.component';
import {AdminTaskDetailsPageComponent} from './admin/admin-task-details-page/admin-task-details-page.component';

const routes: Routes = [
  {path: '', component: LogInComponent, canActivate: [AppEntranceGuardService]},
  {path: 'login', component: LogInComponent, canActivate: [LoginPageGuardService]},
  {path: 'workerRegistration', component: WorkerRegisterComponent},
  {path: 'inhabitantRegistration', component: InhabitantRegisterComponent},
  {
    path: 'inhabitantPage', component: InhabitantPageComponent, canActivate: [InhabitantPageGuardService],
    children: [
      {path: 'news', component: InhabitantNewsPageComponent},
      {path: 'newRequest', component: InhabitantNewRequestPageComponent},
      {path: 'requests', component: InhabitantRequestsPageComponent},
      {path: 'settings', component: InhabitantSettingsPageComponent},
      {path: 'requests/:id', component: InhabitantRequestDetailsComponent},
    ]
  },
  {
    path: 'workerPage', component: WorkerPageComponent, canActivate: [WorkerPageGuardService],
    children: [
      {path: 'waitingTasks', component: WorkerWaitingTasksComponent},
      {path: 'assignedTasks', component: WorkerAssignedTasksComponent},
      {path: 'taskDetails/:id', component: WorkerTaskDetailsComponent},
      {path: 'settings', component: WorkerSettingsPageComponent},
      {path: 'newAnnouncement', component: WorkerNewAnnouncementPageComponent},
      {path: 'myAnnouncements', component: WorkerOwnAnnouncementsPageComponent}
    ]
  },
  {
    path: 'adminPage', component: AdminPageComponent, canActivate: [AdminPageGuardService],
    children: [
      {path: 'news', component: AdminNewsPageComponent},
      {path: 'inhabitants', component: AdminInhabitantsPageComponent},
      {path: 'workers', component: AdminWorkersPageComponent},
      {path: 'workerRegister', component: AdminWorkerRegisterPageComponent},
      {path: 'tasks', component: AdminTasksListPageComponent},
      {path: 'tasks/:id', component: AdminTaskDetailsPageComponent},
      {path: 'settings', component: AdminSettingsPageComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
