package taetaena.com.chibbo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import taetaena.com.chibbo.repository.MemberRepository;
import taetaena.com.chibbo.repository.MemoryMemberRepository;
import taetaena.com.chibbo.service.MemberService;

@Configuration
public class SpringConfig {
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
