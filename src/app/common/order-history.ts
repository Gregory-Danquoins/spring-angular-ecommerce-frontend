export class OrderHistory {
  constructor(
    public id: string,
    public oderTrackingNumber: string,
    public totalPrice: number,
    public totalQuantity: number,
    public dateCreated: Date
  ) {}
}
