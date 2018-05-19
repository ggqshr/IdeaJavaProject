package entity;


public class Xccleandatatable {

  private long id;
  private String authorName;
  private String content;
  private long useful;
  private long viewScore;
  private long interestScore;
  private long priceScore;
  private String viewType;
  private long totalScore;
  private String commentTime;
  private String commentPlace;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getUseful() {
    return useful;
  }

  public void setUseful(long useful) {
    this.useful = useful;
  }


  public long getViewScore() {
    return viewScore;
  }

  public void setViewScore(long viewScore) {
    this.viewScore = viewScore;
  }


  public long getInterestScore() {
    return interestScore;
  }

  public void setInterestScore(long interestScore) {
    this.interestScore = interestScore;
  }


  public long getPriceScore() {
    return priceScore;
  }

  public void setPriceScore(long priceScore) {
    this.priceScore = priceScore;
  }


  public String getViewType() {
    return viewType;
  }

  public void setViewType(String viewType) {
    this.viewType = viewType;
  }


  public long getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(long totalScore) {
    this.totalScore = totalScore;
  }


  public String getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(String commentTime) {
    this.commentTime = commentTime;
  }


  public String getCommentPlace() {
    return commentPlace;
  }

  public void setCommentPlace(String commentPlace) {
    this.commentPlace = commentPlace;
  }

}
