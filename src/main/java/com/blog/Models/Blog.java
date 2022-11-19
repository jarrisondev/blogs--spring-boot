package com.blog.Models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_blog;


    @Column(name = "title")
    private String title;
  
    @Column(name = "content")
    private String content;
  
    @Column(name = "published")
    private boolean published;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    // @ManyToMany(mappedBy = "likedBlogs")
    // Set<User> likes;


    public Blog() {
    }

    public Blog(String title, String content, boolean published, User username) {
        this.title = title;
        this.content = content;
        this.published = published;
        this.username = username;
    }
    
    public Long getId() {
        return id_blog;
      }
    
      public String getTitle() {
        return title;
      }

      public String getUsername() {
        return username.getUsername();
      }
    
      public void setTitle(String title) {
        this.title = title;
      }
    
      public String getContent() {
        return content;
      }
    
      public void setContent(String content) {
        this.content = content;
      }
    
      public boolean isPublished() {
        return published;
      }
    
      public void setPublished(boolean isPublished) {
        this.published = isPublished;
      }
    
}
