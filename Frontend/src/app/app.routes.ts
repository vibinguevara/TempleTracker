import { Routes } from '@angular/router';
import {TempleListComponent} from './components/temple-list/temple-list.component';
import { TermsOfUseComponent } from './components/terms-of-use/terms-of-use.component';
import { PrivacyPolicyComponent } from './components/privacy-policy/privacy-policy.component';
import { ContactUSComponent } from './components/contact-s/contact-s.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { AddTempleComponent } from './components/add-temple/add-temple.component';
import { AddTempleEventComponent } from './components/add-temple-event/add-temple-event.component';

export const routes: Routes = [
    { path: '', component: TempleListComponent },
    { path: 'terms-of-use', component: TermsOfUseComponent },
    { path: 'privacy-policy', component: PrivacyPolicyComponent },
    { path: 'contact-us', component: ContactUSComponent },
    { path: 'about-us', component:AboutUsComponent},
    { path: 'add-temple', component: AddTempleComponent },
    { path: ':templeId/add-temple-event', component: AddTempleEventComponent }
];
