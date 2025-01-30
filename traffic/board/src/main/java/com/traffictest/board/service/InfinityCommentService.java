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

    // suppose 1000;
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

        // 5 depth까지 가자.
        // // 전부다 1단으로 가니까 문제가 되는 것 같다.
        while (counter < count) {
            // make 20 depth;
            List<InfinityComment> infinityComments = new ArrayList<>();
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
        // 이거.
    public void init2(int count) {
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

        // 5 depth까지 가자.
        // // 전부다 1단으로 가니까 문제가 되는 것 같다.
        while (counter < count) {
            // make 20 depth;
            List<InfinityComment> buffer = new ArrayList<>();
            InfinityComment comment = InfinityComment.builder()
                    .content("comment" + counter)
                    .writer(users.get(counter++ % 10))
                    .build();
            buffer.add(comment);
            // 1,2,3,4,5,6,7,8,9,10,........
            // more.
            List<InfinityComment> parents = new ArrayList<>();
            List<InfinityComment> children = new ArrayList<>();
            parents.add(comment);
            for (int i = 1; i < Integer.MAX_VALUE; i++) {
// 2배씩 증가하려면
                for (int j = 0; j < parents.size(); j++) {
                    for (int k = 0; k < parents.size() * 2; k++) {
                        comment = InfinityComment.builder()
                                .content("comment" + counter)
                                .parent(parents.get(j))
                                .writer(users.get(counter++ % 10))
                                .build();
                        children.add(comment);
                        buffer.add(comment);
                        if (buffer.size() == 1000) {
                            infinityCommentRepository.saveAll(buffer);
                            buffer.clear();
                        }
                        if (counter == count) {
                            infinityCommentRepository.saveAll(buffer);
                            return;
                        }
                    }
                    parents.clear();
                    parents = children;
                    children = new ArrayList<>();
                }

            }
        }
    }

}
