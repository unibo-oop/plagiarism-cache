package streamline;

public class StreamLineSelfMessage {
	
	private final int streamLineId;
	private Object value;
	
	public StreamLineSelfMessage(int streamLineId, Object value) {
		
		this.streamLineId = streamLineId;
		this.value = value;
		
	}
	
	public int getStreamLineId() {
		
		return this.streamLineId;
	}
	
	public Object getValue() {
		return this.value;
	}	
}
