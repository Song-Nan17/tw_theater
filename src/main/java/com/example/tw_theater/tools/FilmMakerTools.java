package com.example.tw_theater.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.tw_theater.model.FilmMaker;

import java.util.ArrayList;
import java.util.List;

public class FilmMakerTools {

    public List<FilmMaker> generateFilmMakers(JSONArray filmMakersStr) {
        List<FilmMaker> filmMakers = new ArrayList<>();
        for (int i = 0; i < filmMakersStr.size(); i++) {
            JSONObject filmMakerStr = filmMakersStr.getJSONObject(i);
            FilmMaker filmMaker = generateFilmMaker(filmMakerStr);
            filmMakers.add(filmMaker);
        }
        return filmMakers;
    }

    FilmMaker generateFilmMaker(JSONObject filmMakerStr) {
        String id = filmMakerStr.getString("id");
        String name = filmMakerStr.getString("name");
        String alt = filmMakerStr.getString("alt");
        String image = filmMakerStr.getJSONObject("avatars").getString("small");

        FilmMaker filmMaker = new FilmMaker();
        filmMaker.setId(id);
        filmMaker.setName(name);
        filmMaker.setAlt(alt);
        filmMaker.setImage(image);
        return filmMaker;
    }
}
