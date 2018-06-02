package entity;


public class Xctyperate {

  private String comment_place;
  private String view_type;
  private double prate;
  private double nrate;
  private long pnum;
  private long nnum;
  private long anum;


  public String getComment_place() {
    return comment_place;
  }

  public void setComment_place(String comment_place) {
    this.comment_place = comment_place;
  }

  public String getView_type() {
    return view_type;
  }

  public void setView_type(String view_type) {
    this.view_type = view_type;
  }

  public double getPrate() {
    return prate;
  }

  public void setPrate(double prate) {
    this.prate = prate;
  }


  public double getNrate() {
    return nrate;
  }

  public void setNrate(double nrate) {
    this.nrate = nrate;
  }


  public long getPnum() {
    return pnum;
  }

  public void setPnum(long pnum) {
    this.pnum = pnum;
  }


  public long getNnum() {
    return nnum;
  }

  public void setNnum(long nnum) {
    this.nnum = nnum;
  }


  public long getAnum() {
    return anum;
  }

  public void setAnum(long anum) {
    this.anum = anum;
  }

}
