package taetaena.com.chibbo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import taetaena.com.chibbo.domain.Member;
import taetaena.com.chibbo.repository.MemberRepository;

@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		
	}
	
	/*
	 * ȸ�� ����
	 */
	public Long join(Member member) {
		
		vaildateDuplicateMember(member); // �ߺ�ȸ�� ����
		memberRepository.save(member);
		return member.getId();
	}
	
	// ���� �̸��� �ִ� ȸ���� �ߺ� ȸ������ Ī��
	// �ߺ�ȸ���� ������ �ȵ�
	private void vaildateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				.ifPresent(m -> {
					throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
				});
	}
	
	/*
	 * ��ü ȸ�� ����
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long MemberId){
		return memberRepository.findById(MemberId);
	}
	
}
