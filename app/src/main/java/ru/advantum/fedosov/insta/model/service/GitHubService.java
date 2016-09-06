package ru.advantum.fedosov.insta.model.service;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.advantum.fedosov.insta.model.Repo;

/**
 * Created by fedosov on 9/6/16.
 */
public  interface GitHubService {
    @GET("users/{user}/repos")
    rx.Observable<List<Repo>> listRepos(@Path("user") String user);
}
