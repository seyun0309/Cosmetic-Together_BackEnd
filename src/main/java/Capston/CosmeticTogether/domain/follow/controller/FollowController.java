package Capston.CosmeticTogether.domain.follow.controller;

import Capston.CosmeticTogether.ResponseMessage;
import Capston.CosmeticTogether.domain.follow.dto.response.FollowResponseDTO;
import Capston.CosmeticTogether.domain.follow.dto.response.GetFollowAndFollowingMemberDTO;
import Capston.CosmeticTogether.domain.follow.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "팔로우 및 언팔로우", description = "팔로우, 언팔로우, 팔로워 리스트 조회, 팔로우 리스트 조회")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow/{followingId}")
    @Operation(summary = "[API] 상대방 팔로우 하기 - 토큰 필요", description = "상대방 id를 URL 경로에 포함시켜 전달하면 팔로우가 됩니다")
    public ResponseEntity<ResponseMessage> followOrUnFollow(@PathVariable("followingId") Long followingId) {
        FollowResponseDTO response = followService.likeOrUnlikeBoard(followingId);
        if(response.isValid()) {
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), response.getFollowingNickName() + "님을 팔로우하였습니다"));
        } else {
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), response.getFollowingNickName() + "님을 언팔로우하였습니다"));
        }
    }

    @GetMapping("/followers")
    @Operation(summary = "[API] 본인의 팔로워 가져오기 - 토큰 필요", description = "본인의 팔로워를 사진, 이름과 함께 전달합니다")
    public ResponseEntity<List<GetFollowAndFollowingMemberDTO>> getFollowers() {
        List<GetFollowAndFollowingMemberDTO> response = followService.getFollowers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/followings")
    @Operation(summary = "[API] 본인의 팔로잉 가져오기 - 토큰 필요", description = "본인의 팔로잉을 사진, 이름과 함께 전달합니다")
    public ResponseEntity<List<GetFollowAndFollowingMemberDTO>> getFollowings() {
        List<GetFollowAndFollowingMemberDTO> response = followService.getFollowings();
        return ResponseEntity.ok(response);
    }
}
