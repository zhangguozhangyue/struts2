package cn.bluedot.bigdatapig.control.config;

public class ResultConfig {
	private String name="success";
	private String type="dispatcher";
	private String localtion;
	
	public ResultConfig() {
		
	}
	public ResultConfig(String localtion) {
		super();
		this.localtion = localtion;
	}
	public ResultConfig(String type, String localtion) {
		super();
		this.type = type;
		this.localtion = localtion;
	}
	public ResultConfig(String name, String type, String localtion) {
		super();
		this.name = name;
		this.type = type;
		this.localtion = localtion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocaltion() {
		return localtion;
	}
	public void setLocaltion(String localtion) {
		this.localtion = localtion;
	}
	
}
