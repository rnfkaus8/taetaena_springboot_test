package taetaena.com.chibbo;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import taetaena.com.chibbo.repository.JdbcMemberRepository;
import taetaena.com.chibbo.repository.JdbcTemplateMemberRepository;
import taetaena.com.chibbo.repository.JpaMemberRepository;
import taetaena.com.chibbo.repository.MemberRepository;
import taetaena.com.chibbo.repository.MemoryMemberRepository;
import taetaena.com.chibbo.service.MemberService;

@Configuration
public class SpringConfig {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
////	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
}
