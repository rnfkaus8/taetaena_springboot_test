package taetaena.com.chibbo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import taetaena.com.chibbo.domain.Member;
import taetaena.com.chibbo.repository.MemberRepository;
import taetaena.com.chibbo.service.MemberService;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		memberRepository.save(member);
		
		Member result = memberRepository.findById(member.getId()).get();
		
		assertThat(result).isEqualTo(member);
	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		memberRepository.save(member2);
		
		Member result = memberRepository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		memberRepository.save(member2);
		
		List<Member> result = memberRepository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
}
