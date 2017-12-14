package com.zerock;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zerock.domain.FreeBoard;
import com.zerock.domain.FreeBoardReply;
import com.zerock.domain.Member;
import com.zerock.domain.Profile;
import com.zerock.persistence.FreeBoardReplyRepository;
import com.zerock.persistence.FreeBoardRepository;
import com.zerock.persistence.MemberRepository;
import com.zerock.persistence.ProfileRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {
	@Autowired
	FreeBoardRepository boardRepo;
	
	@Autowired
	FreeBoardReplyRepository replyRepo;
	
	@Test
	@Ignore
	public void insertDummy() {
		IntStream.range(1, 200).forEach(i -> {
			FreeBoard board = new FreeBoard();
			board.setTitle("Free title.... "+i);
			board.setContent("Free content.... "+i);
			board.setWriter("user "+i);
			boardRepo.save(board);
			log.info("dd");
		});
	}
	@Transactional
	@Test
	public void insertReply2Way() {
		Optional<FreeBoard> result = boardRepo.findById(199L);
		
		result.ifPresent(board ->{
			List<FreeBoardReply> replies = board.getReplies();
			FreeBoardReply reply = new FreeBoardReply();
			reply.setReply("REPLY......");
			reply.setReplyer("ahnjunwoo");
			reply.setBoard(board);
			
			replies.add(reply);
			
			board.setReplies(replies);
			
			boardRepo.save(board);
		});
	}
	
	@Test
	@Ignore
	public void insertReply1Way() {
		
		FreeBoard board = new FreeBoard();
		board.setBno(199L);
		
		FreeBoardReply reply = new FreeBoardReply();
		reply.setReply("REPLY......................");
		reply.setReplyer("replyer00");
		reply.setBoard(board);
		
		replyRepo.save(reply);
	}
	@Test
	@Ignore
	public void testList1() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board->{
			log.info(board.getBno() +": "+board.getTitle());
		});
	}
	@Transactional
	@Test
	@Ignore
	public void testList2() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		boardRepo.findByBnoGreaterThan(0L, page).forEach(board->{
			log.info(board.getBno() +": "+board.getTitle() + ":" + board.getReplies().size());
		});
	}
	@Test
	public void testList3() {
		Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC,"bno");
		boardRepo.getPage(page).forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	
}
