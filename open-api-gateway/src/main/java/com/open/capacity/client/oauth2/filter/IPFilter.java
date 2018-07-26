package com.open.capacity.client.oauth2.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.open.capacity.client.oauth2.mapper.User;
import com.open.capacity.client.oauth2.mapper.UserTimesMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * IP过滤器
 *
 * @author owen 624191343@qq.com
 * <p>
 * 2017年10月14日
 */
public class IPFilter extends OncePerRequestFilter implements InitializingBean {

    private Integer TOKEN_TIMES = 5000;//每个token可以访问的上限
    Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationFailureHandler authenticationFailureHandler;

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


    // 所有需要拦截的url
    private Set<String> urls = new HashSet<>();
    // url匹配器
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet(); // 初始化其他bean参数

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = null;

        accessToken = extractToken(request);
        UserTimesMapper userTimesMapper = SpringUtil.getBean(UserTimesMapper.class);

        String ipAddr = this.getIpAddr(request);
        logger.info("请求IP地址为：[{}]", ipAddr);
        logger.info("头信息中的token为：[{}]", accessToken);
        if (accessToken != null) {
            User user = userTimesMapper.findTokenTimes(accessToken);
            if (user != null) {
                long times = user.getTimes();
                logger.info("访问的次数为：[{}]", times);
                if (times >= TOKEN_TIMES) {
                    logger.info("超过访问的次数限制！！！");
                    response.sendError(500, "超过访问的次数限制！！！");
                } else {
                    if (StringUtils.isNotBlank(user.getIp())) {
                        List<String> ips = Arrays.asList(user.getIp().split(","));
                        if (!ips.contains(ipAddr)) {
                            ipAddr = user.getIp() + ipAddr + ",";
                        } else {
                            ipAddr = ipAddr + ",";
                        }
                    } else {
                        ipAddr = ipAddr + ",";
                    }
                    userTimesMapper.updateTimes(accessToken, times + 1, ipAddr);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    protected String extractToken(HttpServletRequest request) {
        // first check the header...
        String token = extractHeaderToken(request);

        // bearer type allows a request parameter as well
        if (token == null) {
            logger.debug("Token not found in headers. Trying request parameters.");
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
            if (token == null) {
                logger.debug("Token not found in request parameters.  Not an OAuth2 request.");
            } else {
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
            }
        }

        return token;
    }

    /**
     * Extract the OAuth bearer token from a header.
     *
     * @param request The request.
     * @return The token, or null if no OAuth authorization header was supplied.
     */
    protected String extractHeaderToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders("Authorization");
        while (headers.hasMoreElements()) { // typically there is only one (most
            // servers enforce that)
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                // Add this here for the auth details later. Would be better to
                // change the signature of this method.
                request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
                        value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }

        return null;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
