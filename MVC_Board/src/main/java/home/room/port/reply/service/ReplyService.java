package home.room.port.reply.service;

import java.util.List;

import home.room.port.commons.paging.Criteria;
import home.room.port.reply.domain.ReplyVO;

public interface ReplyService {
	List<ReplyVO> getReplies(Integer boardNo) throws Exception;
	void addReply(ReplyVO replyVO) throws Exception;
	void modifyReply(ReplyVO replyVO) throws Exception;
	void removeReply(Integer boardNo) throws Exception;
	List<ReplyVO> getRepliesPaging(Integer boardNo, Criteria criteria) throws Exception;
	int countReplies(Integer boardNo) throws Exception;
}
