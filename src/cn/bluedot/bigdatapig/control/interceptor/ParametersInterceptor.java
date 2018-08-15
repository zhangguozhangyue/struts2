package cn.bluedot.bigdatapig.control.interceptor;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import cn.bluedot.bigdatapig.control.context.ActionContext;
import cn.bluedot.bigdatapig.control.invocation.ActionInvocation;
import cn.bluedot.bigdatapig.control.stack.ValueStack;

/**
 * 参数拦截器，将请求参数封装到action属性中
 * 
 */
public class ParametersInterceptor implements Interceptor {

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) {
		ActionContext ac = invocation.getInvocationContext();
		HttpServletRequest req=ac.getRequest();
		ValueStack valueStack = ac.getValueStack();
		// 获取action对象
		Object action = valueStack.peek();
		try {
			// 将请求参数数据填充到action对象中
			BeanUtils.populate(action, ac.getRequest().getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.invoke(invocation);
	}
	@Override
	public void destory() {
	}

}
