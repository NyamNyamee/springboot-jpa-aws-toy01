package com.invu.domaint.posts;

import com.invu.domain.posts.Posts;
import com.invu.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest  // h2 database 자동 실행
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void post_create_read() {
        String title = "test title";
        String content = "test content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("inwoo94")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo("inwoo94");
    }

    @Test
    public void baseTimeEntity_save() {
        LocalDateTime now = LocalDateTime.of(2023, 7, 23, 12, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title01")
                .content("content01")
                .author("author01")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out.println("posts = " + posts.getTitle());
        System.out.println("posts = " + posts.getCreatedDate());
        System.out.println("posts = " + posts.getModifiedDate());

        posts.update("title02", "content02");
        System.out.println("posts = " + posts.getTitle());
        System.out.println("posts = " + posts.getCreatedDate());
        System.out.println("posts = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
