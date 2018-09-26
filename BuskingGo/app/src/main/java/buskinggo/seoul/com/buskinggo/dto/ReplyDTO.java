package buskinggo.seoul.com.buskinggo.dto;

public class ReplyDTO {
    private int commentNo;
    private String userNickName;
    private String currentTime;
    private String comment;
    private int replyNo;

    public ReplyDTO(int commentNo, String userNickName, String currentTime, String comment, int replyNo){
        this.commentNo = commentNo;
        this.userNickName = userNickName;
        this.currentTime = currentTime;
        this.comment = comment;
        this.replyNo = replyNo;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }
}
