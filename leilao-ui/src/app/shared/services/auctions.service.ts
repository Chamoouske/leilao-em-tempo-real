import { Injectable, signal, Signal } from "@angular/core";
import { Apollo } from "apollo-angular";
import { GET_AUCTIONS, NEW_AUCTION } from "../graphql/querys";
import { Auction, SearchAuctionInput } from "../models/Auction";
import { map } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class AuctionsService {
    constructor(private apollo: Apollo) { }
    private auctions = signal<Auction[]>([]);

    public getAuctions(input: SearchAuctionInput) {
        this.apollo.watchQuery({
            query: GET_AUCTIONS,
            variables: {
                input
            },
            pollInterval: 5000
        }).valueChanges
            .pipe(map((result: any) => result.data.auctions))
            .subscribe((auctions: Auction[]) => {
                this.auctions.set(auctions);
            });

        return this.auctions;
    }

    public newAuction(input: Auction) {
        return this.apollo.mutate({
            mutation: NEW_AUCTION,
            variables: {
                input
            }
        });
    }
}
