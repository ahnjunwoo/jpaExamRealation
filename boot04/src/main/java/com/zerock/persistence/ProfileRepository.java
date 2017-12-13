package com.zerock.persistence;

import org.springframework.data.repository.CrudRepository;

import com.zerock.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, String>{
	

}
