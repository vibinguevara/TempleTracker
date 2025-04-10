import { Routes } from '@angular/router';
import {TempleListComponent} from './components/temple-list/temple-list.component';
import { TermsOfUseComponent } from './components/terms-of-use/terms-of-use.component';
import { PrivacyPolicyComponent } from './components/privacy-policy/privacy-policy.component';
import { ContactUSComponent } from './components/contact-s/contact-s.component';

export const routes: Routes = [
    { path: '', component: TempleListComponent },
    { path: 'terms-of-use', component: TermsOfUseComponent },
    { path: 'privacy-policy', component: PrivacyPolicyComponent },
    { path: 'contact-us', component: ContactUSComponent }
];
