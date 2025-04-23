import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ApolloClientOptions, InMemoryCache } from '@apollo/client/core';
import { HttpLink } from '@apollo/client';
import { APOLLO_OPTIONS } from 'apollo-angular';
import { environment } from '../environments/environment';

export function createApollo(): ApolloClientOptions<any> {
  return {
    link: new HttpLink({
      uri: environment.graphQLUri,
    }),
    cache: new InMemoryCache(),
  };
}

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
  ],
  providers: [
    {
      provide: APOLLO_OPTIONS,
      useFactory: createApollo,
      deps: [HttpLink],
    },
  ],
})
export class AppModule { }
