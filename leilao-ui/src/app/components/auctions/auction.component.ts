import { Component, computed, OnInit, signal } from "@angular/core";
import { AuctionsService } from "../../shared/services/auctions.service";
import { Auction, SearchAuctionInput } from "../../shared/models/Auction";
import { CommonModule, JsonPipe } from "@angular/common";

@Component({
    selector: 'auction-component',
    templateUrl: './auction.component.html',
    styleUrls: ['./auction.component.scss'],
    imports: [CommonModule, JsonPipe]
})
export class AuctionComponent implements OnInit {
    public auctions = computed(() => this.auctionsService.getAuctions({} as SearchAuctionInput)());

    constructor(private auctionsService: AuctionsService) { }

    ngOnInit() {
        this.getAuctions();
    }

    public newAuction() {
        this.auctionsService.newAuction({} as Auction).subscribe();
        this.getAuctions();
    }

    private getAuctions() {
        this.auctions = this.auctionsService.getAuctions({} as SearchAuctionInput);
    }
}
