package com.zerock;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.zerock.domain.Member;
import com.zerock.domain.Profile;
import com.zerock.persistence.MemberRepository;
import com.zerock.persistence.ProfileRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ProfileTests {
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Test
	@Ignore
	public void testInsertMembers() {
		IntStream.range(1, 101).forEach(i -> {
			Member member = new Member();
			member.setUid("user"+i);
			member.setUname("사용자"+i);
			member.setUpw("pw"+i);
			
			memberRepository.save(member);
		});
	}
	@Test
	@Ignore
	public void testInsertProfile() {
		Member member = new Member();
		member.setUid("user1");
		
		for(int i = 1; i<5; i++) {
			Profile profile1 = new Profile();
			
			profile1.setFname("face"+i+".jpg");
			
			if(i==1) {
				profile1.setCurrent(true);
			}
			profile1.setMember(member);
			profileRepository.save(profile1);
		}
	}
	@Test
	@Ignore
	public void testFetchJoin1() {
		List<Object[]> result = memberRepository.getMemberWithProfileCount("user1");
		
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
	@Test
	public void testFetchJoin2() {
		List<Object[]> result = memberRepository.getMemberWithProfile("user1");
		
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
