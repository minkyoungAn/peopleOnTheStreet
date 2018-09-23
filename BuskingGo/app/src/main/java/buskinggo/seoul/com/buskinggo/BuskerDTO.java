package buskinggo.seoul.com.buskinggo;

public class BuskerDTO {
    private String buskerName;
    private String photo;
    private String mainPlace;
    private String genre;
    private String introduce;
    private int favorite;
    private int myFavorite;

    public BuskerDTO(int favorite, int myFavorite){
        this.favorite = favorite;
        this.myFavorite = myFavorite;
    }
    public BuskerDTO(String buskerName, String photo, String mainPlace, String genre, String introduce){
        this.buskerName = buskerName;
        this.photo = photo;
        this.mainPlace = mainPlace;
        this.genre = genre;
        this.introduce = introduce;
    }
    public BuskerDTO(String buskerName,String photo, String mainPlace, String genre, String introduce, int favorite, int myFavorite){
        this.buskerName = buskerName;
        this.photo = photo;
        this.mainPlace = mainPlace;
        this.genre = genre;
        this.introduce = introduce;
        this.favorite = favorite;
        this.myFavorite = myFavorite;
    }


    public String getBuskerName() {
        return buskerName;
    }

    public void setBuskerName(String buskerName) {
        this.buskerName = buskerName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMainPlace() {
        return mainPlace;
    }

    public void setMainPlace(String mainPlace) {
        this.mainPlace = mainPlace;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }


    public int getMyFavorite() {
        return myFavorite;
    }

    public void setMyFavorite(int myFavorite) {
        this.myFavorite = myFavorite;
    }
}
