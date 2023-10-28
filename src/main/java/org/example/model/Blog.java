package org.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "slug")
    private String slug;
    @Column(name = "banner_url")
    private String bannerURL;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "blog_introduction", columnDefinition = "TEXT")
    private String blogIntroduction;

    @Column(name = "blog_body", columnDefinition = "TEXT")
    private String blogBody;

    @Column(name = "blog_conclusion", columnDefinition = "TEXT")
    private String blogConclusion;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    public Blog() {

    }

    public Blog(String slug,
                String bannerURL,
                String title,
                String summary,
                String blogIntroduction,
                String blogBody,
                String blogConclusion,
                Author author
    ) {
        this.slug = slug;
        this.bannerURL = bannerURL;
        this.title = title;
        this.summary = summary;
        this.blogIntroduction = blogIntroduction;
        this.blogBody = blogBody;
        this.blogConclusion = blogConclusion;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBlogIntroduction() {
        return blogIntroduction;
    }

    public void setBlogIntroduction(String blogIntroduction) {
        this.blogIntroduction = blogIntroduction;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }

    public String getBlogConclusion() {
        return blogConclusion;
    }

    public void setBlogConclusion(String blogConclusion) {
        this.blogConclusion = blogConclusion;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
