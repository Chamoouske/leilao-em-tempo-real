import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { HttpLink, InMemoryCache } from '@apollo/client';
import { environment } from '../environments/environment';
import { provideApollo } from 'apollo-angular';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    provideApollo(() => ({
      link: new HttpLink({
        uri: environment.graphQLUri,
      }),
      cache: new InMemoryCache()
    }))
  ]
};
