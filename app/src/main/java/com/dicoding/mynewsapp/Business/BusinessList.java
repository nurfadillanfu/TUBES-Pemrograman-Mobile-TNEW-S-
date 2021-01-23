
package com.dicoding.mynewsapp.Business;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessList implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;
    public final static Parcelable.Creator<BusinessList> CREATOR = new Creator<BusinessList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BusinessList createFromParcel(Parcel in) {
            return new BusinessList(in);
        }

        public BusinessList[] newArray(int size) {
            return (new BusinessList[size]);
        }

    }
            ;

    protected BusinessList(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.articles, (com.dicoding.mynewsapp.Business.Article.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public BusinessList() {
    }

    /**
     *
     * @param articles
     * @param totalResults
     * @param status
     */
    public BusinessList(String status, Integer totalResults, List<Article> articles) {
        super();
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(totalResults);
        dest.writeList(articles);
    }

    public int describeContents() {
        return  0;
    }

}
