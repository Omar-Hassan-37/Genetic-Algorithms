
public class InvestmentInfo {
	private String channelName;
	private Double ROI;
	private Double lowerBound;
	private Double upperBound;
	
	public InvestmentInfo(String channelName, Double ROI, Double lowerBound, Double upperBound) {
		super();
		this.channelName = channelName;
		this.ROI = ROI;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Double getLowerBound() {
		return (lowerBound == -1.0)? 0 : lowerBound;
	}
	public void setLowerBound(Double lowerBound) {
		this.lowerBound = lowerBound;
	}
	public Double getUpperBound() {
		return (upperBound == -1)? 1.0 : upperBound;
	}
	public void setUpperBound(Double upperBound) {
		this.upperBound = upperBound;
	}
	public Double getROI() {
		return ROI;
	}
	public void setROI(Double rOI) {
		ROI = rOI;
	}
	
	
	
}
