package com.example.socialwestern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         <color name="colorPrimary">#4267B2</color>
//    <color name="colorPrimaryDark">#2F477A</color>

        TabLayout tabLayout = findViewById(R.id.top_nav);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_forum_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_people_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_tvplay));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_notifications));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_hamb));



        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.divider));
        rv.addItemDecoration(dividerItemDecoration);

        rv.setAdapter(postAdapter);

        List<Post> posts = new ArrayList<>();

        Post post1 = new Post();
        post1.setImageViewUser(R.drawable.dragon_profile);
        post1.setImageViewPost(R.drawable.interior);
        post1.setTextViewUsername("Calango");
        post1.setTextViewContent("Podemos já vislumbrar o modo pelo qual o fenômeno da Internet desafia a capacidade de equalização dos conhecimentos estratégicos para atingir a excelência. o mundo atual, a competitividade nas transações comerciais deve passar por modificações independentemente do impacto na agilidade decisória.");
        post1.setTextViewTitle("interior.com".toUpperCase(Locale.ROOT));
        post1.setTextViewSubtitle("Assim mesmo, o fenômeno da Internet pode nos levar a considerar a reestruturação dos procedimentos normalmente adotados.");
        post1.setTextViewTime("2 min");
        posts.add(post1);

        Post post2 = new Post();
        post2.setImageViewUser(R.drawable.gato);
        post2.setImageViewPost(R.drawable.teclado);
        post2.setTextViewUsername("Cat");
        post2.setTextViewContent("Podemos já vislumbrar o modo pelo qual o fenômeno da Internet desafia a capacidade de equalização dos conhecimentos estratégicos para atingir a excelência. o mundo atual, a competitividade nas transações comerciais deve passar por modificações independentemente do impacto na agilidade decisória.");
        post2.setTextViewTime("2 min");
        posts.add(post2);

        posts.add(post1);
        posts.add(post2);
        posts.add(post1);
        posts.add(post2);
        posts.add(post1);
        posts.add(post2);
        posts.add(post1);
        posts.add(post2);
        posts.add(post1);
        posts.add(post2);
        posts.add(post1);
        posts.add(post2);


        postAdapter.setPosts(posts);
        postAdapter.notifyDataSetChanged();

    }

    private static class PostViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewUser;
        private final TextView textViewTime;
        private final TextView textViewUsername;
        private final TextView textViewContent;
        private final ImageView imageViewPost;
        private final TextView textViewTitle;
        private final TextView textViewSubtitle;


        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewUser = itemView.findViewById(R.id.image_view_user);
            imageViewPost = itemView.findViewById(R.id.image_view_post);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewUsername = itemView.findViewById(R.id.text_view_username);
            textViewContent = itemView.findViewById(R.id.text_view_content);
            textViewTitle = itemView.findViewById(R.id.text_post_title);
            textViewSubtitle = itemView.findViewById(R.id.text_post_subtitle);
        }

        void bind(Post post) {
            imageViewPost.setImageResource(post.getImageViewPost());
            imageViewUser.setImageResource(post.getImageViewUser());
            textViewTime.setText(post.getTextViewTime());
            textViewUsername.setText(post.getTextViewUsername());
            textViewContent.setText(post.getTextViewContent());
            textViewTitle.setText(post.getTextViewTitle());
            textViewSubtitle.setText(post.getTextViewSubtitle());

            if (post.getTextViewTitle() == null){
                itemView.findViewById(R.id.post_container).setVisibility(View.GONE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) itemView);
                constraintSet.setDimensionRatio(R.id.image_view_post, "1:1");
                constraintSet.applyTo((ConstraintLayout) itemView);
            }else {
                itemView.findViewById(R.id.post_container).setVisibility(View.VISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone((ConstraintLayout) itemView);
                constraintSet.setDimensionRatio(R.id.image_view_post, "16:9");
                constraintSet.applyTo((ConstraintLayout) itemView);
            }
        }
    }

    private class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

        private List<Post> posts = new ArrayList<>();

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.feed_item, parent, false);
            return new PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
            Post post = posts.get(position);
            holder.bind(post);
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }
    }


    private class Post {
        private int ImageViewUser;
        private String textViewTime;
        private String textViewUsername;
        private String textViewContent;
        private int ImageViewPost;
        private String textViewTitle;
        private String textViewSubtitle;

        public int getImageViewUser() {
            return ImageViewUser;
        }

        public void setImageViewUser(int imageViewUser) {
            ImageViewUser = imageViewUser;
        }

        public String getTextViewTime() {
            return textViewTime;
        }

        public void setTextViewTime(String textViewTime) {
            this.textViewTime = textViewTime;
        }

        public String getTextViewUsername() {
            return textViewUsername;
        }

        public void setTextViewUsername(String textViewUsername) {
            this.textViewUsername = textViewUsername;
        }

        public String getTextViewContent() {
            return textViewContent;
        }

        public void setTextViewContent(String textViewContent) {
            this.textViewContent = textViewContent;
        }

        public int getImageViewPost() {
            return ImageViewPost;
        }

        public void setImageViewPost(int imageViewPost) {
            ImageViewPost = imageViewPost;
        }

        public String getTextViewTitle() {
            return textViewTitle;
        }

        public void setTextViewTitle(String textViewTitle) {
            this.textViewTitle = textViewTitle;
        }

        public String getTextViewSubtitle() {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(String textViewSubtitle) {
            this.textViewSubtitle = textViewSubtitle;
        }
    }

}