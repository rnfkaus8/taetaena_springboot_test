package taetaena.com.chibbo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		member.setPhoneNum("01043524350");
		memberRepository.save(member);
		
		Member result = memberRepository.findById(member.getId()).get();
		
		assertThat(result).isEqualTo(member);
	}

	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		member1.setPhoneNum("01043524350");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		member2.setPhoneNum("01043524350");
		memberRepository.save(member2);
		
		Member result = memberRepository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		member1.setPhoneNum("01043524351");
		memberRepository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		member2.setPhoneNum("01043524351");
		memberRepository.save(member2);
		
		List<Member> result = memberRepository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
	
	@Test
	public void findByPhoneNum() {
		Member member = new Member();
		member.setName("spring1");
		member.setPhoneNum("01043524350");
		memberRepository.save(member);
		
		Member result = memberRepository.findByPhoneNum(member.getPhoneNum()).get();
		assertThat(result).isEqualTo(member);
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		// Given
		Member member1 = new Member();
		member1.setName("spring1");
		member1.setPhoneNum("01043524350");
		Member member2 = new Member();
		member2.setName("spring");
		member2.setPhoneNum("01043524350");
		// When
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 예외가
																												// 발생해야
																												// 한다.
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
}
