package com.ewhatever.qna.post.controller;

import com.ewhatever.qna.common.Base.BaseException;
import com.ewhatever.qna.common.Base.BaseResponse;
import com.ewhatever.qna.post.dto.GetPostsRes;
import com.ewhatever.qna.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    /**
     * [GET] 주씨글 목록 조회
     */
    @ResponseBody
    @GetMapping("")
    public BaseResponse<Page<GetPostsRes>> getPosts(@PageableDefault(size = 10) Pageable page,
                                                    @RequestParam(required = false) String category) {
        try {
            if (category.isBlank()) { // 전체 조회
                return new BaseResponse<>(postService.getPosts(page));
            } else return new BaseResponse<>(postService.getPostsByCategory(category, page)); // 카테고리 조회
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}