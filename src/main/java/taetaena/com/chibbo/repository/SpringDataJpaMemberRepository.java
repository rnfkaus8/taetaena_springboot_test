package taetaena.com.chibbo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import taetaena.com.chibbo.domain.Member;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	
	@Override
	Optional<Member> findByName(String name);
	@Override
	Optional<Member> findByPhoneNum(String phoneNum);
	
}
