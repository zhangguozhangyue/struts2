package cn.bluedot.bigdatapig.control.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bluedot.bigdatapig.control.config.ActionConfig;
import cn.bluedot.bigdatapig.control.config.ConfigurationManager;
import cn.bluedot.bigdatapig.control.config.ResultConfig;
import cn.bluedot.bigdatapig.control.context.ActionContext;
import cn.bluedot.bigdatapig.control.invocation.ActionInvocation;

/**
 * 核心拦截器
 * 
 */
public class StrutsPrepareAndExecuteFilter implements Filter {
	// 存放拦截器链中每个拦截器全限定类名
	private List<String> interceptors;
	// 请求后缀
	private String extension;
	// 所有action配置信息
	private Map<String, ActionConfig> actionConfigs;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 准备拦截器链配置信息
		interceptors = ConfigurationManager.getInterceptors();
		// 准备constant配置的访问后缀信息
		extension = ConfigurationManager.getConstant("struts.action.extension");
		// action配置信息
		actionConfigs = ConfigurationManager.getActions();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 将request和response强转成HttpServletRequest和HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 获得请求路径
		String reqPath = req.getRequestURI();
		
		// 判断是否要访问action
		if (!reqPath.endsWith(".action")) {
			// 请求路径名后缀不是指定的后缀名，放行
			System.out.println("数据进来");
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			chain.doFilter(request, response);
			return;
		} else {
			// 以指定后缀名访问，说明需要访问action
			// 获得需要访问action的名称，如Hello.action
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			String reqName = reqPath.substring(reqPath.lastIndexOf("/") + 1, reqPath.lastIndexOf("."));
			// 查找action对应的配置信息
			ActionConfig config = actionConfigs.get(reqName);
			if (config == null) {
				// 未找到该action的配置信息
				throw new RuntimeException("未找到" + reqPath + "的配置信息");
			}
			// 创建ActionInvocation，完成对拦截器链和action的调用
			ActionInvocation invocation = new ActionInvocation(interceptors, config, req, resp);		
			// 获得结果串
			String result = invocation.invoke(invocation);
			System.out.println(req.getSession().getAttribute("user"));
			// 根据结果串查找配置信息中的对应路径
			ResultConfig r = config.getResultMap().get(result);
			if("redirect".equals(r.getType())) {
				resp.sendRedirect(req.getServletContext().getContextPath()+r.getLocaltion());
				return;
			}
			String dispatcherPath=r.getLocaltion();
			if (dispatcherPath == null || "".equals(dispatcherPath.trim())) {
				throw new RuntimeException("未找到" + result + "对应的路径");
			}
			// 请求转发配置的路径
			req.getRequestDispatcher(r.getLocaltion()).forward(req, resp);
			
			// 释放资源
			ActionContext.actionContext.remove();
		}
	}

	@Override
	public void destroy() {
	}

}
