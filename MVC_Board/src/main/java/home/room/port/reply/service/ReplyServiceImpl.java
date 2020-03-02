package home.room.port.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import home.room.port.commons.paging.Criteria;
import home.room.port.reply.domain.ReplyVO;
import home.room.port.reply.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	private ReplyDAO replyDAO;
	
	@Inject
	public ReplyServiceImpl(ReplyDAO replyDAO) {
		// TODO Auto-generated constructor stub
		this.replyDAO = replyDAO;
	}

	@Override
	public List<ReplyVO> getReplies(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.list(boardNo);
	}

	@Override
	public void addReply(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.create(replyVO);
	}

	@Override
	public void modifyReply(ReplyVO replyVO) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.update(replyVO);
	}

	@Override
	public void removeReply(Integer replyNo) throws Exception {
		// TODO Auto-generated method stub
		replyDAO.delete(replyNo);
	}

	@Override
	public List<ReplyVO> getRepliesPaging(Integer boardNo, Criteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.listPaging(boardNo, criteria);
	}

	@Override
	public int countReplies(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.countReply(boardNo);
	}
	

}
