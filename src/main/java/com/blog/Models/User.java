package com.blog.Models;

import java.util.Set;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
  
  
  @Column(name = "name")
  private String name;
  
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy = "username")
    private Set<Blog> blogs;


  // @ManyToMany(cascade = { CascadeType.ALL,  CascadeType.MERGE })
  // @JoinTable(
  //   name = "blogs_favourites", 
  //   joinColumns = @JoinColumn(name = "username"),
  //   inverseJoinColumns = @JoinColumn(name = "id_blog")
  // )
  // private List<Blog> likedBlogs = new ArrayList()<>();
  

  public User() {
  }

  public User(String name, String username, String password) {
      this.name = name;
      this.username = username;
      this.password = password;
  }
  
  
    public String getName() {
      return name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
     
}
