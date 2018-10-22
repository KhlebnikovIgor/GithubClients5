package ru.btec.smr.githubusers.rest;


import retrofit2.http.Query;
import ru.btec.smr.githubusers.model.GithubUser;
import ru.btec.smr.githubusers.model.RepsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Endpoints {

    @GET("/users/{user}")
    Observable<GithubUser> getUser(
            @Path("user") String user);

    @GET("/users")
    Flowable<List<GithubUser>> getUsers(@Query("since") long since, @Query("per_page") int per_page);

    @GET("/search/users")
    Flowable<List<GithubUser>> getSearchUsers(@Query("q") String login, @Query("page") int page);
//    https://api.github.com/search/users?q=tom&page=34

    @GET("/repositories")
    Flowable<List<RepsModel>> getRepos();
}
