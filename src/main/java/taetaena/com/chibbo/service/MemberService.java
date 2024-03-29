package taetaena.com.chibbo.service;

import java.util.List;
import java.util.Optional;


import taetaena.com.chibbo.domain.Member;
import taetaena.com.chibbo.repository.MemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		
	}
	
	/*
	 * 회원 가입
	 */
	public Long join(Member member) {
		
		vaildateDuplicateMember(member); // 중복회원 검증
		memberRepository.save(member);
		return member.getId();
	}
	
	// 같은 이름이 있는 회원은 중복 회원으로 칭함
	// 같은 전화번호가 있는 회원은 중복 회원으로 칭함
	// 중복회원이 있으면 안됨
	private void vaildateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
		memberRepository.findByPhoneNum(member.getPhoneNum())
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
		
	}
	
	/*
	 * 전체 회원 조희
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long MemberId){
		return memberRepository.findById(MemberId);
	}
	
}
