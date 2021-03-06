package kr.co.chahoo.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class CustomAuthenticationProvider implements AuthenticationProvider {  

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}



	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String user_id = (String)authentication.getPrincipal();     
		String user_pw = (String)authentication.getCredentials();
		logger.info("로그인 검증 {}", user_id);
		//logger.info("로그인 입력정보 {}", user_id + "/" + user_pw);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user_id, user_pw, roles);
		result.setDetails(new CustomUserDetails(user_id, user_pw));
		return result;          

		/*logger.info("로그인 실패");
            throw new BadCredentialsException("Bad credentials");
		*/
	}
}
