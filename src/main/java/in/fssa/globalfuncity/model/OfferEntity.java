package in.fssa.globalfuncity.model;

public class OfferEntity {

	private int offerId;
	private String offerName;
	private String offerDescription;
	private String offerSmallDesc;
	private String offerImg;
	private String offerDetail;
	private String offerInclusion;
	
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public String getOfferSmallDesc() {
		return offerSmallDesc;
	}
	public void setOfferSmallDesc(String offerSmallDesc) {
		this.offerSmallDesc = offerSmallDesc;
	}
	public String getOfferImg() {
		return offerImg;
	}
	public void setOfferImg(String offerImg) {
		this.offerImg = offerImg;
	}
	public String getOfferDetail() {
		return offerDetail;
	}
	public void setOfferDetail(String offerDetail) {
		this.offerDetail = offerDetail;
	}
	public String getOfferInclusion() {
		return offerInclusion;
	}
	public void setOfferInclusion(String offerInclusion) {
		this.offerInclusion = offerInclusion;
	}
	
	@Override
	public String toString() {
		return "OfferEntity [offerId=" + offerId + ", offerName=" + offerName + ", offerDescription=" + offerDescription
				+ ", offerSmallDesc=" + offerSmallDesc + ", offerImg=" + offerImg + ", offerDetail=" + offerDetail
				+ ", offerInclusion=" + offerInclusion + "]";
	}
}
