import { gql } from "apollo-angular";

const GET_AUCTIONS = gql`
  query GetAuctions($input: SearchBidInput!) {
    auctions(input: $input) {
        version
        status
        name
        id
        endDate
        description
        currentBidder
        currentBid
        createdAt
    }
  }
`;

const NEW_AUCTION = gql`
  mutation NewAuction($input: AuctionInput!) {
    createAuction(input: $input) {
      success
      message
    }
  }
`;

const BID_AUCTION = gql`
  mutation BidAuction($input: BidInput!) {
    createBid(input: $input) {
      success
      message
    }
  }
`;

export { GET_AUCTIONS, NEW_AUCTION, BID_AUCTION };
