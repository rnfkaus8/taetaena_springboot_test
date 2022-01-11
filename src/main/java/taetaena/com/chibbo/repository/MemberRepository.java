package taetaena.com.chibbo.repository;

import java.util.List;
import java.util.Optional;


import taetaena.com.chibbo.domain.Member;


public interface MemberRepository {
	
	Member save(Member memeber);
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	Optional<Member> findByPhoneNum(String phoneNum);
	List<Member> findAll();
	
	
	
}
