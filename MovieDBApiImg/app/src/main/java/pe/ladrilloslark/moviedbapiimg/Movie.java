package pe.ladrilloslark.moviedbapiimg;

import com.google.gson.annotations.SerializedName;

class Movie {

    private  String title;
    private  String overview ;

    @SerializedName("poster_path")
    private  String posterPath;
    @SerializedName("release_date")
    private  String releaseDate;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
