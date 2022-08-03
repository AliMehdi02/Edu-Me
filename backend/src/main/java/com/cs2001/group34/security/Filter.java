package com.cs2001.group34.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;


@Component
public class Filter extends GenericFilterBean {

	String un = null;
	String token = null;

	@Autowired
	private JWT jwt;
	@Autowired
	private UserDetailsServiceImpl uds;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws ServletException, IOException {

		((HttpServletResponse) res).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		((HttpServletResponse) res).setHeader("Access-Control-Expose-Headers", "Authorization");
		String ah = ((HttpServletRequest) req).getHeader("Authorization");

		if (ah!=null && ah.startsWith("Bearer "))
		{
			token = ah.substring(7);
			un = jwt.gUN(token);
		}
		if (un!=null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails ud = uds.loadUserByUsername(un);
			if (jwt.CT(token,ud)){

				UsernamePasswordAuthenticationToken upad = new UsernamePasswordAuthenticationToken(ud,
						null,ud.getAuthorities());
				upad.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) req));
				SecurityContextHolder.getContext().setAuthentication(upad);
			}
		}
		filterChain.doFilter(req,res);
	}
}
