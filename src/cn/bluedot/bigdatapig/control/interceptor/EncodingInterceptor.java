package cn.bluedot.bigdatapig.control.interceptor;

import java.io.UnsupportedEncodingException;

import cn.bluedot.bigdatapig.control.context.ActionContext;
import cn.bluedot.bigdatapig.control.invocation.ActionInvocation;

/**
 * 编码拦截器，
 * 
 */
public class EncodingInterceptor implements Interceptor{
   @Override
public void init() {
	// TODO Auto-generated method stub
	
}
   @Override
	public String intercept(ActionInvocation invocation) {
	   ActionContext ac=invocation.getInvocationContext();
		try {
			ac.getRequest().setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ac.getResponse().setContentType("text/html;charset=utf-8");	
		return invocation.invoke(invocation);
	}
   @Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}
}
