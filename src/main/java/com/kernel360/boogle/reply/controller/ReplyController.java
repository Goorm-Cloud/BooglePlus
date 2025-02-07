package com.kernel360.boogle.reply.controller;

import com.kernel360.boogle.member.db.MemberEntity;
import com.kernel360.boogle.member.model.MemberDTO;
import com.kernel360.boogle.reply.model.ReplyDTO;
import com.kernel360.boogle.reply.service.ReplyService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글 관련 API")
@RestController
@Slf4j
public class ReplyController {

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    private final ReplyService replyService;

    @PostMapping("/reply")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "공백은 입력될 수 없습니다.")
    })
    public void createReply(@RequestBody ReplyDTO reply, @AuthenticationPrincipal MemberEntity memberEntity) {
        replyService.createReply(reply, MemberDTO.from(memberEntity));
    }

    @CrossOrigin
    @PatchMapping("/reply")
    @ApiResponses({
            @ApiResponse(responseCode = "401", description = "공백은 입력될 수 없습니다.")
    })
    public void updateReply(@RequestBody ReplyDTO reply, @AuthenticationPrincipal MemberEntity memberEntity) {
        log.info("댓글 수정이 수행됨. 수정 전 댓글 정보: " + replyService.getReplyById(reply.getReplyEntity().getId()) + " 수정 후 댓글 정보: " + reply.getReplyEntity());
        replyService.updateReply(reply, MemberDTO.from(memberEntity));
    }

    @DeleteMapping("/reply")
    public void deleteReply(@RequestBody ReplyDTO reply) {
        log.info("댓글/대댓글 삭제가 수행됨. 삭제된 댓글 정보: " + replyService.getReplyById(reply.getId()) + " 추가로 삭제된 대댓글 정보: " + replyService.getRepliesByParentReplyId(reply.getId()));
        replyService.deleteReply(reply);
    }
}
