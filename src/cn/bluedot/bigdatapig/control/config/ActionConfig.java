package cn.bluedot.bigdatapig.control.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 映射struts.xml中action标签的实体类
 * 
 */
public class ActionConfig {
	
	private String name;
	private String method;
	private String className;

	private Map<String, ResultConfig> resultMap = new LinkedHashMap<String, ResultConfig>();

	public String getName() {
		return name;
	}

	

	public Map<String, ResultConfig> getResultMap() {
		return resultMap;
	}



	public void setResultMap(Map<String, ResultConfig> resultMap) {
		this.resultMap = resultMap;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	@Override
	public String toString() {
		return "ActionConfig [name=" + name + ", method=" + method + ", className=" + className + ", results=" + resultMap
				+ "]";
	}

}
