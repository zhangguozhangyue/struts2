package cn.bluedot.bigdatapig.control.interceptor;

import cn.bluedot.bigdatapig.control.context.ActionContext;
import cn.bluedot.bigdatapig.control.invocation.ActionInvocation;

public class AuthorityInterceptor implements Interceptor{
      @Override
    public void init() {
    	// TODO Auto-generated method stub
    	
    }
      @Override
    public String intercept(ActionInvocation invocation) {
    	ActionContext ac=invocation.getInvocationContext();
    	
    	return null;
    }
      @Override
    public void destory() {
    	// TODO Auto-generated method stub
    	
    }
}
