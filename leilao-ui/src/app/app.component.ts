import { Component } from '@angular/core';
import { environment } from '../environments/environment';
import { AuctionComponent } from './components/auctions/auction.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true,
  imports: [AuctionComponent]
})
export class AppComponent {
  public teste = environment.graphQLUri;
}
