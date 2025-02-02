package com.traffictest.board.service;

import com.traffictest.entity.InfinityComment;
import com.traffictest.entity.InfinityCommentRepository;
import com.traffictest.entity.User;
import com.traffictest.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfinityCommentService {

    private final InfinityCommentRepository infinityCommentRepository;
    private final UserRepository userRepository;

    // 45000 = 1sec
    public List<InfinityComment> findAll() {
        return infinityCommentRepository.findCommentHierarchy();
    }

    public void init(int count) {
        int counter = 0;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(User.builder()
                    .username("user" + i)
                    .build());
        }
        userRepository.saveAll(users);
        // 5단계 댓글 생성
        // need fix
        // 댓글 대댓
        //
        while (counter < count) {
            // make 100 depth;
            List<InfinityComment> infinityComments = new ArrayList<>();
            // 첫 comment
            InfinityComment comment = InfinityComment.builder()
                    .content("comment" + counter)
                    .writer(users.get(counter++ % 10))
                    .build();
            infinityComments.add(comment);
            for (int i = 0; i < 99; i++) {
                comment = InfinityComment.builder()
                        .content("comment" + counter)
                        .parent(comment)
                        .writer(users.get(counter++ % 10))
                        .build();
                infinityComments.add(comment);
            }
            infinityCommentRepository.saveAll(infinityComments);
            infinityComments.clear();
        }
    }

}
