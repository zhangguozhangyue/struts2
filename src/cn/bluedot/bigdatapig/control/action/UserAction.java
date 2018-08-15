package cn.bluedot.bigdatapig.control.action;

import cn.bluedot.bigdatapig.control.context.ActionContext;

public class UserAction {
	private String username;
	private String pwd;
	public String list() {
	//	if("张国".equals(username)&&"123".equals(pwd)) {
       /* UserAction user=new UserAction();
        user.getUsername();
        user.getPwd();*/
		ActionContext.getContext().getResponse().setCharacterEncoding("utf-8");
		ActionContext.getContext().getResponse().setContentType("text/html;charset=utf-8");
		ActionContext.getContext().getSession().setAttribute("user", username);
		return "success";
		
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
