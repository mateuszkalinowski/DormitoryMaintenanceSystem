import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LogInComponent } from './log-in/log-in.component';
import { FormsModule } from '@angular/forms';
import { InhabitantPageComponent } from './inhabitant/inhabitant-page/inhabitant-page.component';
import { WorkerPageComponent } from './worker/worker-page/worker-page.component';
import { InhabitantRegisterComponent } from './registration/inhabitant-register/inhabitant-register.component';
import { WorkerRegisterComponent } from './registration/worker-register/worker-register.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { InhabitantNewsPageComponent } from './inhabitant/inhabitant-news-page/inhabitant-news-page.component';
import { InhabitantNewRequestPageComponent } from './inhabitant/inhabitant-new-request-page/inhabitant-new-request-page.component';
import { InhabitantRequestsPageComponent } from './inhabitant/inhabitant-requests-page/inhabitant-requests-page.component';
import { InhabitantSettingsPageComponent } from './inhabitant/inhabitant-settings-page/inhabitant-settings-page.component';
import { InhabitantRequestDetailsComponent } from './inhabitant/inhabitant-request-details/inhabitant-request-details.component';
import { WorkerWaitingTasksComponent } from './worker/worker-waiting-tasks/worker-waiting-tasks.component';
import { WorkerAssignedTasksComponent } from './worker/worker-assigned-tasks/worker-assigned-tasks.component';
import { WorkerTaskDetailsComponent } from './worker/worker-task-details/worker-task-details.component';
import { WorkerSettingsPageComponent } from './worker/worker-settings-page/worker-settings-page.component';
import { WorkerNewAnnouncementPageComponent } from './worker/worker-new-announcement-page/worker-new-announcement-page.component';
import { WorkerOwnAnnouncementsPageComponent } from './worker/worker-own-announcements-page/worker-own-announcements-page.component';
@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    InhabitantPageComponent,
    WorkerPageComponent,
    InhabitantRegisterComponent,
    WorkerRegisterComponent,
    AdminPageComponent,
    InhabitantNewsPageComponent,
    InhabitantNewRequestPageComponent,
    InhabitantRequestsPageComponent,
    InhabitantSettingsPageComponent,
    InhabitantRequestDetailsComponent,
    WorkerWaitingTasksComponent,
    WorkerAssignedTasksComponent,
    WorkerTaskDetailsComponent,
    WorkerSettingsPageComponent,
    WorkerNewAnnouncementPageComponent,
    WorkerOwnAnnouncementsPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
