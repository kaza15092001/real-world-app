package com.khoingyen.realworldapp.entity;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article_tbl")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private String tagList;
    private Date createdAt;
    private Date updateAt;
    private boolean favorited;
    private int favoriteCount;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public List<String> getTagList() {
        return Arrays.asList(this.tagList.split(";"));
    }

    public void setTagList(List<String> tagList) {
        StringBuilder str = new StringBuilder();
        tagList.stream().forEach(s -> str.append(s).append(";"));
        this.tagList = str.substring(0, str.length() - 1).toString();
    }

    @ManyToMany(mappedBy = "articleFavorited")
    private Set
}
