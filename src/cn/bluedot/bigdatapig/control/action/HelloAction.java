package cn.bluedot.bigdatapig.control.action;

import cn.bluedot.bigdatapig.control.context.ActionContext;

public class HelloAction {
	
	private String name;
	private String year;
	private int age;
	public String execute(){
		//将year放到request域中
		 ActionContext.getContext().getRequest().setAttribute("year", year);
		//将name放到request域中
		 ActionContext.getContext().getRequest().setAttribute("name", name);
		//将Jason字符串放到request域中
		 ActionContext.getContext().getRequest().setAttribute("age", age);
		 
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}	
