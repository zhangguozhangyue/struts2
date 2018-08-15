package cn.bluedot.bigdatapig.control.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bluedot.bigdatapig.control.stack.ValueStack;

/**
 * 数据中心
 * 
 */
public class ActionContext {

	public static final String REQUEST = "request";
	public static final String RESPONSE = "response";
	public static final String SESSION = "session";
	public static final String APPLICATION = "application";
	public static final String PARAMETERS = "parameters";
	public static final String VALUESTACK = "valuestack";

	private Map<String, Object> context;
	public static ThreadLocal<ActionContext> actionContext = new ThreadLocal<ActionContext>();

	public ActionContext(HttpServletRequest request, HttpServletResponse response, Object action) {
		context = new HashMap<String, Object>();
		// 准备域
		// request
		context.put(REQUEST, request);
		// response
		context.put(RESPONSE, response);
		// session
		context.put(SESSION, request.getSession());
		// application
		context.put(APPLICATION, request.getSession().getServletContext());
		// parameters
		context.put(PARAMETERS, request.getParameterMap());
		// valuestack
		ValueStack vs = new ValueStack();
		// 将action压入栈顶
		vs.push(action);
		// 将ValueStack放入request域中
		request.setAttribute(VALUESTACK, vs);
		// 将ValueStack放入context域中
		context.put(VALUESTACK, vs);
		// 为当前请求线程设置好ActionContext
		actionContext.set(this);
	}

	/**
	 * 当前线程对应的ActionContext对象
	 */
	public static ActionContext getContext() {
		return actionContext.get();
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) context.get(REQUEST);
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) context.get(RESPONSE);
	}

	public HttpSession getSession() {
		return (HttpSession) context.get(SESSION);
	}

	public ServletContext getApplication() {
		return (ServletContext) context.get(APPLICATION);
	}

	public Map<String, String[]> getParams() {
		return (Map<String, String[]>) context.get(PARAMETERS);
	}

	public ValueStack getValueStack() {
		return (ValueStack) context.get(VALUESTACK);
	}

}
