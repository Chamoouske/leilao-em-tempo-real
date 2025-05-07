import { Component, computed, OnInit } from "@angular/core";
import { AuctionsService } from "../../shared/services/auctions.service";
import { Auction, BidAuctionInput, SearchAuctionInput } from "../../shared/models/Auction";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
    selector: 'auction-component',
    templateUrl: './auction.component.html',
    styleUrls: ['./auction.component.scss'],
    imports: [
        CommonModule,
        FormsModule,
        MatTableModule,
        MatInputModule,
        MatButtonModule,
        MatFormFieldModule
    ]
})
export class AuctionComponent implements OnInit {
    public auctions = computed(() => this.auctionsService.getAuctions({} as SearchAuctionInput)());
    private bidAmounts: Map<string, number> = new Map();
    private bidderIds: Map<string, string> = new Map();
    displayedColumns: string[] = ['id', 'name', 'description', 'currentBid', 'currentBidder', 'endDate', 'status', 'version', 'action'];

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

    public getBidAmount(auctionId: string): number {
        return this.bidAmounts.get(auctionId) || 0;
    }

    public setBidAmount(auctionId: string, amount: number) {
        this.bidAmounts.set(auctionId, amount);
    }

    public getBidderId(auctionId: string): string {
        return this.bidderIds.get(auctionId) || '';
    }

    public setBidderId(auctionId: string, bidderId: string) {
        this.bidderIds.set(auctionId, bidderId);
    }

    public newBid(auction: Auction) {
        const bidAmount = this.bidAmounts.get(auction.id) || 0;
        const bidderId = this.bidderIds.get(auction.id) || '1';

        this.auctionsService.newBid({
            auctionId: auction.id,
            bidderId: bidderId,
            amount: bidAmount,
            auctionVersion: auction.version
        }).subscribe(() => {
            this.bidAmounts.delete(auction.id);
            this.bidderIds.delete(auction.id);
            this.getAuctions();
        });
    }
}
