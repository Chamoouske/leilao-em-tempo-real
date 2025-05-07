export type Auction = {
    version: number;
    status: string;
    name: string;
    id: string;
    endDate: string;
    description: string;
    currentBidder: string;
    currentBid: number;
    createdAt: string;
}

export type BidAuctionInput = {
    auctionId: string;
    bidderId: string;
    amount: number;
    auctionVersion: number;
}

export type SearchAuctionInput = {
    amount: number;
    bidderId: string;
    auctionId: string;
    description: string;
}
