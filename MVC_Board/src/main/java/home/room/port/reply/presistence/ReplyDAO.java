package home.room.port.reply.presistence;

import java.util.List;

import home.room.port.commons.paging.Criteria;
import home.room.port.reply.domain.ReplyVO;


public interface ReplyDAO {
	List<ReplyVO> list(Integer boardNo) throws Exception;
	void create(ReplyVO replyVO) throws Exception;
	void update(ReplyVO replyVO) throws Exception;
	void delete(Integer replyNo) throws Exception;
	List<ReplyVO> listPaging(Integer boardNo, Criteria criteria) throws Exception;
	int countReply(Integer boardNo) throws Exception;
}
