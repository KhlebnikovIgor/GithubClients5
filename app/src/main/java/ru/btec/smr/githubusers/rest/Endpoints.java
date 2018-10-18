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

    @GET("/users?per_page=50")
    Flowable<List<GithubUser>> getUsers(@Query("since") int since);

    @GET("/repositories")
    Flowable<List<RepsModel>> getRepos();
}
