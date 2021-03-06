package kr.inhatc.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.spring.dto.BoardDto;
import kr.inhatc.spring.model.Board;
import kr.inhatc.spring.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Transactional
	public int savePost (BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getBoardId();
	}
	
	@Transactional
	public List<BoardDto> getBoardList() {
		List<Board> boardList = boardRepository.findAll();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardDto boardDto = BoardDto.builder()
					.boardId(board.getBoardId())
					.boardTitle(board.getBoardTitle())
					.boardContent(board.getBoardContent())
					.fileId(board.getFileId())
					.goodCnt(board.getGoodCnt())
					.hitCnt(board.getHitCnt())
					.boardWriter(board.getBoardWriter())
					.useYn(board.getUseYn())
					.createSeq(board.getCreateSeq())
					.createdDate(board.getCreatedDate())
					.modifySeq(board.getModifySeq())
					.modifiedDate(board.getModifiedDate())
					.build();
			boardDtoList.add(boardDto);
		}
		
		return boardDtoList;
	}
	
	@Transactional
	public BoardDto getPost(int boardId) {
		Board board = boardRepository.findById(boardId).get();
		
		BoardDto boardDto = BoardDto.builder()
				.boardId(board.getBoardId())
				.boardWriter(board.getBoardWriter())
				.boardTitle(board.getBoardTitle())
				.boardContent(board.getBoardContent())
				.fileId(board.getFileId())
				.goodCnt(board.getGoodCnt())
				.hitCnt(board.getHitCnt())
				.useYn(board.getUseYn())
				.createSeq(board.getCreateSeq())
				.createdDate(board.getCreatedDate())
				.modifySeq(board.getModifySeq())
				.modifiedDate(board.getModifiedDate())
				.build();
		return boardDto;
	}
	//???????????? ????????? /post/{board_id}??? delete ??????????????? deletePost()??????
	@Transactional
	public void deltePost(int boardId) {
		boardRepository.deleteById(boardId);
	}
	
	// ????????? ????????????
	@Transactional
    public int updateView(int boardId) {
        return boardRepository.updateView(boardId);
    }
	
	// ????????? ????????? ??????
	@Transactional
    public int updatePlusGoodCnt(int boardId) {
        return boardRepository.updatePlusGoodCnt(boardId);
    }
	
	// ????????? ????????? ??????
	@Transactional
	public int updateMinusGoodCnt(int boardId) {
		return boardRepository.updateMinusGoodCnt(boardId);
	}
	
}